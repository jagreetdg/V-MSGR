package main;
import client_resources.ClientManager;
import client_resources.LoginFrame;
/**
 *
 * @author Jagreet Das Gupta
 */
public class Client_Main {

    
    public static void run() 
    {
        ClientManager clientManager=new ClientManager();
        LoginFrame loginFrame=new LoginFrame(clientManager);
        loginFrame.setVisible(true);
        // TODO code application logic here
    }

}
