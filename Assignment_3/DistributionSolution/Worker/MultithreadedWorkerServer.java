import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class MultithreadedWorkerServer {
	private static final int PORT = 1234;

	public static void main(String args[]) throws IOException {

		//Opening the listening socket.
		ServerSocket connectionSocket = new ServerSocket(PORT);

		int counter = 0;	//For debbuging
		while (true) {

			System.out.println("Server is listening to port: " + PORT);

			//New client is trying to connect, so we accept the request.
			Socket dataSocket = connectionSocket.accept();
			counter ++;
			System.out.println("Received request from " + dataSocket.getInetAddress() + " "+ counter);

			// Dispatcher, creating a new ServerThread for each client that requests a connection.
			WorkerThread wthread = new WorkerThread(dataSocket);
			wthread.start();
		}
	}
}
