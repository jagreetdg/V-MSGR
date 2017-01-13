package client_resources;

/**
 *
 * @author Jagreet Das Gupta
 */
import java.net.*;
import java.io.*;

public class FileSender implements Runnable
{
    String fileName;
  FileSender(String file)
  {
      fileName=file;
    // create socket
    
    }

  public void run()
  {
        try {
            ServerSocket servsock = new ServerSocket(13267);
            main.UI.writeLogReportln("Waiting...");
            Socket sock = servsock.accept();
            main.UI.writeLogReportln("Accepted connection : " + sock);
            // sendfile
            File myFile = new File(fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            OutputStream os = sock.getOutputStream();
            main.UI.writeLogReportln("Sending...");
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            sock.close();
        } 
        catch (IOException ex)
        {
        }
      }
}
