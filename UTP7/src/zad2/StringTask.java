package zad2;

public class StringTask implements Runnable{
    String text;
    int value;
    String result = "";
    TaskState state = TaskState.CREATED;
    boolean done;


    public StringTask(String text, int value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public void run() {
        if(state == TaskState.RUNNING) {
            for (int i = 0; i < value; i++){
                result += text;
                if(i == value - 1) {
                    state = TaskState.READY;
                }
            }
        }
        done = true;
    }

    public TaskState getState() {
        return state;
    }

    public void start() {
        state = TaskState.RUNNING;
        run();
    }

    public boolean isDone() {
        return done;
    }

    public String getResult() {
        return result;
    }

    public void abort() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        state = TaskState.ABORTED;
        done = true;
    }

}
