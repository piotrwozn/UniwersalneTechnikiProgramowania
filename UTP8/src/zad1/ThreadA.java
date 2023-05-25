package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreadA extends Thread {
    static ArrayList<Towar> towars = new ArrayList<>();
    static int i = 2;
    File file;

    public static synchronized int getI() {
        return i;
    }

    @Override
    public synchronized void run() {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (ThreadB.getI() == i - 1 || ThreadB.getI() == i) {
                    towars.add(new Towar(Integer.parseInt(scanner.next()), Double.parseDouble(scanner.next())));
                    Start.setTowars(towars);
                    if (towars.size() % 200 == 0 && towars.size() != 0) {
                        int number = i * 100;
                        System.out.println("utworzono " + number + " obiektów");
                        i += 2;
                    }
                }
            }
            Start.setIloscTowarow(towars.size());
            Start.stopThreadA();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFile(File file) {
        this.file = file;
    }
}
