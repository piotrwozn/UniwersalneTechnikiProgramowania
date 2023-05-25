package zad1;

import java.sql.*;

class Database {

    private TravelData travelData;
    private String link;


    Database(String link, TravelData travelData) {
        this.travelData = travelData;
        this.link = link;
    }

    void create() {
        try (Connection polonczenie = DriverManager.getConnection(link)) {

            if (polonczenie != null) {
                polonczenie.getMetaData();
            }

            assert polonczenie != null;
            Statement statement = polonczenie.createStatement();
            statement.execute("create table if not exists traveldata (data text not null);");

            PreparedStatement preparedStatement = polonczenie.prepareStatement("insert into traveldata (data) values (?)");

            for (Baza baza : travelData.getBazaLista()) {
                preparedStatement.setString(1, baza.toString());
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void showGui() {
        new GUI(travelData.getBazaLista());
    }
}