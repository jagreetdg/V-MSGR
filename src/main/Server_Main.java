package main;
import server_resources.ServerManager;
import server_resources.ServerMonitor;
/**
 *
 * @author Jagreet Das Gupta
 */
public class Server_Main
{

    public static void run()
    {
        ServerManager serverManager=new ServerManager();
        ServerMonitor monitor=new ServerMonitor(serverManager);

        monitor.setVisible(true);
    }

}
