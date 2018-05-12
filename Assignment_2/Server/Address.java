public class Address {

      private String IP;
      private String name;


      public Address (String IP , String Name){

         this.IP = IP;
         this.name = Name;
      }

		 	public synchronized String getIP(){
				return IP;
			}
			public synchronized String getName(){
				return name;
			}
      public synchronized void updateName(String newName){
        this.name = newName;
      }

   }
