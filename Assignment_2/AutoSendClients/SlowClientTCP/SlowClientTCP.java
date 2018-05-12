/*

(β) Πελάτες εγγραφής που εκτελούν λίγες αλλά ‘αργές ‘(με σχετικά μεγάλη καθυστέρηση)
λειτουργίες I, D, U. (INSERT , DELETE , UPDATE)
*/
import java.util.*;
import java.net.*;
import java.io.*;

public class SlowClientTCP {

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
  		SlowClientProtocol app = new SlowClientProtocol();

      for (int i = 1; i < 4; i++) {
        
        switch (i) {
          case 1:
          outmsg = app.prepareRequest("D 122.122.122.122 test.com");
          break;

          case 2:
          outmsg = app.prepareRequest("I 122.122.122.522 dimitris.com");
           
          break;

          case 3:
          outmsg = app.prepareRequest("U 122.122.122.922 dogs.com");
           
          break;

          
          default:
          outmsg = "A 122.122.122.122";
       }
        out.println(outmsg);
        startTime = System.nanoTime();
        inmsg = in.readLine();
        app.processReply(inmsg);
        System.out.println("Response time : " + (System.nanoTime() - startTime) / 100000 + "ms");
        try{
          Thread.sleep(4000);
        }catch (InterruptedException e){}


    }


      out.println("CLOSE");
  		dataSocket.close();
  		System.out.println("Data Socket closed");
  	}
  }
