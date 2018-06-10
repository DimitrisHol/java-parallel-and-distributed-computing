import java.io.*;
import java.net.*;
import java.util.ArrayList;

class WorkerThread extends Thread
{

	// We receive this as a parameter in MultithreadedWorkerServer
	private Socket dataSocket;

	// Input and output streams
	private InputStream is;
  private BufferedReader in;
	private OutputStream os;
  private PrintWriter out;

	private static final String EXIT = "CLOSE";

   	public WorkerThread(Socket socket)
   	{
      	dataSocket = socket;
      	try {
							is = dataSocket.getInputStream();
							in = new BufferedReader(new InputStreamReader(is));
							os = dataSocket.getOutputStream();
							out = new PrintWriter(os,true);
		}
				catch (IOException e)	{System.out.println("I/O Error " + e);}
    }

	public void run()
	{
   		String inmsg, outmsg;

		try {
				inmsg = in.readLine();
				//Ready to work.
				WorkerProtocol app = new WorkerProtocol();

				outmsg = app.processRequest(inmsg);
				while (!outmsg.equals(EXIT)){
					out.println(outmsg);
					inmsg = in.readLine();
					outmsg = app.processRequest(inmsg);
				}
				//Work Complete!

			dataSocket.close();
			System.out.println("Data socket closed");

		} catch (IOException e)	{System.out.println("I/O Error " + e);}
	}
}
