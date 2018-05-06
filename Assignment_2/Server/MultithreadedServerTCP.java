import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class MultithreadedServerTCP {
	private static final int PORT = 1234;
	public static ArrayList<Address> list = new ArrayList<Address>();

	public static void main(String args[]) throws IOException {

		//Opening the listening socket.
		ServerSocket connectionSocket = new ServerSocket(PORT);

		Address p1 = new Address("122.122.122.122" , "test.com");
		Address p2 = new Address("122.122.122.622" , "sports.com");
		Address p3 = new Address("122.122.122.922" , "news.com");

		list.add(p1);
		list.add(p2);
		list.add(p3);

		while (true) {

			System.out.println("Server is listening to port: " + PORT);

			//New client is trying to connect, so we accept the request.
			Socket dataSocket = connectionSocket.accept();
			System.out.println("Received request from " + dataSocket.getInetAddress());

			// Dispatcher, creating a new ServerThread for each client that requests a connection.
			ServerThread sthread = new ServerThread(dataSocket , list);
			sthread.start();
		}
	}
}
