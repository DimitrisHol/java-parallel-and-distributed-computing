public class Address {

      private String IP;
      private String name;


      public Address (String IP , String Name){

         this.IP = IP;
         this.name = Name;
      }

		 	public String getIP(){
				return IP;
			}
			public String getName(){
				return name;
			}
      public void updateName(String newName){
        this.name = newName;
      }

   }
