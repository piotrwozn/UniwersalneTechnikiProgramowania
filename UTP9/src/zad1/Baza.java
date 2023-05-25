package zad1;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Baza {
    private Locale lokalizacja;
    private String kraj;
    private Date dataOd;
    private Date dataDo;
    private String miejsce;
    private Double cena;
    private String symbol_waluty;


    Baza(Locale lokalizacja, String kraj, Date dataOd, Date dataDo, String miejsce, Double cena, String symbol_waluty) {
        this.lokalizacja = lokalizacja;
        this.kraj = kraj;
        this.dataOd = dataOd;
        this.dataDo = dataDo;
        this.miejsce = miejsce;
        this.cena = cena;
        this.symbol_waluty = symbol_waluty;
    }

    Object[] Lista() {
        return new Object[]{lokalizacja, kraj, dataOd, dataDo, miejsce, cena, symbol_waluty};
    }

    Locale getLokalizacja() {
        return lokalizacja;
    }

    String getKraj() {
        return kraj;
    }

    Date getDataOd() {
        return dataOd;
    }

    Date getDataDo() {
        return dataDo;
    }

    String getMiejsce() {
        return miejsce;
    }

    Double getCena() {
        return cena;
    }

    String getSymbol_waluty() {
        return symbol_waluty;
    }
}