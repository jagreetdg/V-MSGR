package server_resources;

import static server_resources.ServerConstant.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Jagreet Das Gupta
 */
public class ServerManager implements MessageListener
{
    ExecutorService serverExeCutor;
    ServerSocket server;
    Socket clientSocket;
    Clients[] client;
    int clientNumber=0;
    static String[] clientTracker;
    String users="";

    public ServerManager()
    {
        client=new Clients[CLIENT_NUMBER];
        clientTracker=new String [CLIENT_NUMBER];
        serverExeCutor=Executors.newCachedThreadPool();
    }

    public void startServer(ServerStatusListener statusListener,ClientListener clientListener)
    {
        try
        {
            statusListener.status("Server Listening On Port : "+SERVER_PORT);

            server=new ServerSocket(SERVER_PORT,BACKLOG);
            serverExeCutor.execute(new ConnectionController(statusListener,clientListener));
        }
        catch(IOException ioe)
        {
            statusListener.status("IOException Occured While Starting Server");
        }
    }

    public void stopServer(ServerStatusListener statusListener)
    {
        try 
        {
            server.close();
            statusListener.status("Server Stopped");
        }
        catch(SocketException ex)
        {
            //ex.printStackTrace();
            statusListener.status("SocketException Occured During Stoppage Of Server");
        }
        catch (IOException ioe)
        {
            //ioe.printStackTrace();
            statusListener.status("IOException Occured During Stoppage Of Server");
        }
    }

    public void controllConnection(ServerStatusListener statusListener,ClientListener clientListener)
    {
        while(clientNumber<CLIENT_NUMBER)
        { 
            try
            {
                clientSocket= server.accept();
                client[clientNumber]=new Clients(clientListener,clientSocket,this,clientNumber);
                serverExeCutor.execute(client[clientNumber]);
                clientNumber++;
                //main.UI.writeLogReportln(clientNumber);
            }
            catch(SocketException ex)
            {
                ex.printStackTrace();
                break;
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
                statusListener.status("Problem Occured While Receiving Connection");
                break;
            }
        }
    }


    public void sendInfo(String message)
    {
        StringTokenizer tokens=new StringTokenizer(message);
        String to=tokens.nextToken();

        for(int i=0;i<clientNumber;i++)
        {
            if(clientTracker[i].equalsIgnoreCase(to))
            {
                try
                {
                    client[i].output.writeObject(message);
                    client[i].output.flush();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void sendFile(String sendTo,int file)
    {
        for(int i=0;i<clientNumber;i++)
        {
            if(clientTracker[i].equalsIgnoreCase(sendTo))
            {
                try
                {
                    client[i].output.writeInt(file);
                    client[i].output.flush();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void sendNameToAll(String message)
    {
        for(int i=0;i<clientNumber;i++)
        {
            try
            {
                main.UI.writeLogReportln("Server Is Sending   "+ message);
                client[i].output.writeObject(message);
                client[i].output.flush();
            }
            catch (IOException ex)
            {}
        }
    }

    class ConnectionController implements Runnable
    {
        ServerStatusListener statusListener;
        ClientListener clientListener;

        ConnectionController(ServerStatusListener getStatusListener,ClientListener getClientListener)
        {
            statusListener=getStatusListener;
            clientListener=getClientListener;
        }

        public void run()
        {
            controllConnection(statusListener,clientListener);
        }
    }
}
