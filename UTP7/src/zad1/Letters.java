package zad1;

import java.util.ArrayList;

public class Letters {
    static ArrayList<Run> threads = new ArrayList<>();
    static String letters;

    public Letters(String letters) {
        Letters.letters = letters;
    }

    public static void createThreads() {
        char[] chars = letters.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            threads.add(new Run());
            threads.get(i).setName("Thread " + chars[i]);
            threads.get(i).setNameThread(chars[i]);
        }
    }

    public ArrayList<Run> getThreads() {
        createThreads();
        return threads;
    }

    public void runThreads() {
        for (Run thread : threads) {
            thread.start();
        }
    }

    public void stopThreads() {
        for (Run thread : threads) {
            thread.stop();
        }
    }
}
