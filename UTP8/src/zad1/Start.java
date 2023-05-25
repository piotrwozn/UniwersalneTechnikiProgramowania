package zad1;

import java.io.File;
import java.util.ArrayList;

public class Start {
    static String file;
    static ThreadA threadA = new ThreadA();
    static ThreadB threadB = new ThreadB();
    static ArrayList<Towar> towars = new ArrayList<>();
    static int iloscTowarow;


    public static void startProgram() {
        threadB.start();
        File file1 = new File(file);
        threadA.setFile(file1);
        threadA.start();
    }

    public static void stopThreadA() {
        threadA.interrupt();
    }


    public static void stopThreadB() {
        threadB.interrupt();
    }

    public static void setFile(String file) {
        Start.file = file;
    }

    public static synchronized ArrayList<Towar> getTowars() {
        return towars;
    }

    public static synchronized void setTowars(ArrayList<Towar> towars) {
        Start.towars = towars;
    }

    public static synchronized int getIloscTowarow() {
        return iloscTowarow;
    }

    public static synchronized void setIloscTowarow(int iloscTowarow) {
        Start.iloscTowarow = iloscTowarow;
    }
}

