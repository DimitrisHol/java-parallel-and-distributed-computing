import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

public class FastClientProtocol {

	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

	public String prepareRequest() throws IOException {

    System.out.print("Enter message to send to server:");
		String theOutput = user.readLine();

		while (!isValid(theOutput)){
			System.out.print("ERROR : wrong message format. Please Enter message to send to server : ");
			theOutput = user.readLine();
		}
		return theOutput;
    }

	public void processReply(String theInput) throws IOException {
		System.out.println("Message received from server: " + theInput);
	}

	public static boolean isValid(String line){

	 String pattern = "[A]\\s[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}|[N]\\s(\\w{1,}\\.){1,}\\w{1,}";


	 // Create a Pattern object
	 Pattern r = Pattern.compile(pattern);

	 // Now create matcher object.
	 Matcher m = r.matcher(line);
	 return m.matches() || line.equals("CLOSE");
	 // return true;
	}
}
