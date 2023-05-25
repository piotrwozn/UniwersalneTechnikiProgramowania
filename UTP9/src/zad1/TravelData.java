package zad1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    private List<Baza> bazaLista;
    private Properties slownik;

    public TravelData(File plik) {

        bazaLista = new ArrayList<>();
        SimpleDateFormat formatDaty = new SimpleDateFormat("yyyy-MM-dd");
        Arrays.stream(Objects.requireNonNull(plik.listFiles())).forEach(file -> {

            try {
                Files.lines(Paths.get(file.getPath())).forEach(line -> {

                    String[] dane = line.split("\t");
                    int x = 0;

                    Locale lokal = Locale.forLanguageTag(dane[x++].replace("_", "-"));
                    NumberFormat formatLiczb = NumberFormat.getInstance(lokal);

                    try {
                        Baza baza = new Baza(
                                lokal,
                                dane[x++],
                                formatDaty.parse(dane[x++]),
                                formatDaty.parse(dane[x++]),
                                dane[x++],
                                formatLiczb.parse(dane[x++]).doubleValue(),
                                dane[x]
                        );

                        bazaLista.add(baza);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

            try (InputStream input = new FileInputStream("dictionary.properties")) {
                slownik = new Properties();
                slownik.load(input);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    List<String> getOffersDescriptionsList(String lokal2, String formatDaty2) {
        List<String> lista = new ArrayList<>();

        Locale lokal3 = Locale.forLanguageTag(lokal2.replace("_", "-"));
        NumberFormat formatLiczb2 = NumberFormat.getInstance(lokal3);
        SimpleDateFormat formatDaty3 = new SimpleDateFormat(formatDaty2);

        bazaLista.forEach(d -> {
            StringBuilder bob_budowniczy = new StringBuilder();

            String kraj = getMessage(lokal3, d.getKraj());
            bob_budowniczy.append(kraj).append(" ");
            bob_budowniczy.append(formatDaty3.format(d.getDataOd())).append(" ");
            bob_budowniczy.append(formatDaty3.format(d.getDataDo())).append(" ");
            bob_budowniczy.append(getMessage(lokal3, d.getMiejsce())).append(" ");
            bob_budowniczy.append(formatLiczb2.format(d.getCena())).append(" ");
            bob_budowniczy.append(d.getSymbol_waluty());

            lista.add(bob_budowniczy.toString());
        });

        return lista;
    }

    List<Baza> getBazaLista() {
        return bazaLista;
    }

    private String getMessage(Locale locale, String key) {
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle",locale, new ResourceBundleControl());
        return messages.getString(key);
    }
}