package zad1;

public class Towar {
    int id_towaru;
    double waga;

    public Towar(int id_towaru, double waga) {
        this.id_towaru = id_towaru;
        this.waga = waga;
    }

    public synchronized double getWaga() {
        return waga;
    }
}
