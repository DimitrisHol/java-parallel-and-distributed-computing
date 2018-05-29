class GroupThread extends Thread {

  private int myId;
  private int numThreads;
  private double [] localSums;
  private long numSteps;

  private long myStart;
  private long myStop;


  //Constructor
  public GroupThread(int threadId , int numThreads, double[] sums , long steps){

    this.myId = threadId;
    this.numThreads = numThreads;
    this.localSums = sums;
    this.numSteps = steps;

    this.myStart = myId *  (numSteps / numThreads);
    this.myStop = myStart + (numSteps / numThreads);
    if (myId == (numThreads -1)) {
      myStop = numSteps;
    }
  }

  //Run
  public void run(){

    long startTime = System.nanoTime();

    double sum = 0.0;
    localSums[myId] = 0.0;


    double step = 1.0 / (double)numSteps;
    //Computation
    for (long i = myStart; i < myStop; i++){
      double x = ((double)i +0.5) * step;
      sum += 1 / (1.0 + x*x);
    }

    localSums[myId] = 4 * sum * step;


    // System.out.printf("thread %d , Response time : %f seconds \n" ,myId , (double)(System.nanoTime() - startTime) / 1000000000);
    }
}
