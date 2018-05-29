public class PiComputingThreads{

public static void main(String[] args) {

  long numSteps = 0;
  int numThreads = 0;

  //Parsing numSteps
  if (args.length != 1) {
    System.out.println("arguments:  number_of_steps");
    System.exit(1);
  }
  try {
    numSteps = Long.parseLong(args[0]);


  } catch (NumberFormatException e) {
    System.out.println("argument "+ args[0] +" must be long int");
    System.exit(1);
  }

  // Get the number of available cores.
  int cores = Runtime.getRuntime().availableProcessors();

  // Set the number of threads to run.
  numThreads = cores;

  //Cores are defined by user
  // System.out.println("cores = " + numThreads);

  //Array of numSteps to store results coming from Threads.
  double[] localSums = new double[numThreads];

  //Make an array of type groupThread as many as the number of Threads.
  GroupThread groups[] = new GroupThread[numThreads];

  //Start counting time.
  long startTime = System.nanoTime();

  //Make the threads, and start them.
  for (int i=0; i < numThreads; i++){
    groups[i] = new GroupThread(i , numThreads, localSums , numSteps);
    groups[i].start();
  }

    //Wait for all of them to finish.
  for(int i = 0; i < numThreads; i++) {
    try {
      groups[i].join();
            } catch (InterruptedException e) {}
  }

  double pi = 0.0;
  for (int i=0; i< numThreads; i++){
    pi += localSums[i];
  }


  System.out.printf("pararell program results with %d steps\n", numSteps);
  System.out.printf("computed pi = %22.20f\n" , pi);
  System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));


  System.out.printf("Response time : %f seconds \n" , (double)(System.nanoTime() - startTime) / 1000000000);

  }
}
