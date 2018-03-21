
public class Petterson extends Thread {
    
    private static int counter = 0;
    
    private static boolean [] lock = new boolean[2];
    private static int turn = 0;
    
    
    public void run (){
        petterson();
    }
    
    // Mutex : Yes
    // No deadlocks : Yes
    // No extra time : Yes 
    // No starvation : Yes
    public void petterson(){
        
        while (counter < 20) {
            
            if (Integer.parseInt(currentThread().getName()) == 0){  // Thread 0 
                
                // while (counter < 20){

                    lock[0] = true;
                    turn = 1;
                    while (lock[1] && (turn == 1)) {};
                    
                    //Enter Critical Section
                    
                    System.out.print("Counter was " + counter);
                    counter ++;
                    System.out.println(" ,counter is now : " + counter + " from thread : " + currentThread().getName());
                    
                    //Exit Critical Section
                    
                    lock[0] = false;
                // }
            }
            else{                                                   // Thread 1

                // while (counter < 20){

                    lock[1] = true;
                    turn = 0;
                    while (lock[0] && (turn == 0)) {};
                    
                    //Enter Critical Section
                    
                    System.out.print("Counter was " + counter);
                    counter ++;
                    System.out.println(" ,counter is now : " + counter + " from thread : " + currentThread().getName());
                    
                    //Exit Critical Section
                    
                    lock[1] = false;
                // }   
            }
            
        }    
        
        
    }
        public static void main(String[] args) {
                
            lock[0] = false;
            lock[1] = false;
            


            Petterson thread0 = new Petterson();
            Petterson thread1 = new Petterson();


            thread0.setName("0");
            thread1.setName("1");
            thread0.start();
            thread1.start();
            
        }

}