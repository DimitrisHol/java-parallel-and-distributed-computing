class SqrtGroupThread extends Thread
{
		private int myId;
    private int numThreads;
    private double [] table;
    private int tableSize;

		private int myStart;
    private int myStop;

	// constructor
	public SqrtGroupThread(int id, int num, double[] array, int size)
	{
                myId = id;
                numThreads = num;
								table = array;
                tableSize = size;

								myStart = myId * (tableSize / numThreads);
                myStop = myStart + (tableSize / numThreads);
                if (myId == (numThreads - 1)) myStop = tableSize;
	}

	// thread code
	public void run()
	{
		for(int i = myStart; i < myStop; i++)
			table[i] = Math.sqrt(table[i]);
	}
}
