public class Counter extends Thread{

  //Fields
  protected int count;
  protected int inc;
  protected int delay;

  //Constructor
  public Counter (int init , int inc , int delay){
    this.count = init;
    this.inc = inc;
    this.delay = delay;
  }

  // Thread body
  public void run(){
    for (;;) { // while (true)
      System.out.print(count + " ");
      count += inc;
      try {
        sleep(delay);
    }
    catch (InterruptedException e){}
    }
  }


  public static void main(String[] args) {

    // Starting point , Increment , delay.
    new Counter(0 , 1, 33).start();
    new Counter(0 , -1, 100).start();
  }
}
