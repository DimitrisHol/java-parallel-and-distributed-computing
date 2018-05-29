import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class WorkerProtocol {
	public String processRequest(String theInput) {

		System.out.println("Received message from client: " + theInput);

		if (theInput.equals("CLOSE")){
			return "CLOSE";
		}
		String array1[]= theInput.split("-");

		long numSteps = Long.valueOf(array1[0]);
		long start = Long.valueOf(array1[1]);
		long stop = Long.valueOf(array1[2]);

		double sum = 0.0;

    double step = 1.0 / (double)numSteps;
    //Computation
    for (long i = start; i < stop; i++){
      double x = ((double)i +0.5) * step;
      sum += 1 / (1.0 + x*x);
    }
    String theOutput = Double.toString(4 * sum * step);

		return theOutput;
	}


}
