import java.io.*;
import java.net.*;
import java.util.ArrayList;

class ServerThread extends Thread
{

	// We receive this as a parameter in MultithreadedServerTCP
	private Socket dataSocket;

	// Input and output streams (Keyboard and Monitor)
	private InputStream is;
  private BufferedReader in;
	private OutputStream os;
  private PrintWriter out;

	private ArrayList<Address> list;
	//Message whent to close the socket. Client connection stops, but server is still online.
	//Only the thread is closed.
	private static final String EXIT = "CLOSE";

   	public ServerThread(Socket socket , ArrayList<Address> list)
   	{
      	dataSocket = socket;
      	try {
							is = dataSocket.getInputStream();
							in = new BufferedReader(new InputStreamReader(is));
							os = dataSocket.getOutputStream();
							out = new PrintWriter(os,true);
							this.list = list;
		}
				catch (IOException e)	{System.out.println("I/O Error " + e);}
    }

	public void run()
	{
   		String inmsg, outmsg;

		try {
				inmsg = in.readLine();
				ServerProtocol app = new ServerProtocol(list);
				outmsg = app.processRequest(inmsg);

				while(!outmsg.equals(EXIT)) {
						out.println(outmsg);
						inmsg = in.readLine();
						outmsg = app.processRequest(inmsg);
				}

			dataSocket.close();
			System.out.println("Data socket closed");

		} catch (IOException e)	{System.out.println("I/O Error " + e);}
	}
}
