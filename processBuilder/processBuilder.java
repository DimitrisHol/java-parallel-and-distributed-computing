public class processBuilder {

	public static void main(String[] args) throws Exception{

    Process p = new ProcessBuilder(args[0], args[1]).start();
    p.waitFor();
	}

}
