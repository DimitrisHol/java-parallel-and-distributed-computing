import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class MasterProtocol {

	public String processRequest(long numSteps , long start , long stop) {

		String theOutput = numSteps + "-"  + String.valueOf(start) + "-" + String.valueOf(stop);

		return theOutput;
	}

}
