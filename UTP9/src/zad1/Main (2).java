/**
 *
 *  @author Woźnicki Piotr SO0139
 *
 */

package zad1;


import java.io.;
import java.text.ParseException;
import java.util.;

public class Main {
  public static void main(String[] args) throws FileNotFoundException, ParseException {
// Tworzenie obiektu File reprezentującego katalog "data"
    File dataDir = new File("data");
// Tworzenie obiektu TravelData za pomocą katalogu "data"
    TravelData travelData = new TravelData(dataDir);
// Określenie formatu daty jako "yyyy-MM-dd"
    String dateFormat = "yyyy-MM-dd";
// Pętla przechodząca przez listę lokalizacji "pl_PL" i "en_GB"
    for (String locale : Arrays.asList("pl_PL", "en_GB")) {
// Ustawienie lokalizacji na podaną wartość
      Locale.setDefault(new Locale(locale));
// Pobranie listy opisów ofert dla danej lokalizacji i formatu daty
      List<String> offers = travelData.getOffersDescriptionsList(locale, dateFormat);
// Wypisanie opisów ofert na konsoli
      for (String offer : offers) {
        System.out.println(offer);
      }
    }
// Tworzenie obiektu Database
    Database database = new Database();
// Utworzenie bazy danych i wpisanie do niej wszystkich ofert
    database.createDb(travelData);
// Otwarcie GUI z tabelą pokazującą wczytane oferty
    database.openGui();
  }
}