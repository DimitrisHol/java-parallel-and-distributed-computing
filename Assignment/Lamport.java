import java.util.ArrayList;
 

public class Lamport extends Thread{
    
    //Fields
    public int threadId; 
    
    public static int counter = 0;
    public static int numberOfThreads = 5;
    
    private static boolean [] entering = new boolean[numberOfThreads];
        private static int[] numbers = new int[numberOfThreads];
    
    public Lamport(int id ){
        threadId = id;
        
    }

    public void run(){
        
        lamport();
        
    }

    // ΑΡΤΟΠΟΙΕΙΟΥ
    public void lamport(){


        while (counter < 30)    
        {
            
            lock(threadId);
            
            //Enter Critical Section
            
            System.out.print("Counter was " + counter);
            counter ++;
            System.out.println(" ,counter is now : " + counter + " from thread : " + currentThread().getName());

            //Exit Critical Section 

            unlock(threadId);
        }
        
    }
    
    public void lock(int id){
        
        entering[id] = true;
        numbers[id] = max() + 1;
        entering[id] = false;
        
        for (int i = 0; i < numbers.length; i++) {
            
            if (i != id){
                
                while (entering[i]){};
                while (numbers[i] != 0 && ( numbers[id] > numbers[i]  || (numbers[id] == numbers[i] && id > i))){};
            }
        }
    }
    
    public void unlock(int id){
        numbers[id] = 0;
    }
    
    public int max(){
        int max = numbers[0];
        
        for (Integer n :numbers){
            if (n > max){
                max = n;
            }
        }
        
        return max;
        
    }
    
    public static void main(String[] args) {
       
        Lamport[] threads = new Lamport[numberOfThreads];
        
        for (int i = 0; i < threads.length; i++) {
            
            threads[i] = new Lamport(i);
            threads[i].start();
            
        }
        
    }
}
