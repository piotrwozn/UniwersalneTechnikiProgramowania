package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TravelData {
    private File dataDir;
    private ResourceBundle messages;

    public TravelData(File dataDir) {
        this.dataDir = dataDir;
    }

    public List<String> getOffersDescriptionsList(String loc, String dateFormat) throws FileNotFoundException, ParseException {
        // ustawienie odpowiedniej lokalizacji
        Locale locale = new Locale(loc.split("_")[0], loc.split("_")[1]);
        messages = ResourceBundle.getBundle("MessagesBundle", locale);
        // przygotowanie listy opisów ofert
        List<String> offers = new ArrayList<>();
        // odczytanie plików z ofertami
        File[] offerFiles = dataDir.listFiles((dir, name) -> name.endsWith(".txt"));
        for (File offerFile : offerFiles) {
            Scanner scanner = new Scanner(offerFile);
            while (scanner.hasNextLine()) {
                String[] offerParts = scanner.nextLine().split("\t");
                // przygotowanie opisu oferty
                String location = offerParts[0];
                String country = offerParts[1];
                String departureDate = new SimpleDateFormat(dateFormat).format(new SimpleDateFormat("yyyy-MM-dd").parse(offerParts[2]));
                String returnDate = new SimpleDateFormat(dateFormat).format(new SimpleDateFormat("yyyy-MM-dd").parse(offerParts[3]));
                String place = messages.getString(offerParts[4]);
                String price = offerParts[5] + " " + offerParts[6];
                // dodanie opisu oferty do listy
                offers.add(String.format("%s (%s): %s - %s (%s), %s", country, location, departureDate, returnDate, place, price));
            }
            scanner.close();
        }
        return offers;
    }
}
