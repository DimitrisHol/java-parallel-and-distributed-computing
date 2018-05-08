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
		String msgIP;
		String msgName;

		System.out.println("Received message from client: " + theInput);

		if (theInput.equals("CLOSE")){
			return "CLOSE";
		}

		switch (theInput.charAt(0)) {

 			case 'N' :
 			// System.out.println("Search by name");
			msgName = theInput.substring(2);

			for (Address adIndex : list){
				if (adIndex.getName().equals(msgName)){
					return adIndex.getIP();
				}
			}

			theOutput = "ERROR : IP address cannot be matched with the corresponding domain name";
 			break;

 			case 'A' :
 			// System.out.println("Search by address");
			msgIP = theInput.substring(2);

			for (Address adIndex : list){
				if (adIndex.getIP().equals(msgIP)){
					return adIndex.getName();
				}
			}
			theOutput = "ERROR : Domain name cannot be matched with the corresponding IP address";
 			break;

 			case 'I' :

			// Preparing String
			// String = I 122.122.122.122 news.com

			msgIP = theInput.substring(2 , 17);
			msgName = theInput.substring(18);

			// Checking if pair is already there.

			 for (Address adIndex : list){
				 if (adIndex.getIP().equals(msgIP) || adIndex.getName().equals(msgName)){
					 return "ERROR : Domain name or IP address already in use.";
				 }
			 }
			 list.add(new Address(msgIP , msgName));
			 return "OK";

 			case 'D' :
			// Preparing String
			// String = I 122.122.122.122 news.com

			msgIP = theInput.substring(2 , 17);
			msgName = theInput.substring(18);

			// Checking if pair is there.

			 for (Address adIndex : list){
				 if ((adIndex.getIP().equals(msgIP)) && (adIndex.getName().equals(msgName))){
					 list.remove(adIndex);
					 return "OK";

				 }

			 }
			 return "ERROR : Domain name and IP address pair not found.";

 			case 'U' :

			// In IP change the domain name.

			// Preparing String
			// String = I 122.122.122.122 news.com

			msgIP = theInput.substring(2 , 17);
			msgName = theInput.substring(18);


			 for (Address adIndex : list){
				 if ( adIndex.getIP().equals(msgIP)){
					 for (Address newIndex : list){
						 if ( newIndex.getName().equals(msgName))
							 return "ERROR : Name already in use";
					 }
					 adIndex.updateName(msgName);
					 return "OK";
				 }
			 }
			 return "ERROR : IP not found";


 			default:
			// theOutput = "It seems you have made a terrible mistake";
			for (Address adIndex : list){
				System.out.println(adIndex.getName() + " " + adIndex.getIP());
			}
			return "These are not the droids you are looking for";


 		}
		return theOutput;
	}


}
