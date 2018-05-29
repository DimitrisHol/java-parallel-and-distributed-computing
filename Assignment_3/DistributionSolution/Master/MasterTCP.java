import java.net.*;
import java.io.*;

public class MasterTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;

	public static void main(String args[]) throws IOException {

		long numSteps = 0;

		//Parsing numSteps
	  if (args.length != 1) {
	    System.out.println("arguments:  number_of_steps");
	    System.exit(1);
	  }
	  try {
	    numSteps = Long.parseLong(args[0]);


	  } catch (NumberFormatException e) {
	    System.out.println("argument "+ args[0] +" must be long int");
	    System.exit(1);
	  }

		// Get the number of the availabe cores.
		int cores = Runtime.getRuntime().availableProcessors();

		// Set the number of threads to run.
		int numThreads = cores;

		//Array to store sums that return from Workers.
		double[] localSums = new double[numThreads];


		Socket dataSocket = new Socket(HOST ,PORT);
		//Connection established.


		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);

		// MasterProtocol app = new MasterProtocol();
		String inmsg , outmsg;
		long startTime;

		startTime = System.nanoTime();

		//Divide and assign work to our... volunteers.
		for (int i= 0; i< numThreads; i++){

			//Divide
			long start = i * (numSteps / numThreads);
			long stop = start + (numSteps / numThreads);
			if (i == numThreads -1) stop = numSteps;

			//Send numsteps , start , finish to MasterProtocol?
			outmsg =numSteps + "-"  + String.valueOf(start) + "-" + String.valueOf(stop);

			//Conquer
			out.println(outmsg);
			startTime = System.nanoTime();
		}
		//Work Complete
		out.println("CLOSE");

		int counter = 0;
		double pi = 0;

		while (counter < numThreads){
			inmsg = in.readLine();
			pi += Double.parseDouble(inmsg);
			counter ++;
		}


		System.out.printf("distributed program results with %d steps\n", numSteps);
		System.out.printf("computed pi = %22.20f\n" , pi);
		System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));


		System.out.printf("Response time : %f seconds \n" , (double)(System.nanoTime() - startTime) / 1000000000);

	}
}
