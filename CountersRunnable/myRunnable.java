public class myRunnable implements Runnable{        // this time implementing Runnable.

    //Fields

    protected int count;
    protected int inc;
    protected int delay;

    //Constructor

    public myRunnable(int init, int inc , int delay){
        this.count = init;
        this.inc = inc;
        this.delay = delay;
    }

    public void run() {             //Still having the run function.
        while (true) {

            if (count < -20 || count > 20) {
                break;
            }
            System.out.print(count + " ");
            count += inc;
            try {
                Thread.sleep(delay);        // You need to do Thread.Sleep.

            } catch (InterruptedException e) {}
        }
    }
     public static void main(String[] args){

         new Thread(new myRunnable(0 , 1 , 33)).start();        //new Thread taking as parameter the Runnable.
         new Thread(new myRunnable(0 , -1 , 100)).start();



        }
}
