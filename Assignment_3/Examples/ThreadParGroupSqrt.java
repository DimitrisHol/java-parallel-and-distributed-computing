class ThreadParGroupSqrt
{
	public static void main(String[] args)
	{
		// problem size
		int size = 1000;
		// shared data structure initialization   
		double[] a = new double[size];
		for(int i = 0; i < size; i++)
			a[i] = i; 

		// for debugging 
		for(int i = 0; i < size; i++) 
			System.out.println(a[i]);

                //  number of cpus
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("cores = " + cores);

		//  number of threads can be different from the number of cpus 
		int numThreads = cores;
	       
		// get current time
                long start = System.currentTimeMillis();

		// thread creation 
		SqrtGroupThread threads[] = new SqrtGroupThread[numThreads];
		
		// thread execution   
		for(int i = 0; i < numThreads; i++) 
		{
			threads[i] = new SqrtGroupThread(i, numThreads, a, size);
			threads[i].start();
		}

		// wait for threads to terminate            
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
           		} catch (InterruptedException e) {}
		}

		// get current time and calculate elapsed time
             	long elapsedTimeMillis = System.currentTimeMillis()-start;
             	System.out.println("time in ms = "+ elapsedTimeMillis);


             	float elapsedTimeSec = elapsedTimeMillis/size;
	     	System.out.println("time in s =" + elapsedTimeSec);

		// for debugging  
		for(int i = 0; i < size; i++) 
			System.out.println(a[i]);

	}
}