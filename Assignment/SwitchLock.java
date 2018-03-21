
public class SwitchLock extends Thread{
	
    static private int token = 0;
    static private int counter = 0;
    
    
    
    
    public void run(){
    	
    	//ΕΝΑΛΛΑΓΗ
    	switchLock();
    }
	
    // Mutex : YES 
    // No deadlocks : Yes, alla an kapoio termatisei rip (spaei h alysida).
    // No extra time : NO, austhrh seira enallaghs
    // No starvation : YES
    public void switchLock(){
    	
        while (counter < 20){
        	
        	while ( token != Integer.parseInt(currentThread().getName()) ){};	// While token is different that the ThreadID, wait.
        	//Enter Critical Section
        	
            System.out.print("Counter was " + counter);
            counter ++;
            System.out.println(" ,counter is now : " + counter + " from thread : " + currentThread().getName());
            
            //Exit Critical Section
            
            token = (token + 1) % 3;
            
            
        }
    }
	
	
    public static void main(String[] args) {



        SwitchLock thread0 = new SwitchLock();
        SwitchLock thread1 = new SwitchLock();
        SwitchLock thread2 = new SwitchLock();

        thread0.setName("0");
        thread1.setName("1");
        thread2.setName("2");
        thread0.start();
        thread1.start();
        thread2.start();

        
    }

}
