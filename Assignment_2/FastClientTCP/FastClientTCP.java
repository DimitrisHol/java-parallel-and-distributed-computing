/*
(α) Πελάτες ανάγνωσης που εκτελούν πολλές και ‘γρήγορες’ (με σχετικά μικρή καθυστέρηση)
λειτουργίες Ν, Α.  ΑΝΑΖΗΤΗΣΗ ΒΑΣΗ ΛΟΓΙΚΟΥ ΟΝΟΜΑΤΟΣ , ΑΝΑΖΗΤΗΣΗ ΒΑΣΗ ΔΙΕΥΘΥΘΝΗΣΗΣ (IP)
*/
import java.util.*;
import java.net.*;
import java.io.*;

public class FastClientTCP {

      private static final String HOST = "localhost";
	    private static final int PORT = 1234;
      private static final String EXIT = "CLOSE";

      public static void main(String args[]) throws IOException {

  		//InetAddress address = InetAddress.getByName(HOST);
      Socket dataSocket = new Socket(HOST, PORT);

      //Connection is established
      System.out.println("Connection to " + HOST + " established");

  		InputStream is = dataSocket.getInputStream();
  		BufferedReader in = new BufferedReader(new InputStreamReader(is));
  		OutputStream os = dataSocket.getOutputStream();
  		PrintWriter out = new PrintWriter(os,true);


  		String inmsg, outmsg;
      long startTime;
  		FastClientProtocol app = new FastClientProtocol();

      outmsg = app.prepareRequest();
      while(!outmsg.equals(EXIT)){
        out.println(outmsg);
        startTime = System.nanoTime();
        inmsg = in.readLine();
        app.processReply(inmsg);
        System.out.println("Response time : " + (System.nanoTime() - startTime) / 100000 + "ms");
        outmsg = app.prepareRequest();
      }
      out.println(outmsg);


  		dataSocket.close();
  		System.out.println("Data Socket closed");
  	}
  }
