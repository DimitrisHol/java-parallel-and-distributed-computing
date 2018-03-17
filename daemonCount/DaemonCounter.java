public class DaemonCounter extends Thread{

  //Fields
  protected int count;
  protected int inc;
  protected int delay;

  //Constructor
  public DaemonCounter (int init , int inc , int delay){
    this.count = init;
    this.inc = inc;
    this.delay = delay;
  }

  // Thread body
  public void run() {             //Still having the run function.
      while (true) {
          System.out.print(count + " ");
          count += inc;
          try {
              sleep(delay);        // You need to do Thread.Sleep.

          } catch (InterruptedException e) {}
      }
  }


  public static void main(String[] args) {

    // We start c1 and c2, and set the as daemons, so when main ends, they shut off as well.
    // c1 can run for 5 times, and c2 can run for 10 times, before main ends.
    DaemonCounter c1 = new DaemonCounter(0 , 1 , 10);
    DaemonCounter c2 = new DaemonCounter(100 , 1 , 5);
    DaemonCounter c3 = new DaemonCounter(200 , 1 , 200  );

    c1.setDaemon(true);
    c1.start();
    c2.run();

    c2.setDaemon(true);
    c2.start();

    // c3.start();
    // c3.interrupt();

    System.out.print("HERE WE GO");
    try{
      sleep(50);
    } catch (InterruptedException e){}
  }
}
