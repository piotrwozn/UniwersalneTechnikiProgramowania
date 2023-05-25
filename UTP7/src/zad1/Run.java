package zad1;

public class Run extends Thread{
    boolean gate = true;
    char nameThread;
    @Override
    public void run() {
        if(gate) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print(nameThread);
            }
        }
    }

    public void setNameThread(char nameThread) {
        this.nameThread = nameThread;
    }

    public void setGate(boolean gate) {
        this.gate = gate;
    }
}
