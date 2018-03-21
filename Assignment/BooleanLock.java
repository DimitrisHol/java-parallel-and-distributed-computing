public class BooleanLock extends Thread{

    static private boolean lock = false;
    static private int counter = 0;


    public BooleanLock (){
    }


    public void run (){

        // Λογικη μεταβλητη
    	booleanLock();

    }
    
    // Mutex : NO 
    // No deadlocks : YES
    // No extra time : YES
    // No starvation : YES
    public void booleanLock (){ //
        while (counter < 20 ){

            while (lock) {};

            lock = true; // Enter critical section
            
            System.out.print("Counter was " + counter);
            counter ++;
            System.out.println(" ,counter is now : " + counter + " from thread : " + currentThread().getName());


            lock = false; // Exit critical section.

        }
    }


    public static void main(String[] args) {



        BooleanLock thread0 = new BooleanLock();
        BooleanLock thread1 = new BooleanLock();


        thread0.setName("0");
        thread1.setName("1");
        thread0.start();
        thread1.start();
        

        
        
    }
}
