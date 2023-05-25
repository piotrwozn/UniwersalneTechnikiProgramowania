import zad1.TravelData;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database {
    private TravelData travelData;
    private JFrame frame;
    private JTable table;
    private ResourceBundle messages;

    public Database(TravelData travelData) {
        this.travelData = travelData;
    }

    public void createDb() throws Exception {
        // tutaj należy umieścić kod tworzący bazę danych oraz wprowadzający do niej dane z plików (kod zależy od używanego silnika bazy danych)
        // poniższy kod jest jedynie przykładem, nie jest kompatybilny z żadnym silnikiem bazy danych
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/travel_offers";
        String user = "user";
        String password = "password";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        String createTableSql = "CREATE TABLE offers (location VARCHAR(10), country VARCHAR(50), departure_date DATE, return_date DATE, place VARCHAR(50), price DECIMAL(10,2), currency VARCHAR(3))";
        stmt.executeUpdate(createTableSql);
        File[] offerFiles = dataDir.listFiles((dir, name) -> name.endsWith(".txt"));
        for (File offerFile : offerFiles) {
            Scanner scanner = new Scanner(offerFile);
            while (scanner.hasNextLine()) {
                String[] offerParts = scanner.nextLine().split("\t");
                String location = offerParts[0];
                String country = offerParts[1];
                String departureDate = offerParts[2];
                String returnDate = offerParts[3];
                String place = offerParts[4];
                String price = offerParts[5];
                String currency = offerParts[6];
                String insertSql = String.format("INSERT INTO offers (location, country, departure_date, return_date, place, price, currency) VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s')", location, country, departureDate, returnDate, place, price, currency);
                stmt.executeUpdate(insertSql);
            }
            scanner.close();
        }
        stmt.close();
        conn.close();
    }

    public void openGui(String loc) throws Exception {
        // ustawienie odpowiedniej lokalizacji
        Locale locale = new Locale
