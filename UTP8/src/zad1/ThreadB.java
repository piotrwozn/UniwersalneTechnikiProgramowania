package zad1;

import java.util.ArrayList;

public class ThreadB extends Thread {
    static int i = 1;
    static ArrayList<Towar> towars = new ArrayList<>(0);
    static int size = 0;
    static int l = 0;
    static double wagalaczna = 0;

    public static synchronized int getI() {
        return i;
    }

    @Override
    public synchronized void run() {
        towars = Start.getTowars();
        int n = 0;
        while (n == 0) {
            towars = Start.getTowars();
            if (i < ThreadA.getI()) {
                if (size < towars.size()) {
                    if (towars.size() % 100 == 0) {
                        int number = 100 * i;
                        System.out.println("policzono wage " + number);
                        i++;
                        size = towars.size();
                        for (int j = l; j < towars.size(); j++) {
                            wagalaczna += towars.get(j).getWaga();
                        }
                        l += 100;
                    }
                }
            } else if (towars.size() == Start.getIloscTowarow()) {
                for (int j = l; j < towars.size(); j++) {
                    wagalaczna += towars.get(j).getWaga();
                }
                System.out.println(wagalaczna);
                n = 1;
            }
        }
        Start.stopThreadB();
    }

}
