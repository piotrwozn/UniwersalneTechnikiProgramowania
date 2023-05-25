/**
 *
 *  @author Woźnicki Piotr SO0139
 *
 */

package zad1;


public class Main {

  public static void main(String[] args) throws InterruptedException {
    Letters letters = new Letters("ABCD");
    for (Thread t : letters.getThreads()) System.out.println(t.getName());
    letters.runThreads();

    Thread.sleep(5000);

    letters.stopThreads();
    System.out.println("\nProgram skończył działanie");
  }

}
