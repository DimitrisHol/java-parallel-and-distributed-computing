import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class ServerProtocol {

	private ArrayList<Address> list;


	public ServerProtocol(ArrayList<Address> list) {

		this.list = list;
	}
	public String processRequest(String theInput) {
		String theOutput = null;

		System.out.println("Received message from client: " + theInput);



		switch (theInput.charAt(0)) {

 			case 'N' :
 			// System.out.println("Search by name");
			String msgName = theInput.substring(2);

			for (Address adIndex : list){
				if (adIndex.getName().equals(msgName)){
					return adIndex.getIP();
				}
			}

			theOutput = "I cannot find an address with the corresponding domain name";
 			break;

 			case 'A' :
 			// System.out.println("Search by address");
			String msgIP = theInput.substring(2);

			for (Address adIndex : list){
				if (adIndex.getIP().equals(msgIP)){
					return adIndex.getName();
				}
			}
			theOutput = "I cannot find an domain name with the corresponding IP address";
 			break;

 			case 'I' :
 			theOutput = "Invalid domain name or IP address";
 			break;

 			case 'D' :
 			theOutput = "Invalid domain name or IP address";
 			break;

 			case 'U' :
 			theOutput = "Invalid domain name or IP address";
 			break;

 			default:
			theOutput = "It seems you have made a terrible mistake";
 			break;

 		}



		System.out.println("Send message to client: " + theOutput);
		return theOutput;
	}
}
