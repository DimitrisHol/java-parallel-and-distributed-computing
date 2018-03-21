class forkExecWait{
  public static void main(String[] args) throws Exception{

    Runtime.getRuntime().exec(args[0]).waitFor();
  }
}
