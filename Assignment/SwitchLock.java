
public class SwitchLock extends Thread{
	
    static private int token = 0;
    static private int counter = 0;
    
    
    
    
    public void run(){
    	
    	
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
            
            token = (token + 1) % 2;
            
            
        }
    }
	
	
    public static void main(String[] args) {



        ThreadsExample thread0 = new ThreadsExample();
        ThreadsExample thread1 = new ThreadsExample();


        thread0.setName("0");
        thread1.setName("1");
        thread0.start();
        thread1.start();
        

        
    }

}
