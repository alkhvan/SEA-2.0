package de.telekom.sea2;

import de.telekom.sea2.lookup.Salutation;
import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonRepository;
import de.telekom.sea2.ui.Menu;

import javax.print.attribute.standard.Media;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static de.telekom.sea2.lookup.Salutation.*;
import static java.sql.DriverManager.getConnection;

public class SeminarApp {
    final String DRIVER="com.mysql.cj.jdbc.Driver";
    final String URL="jdbc:mysql://localhost:3306/personrepository?user=akhvan&password=seapass";
    Connection connection;
    public void startConnection() throws SQLException {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    };
    public void run(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Menu menu =new Menu();
        try {
            menu.keepAsking();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.close();
        }

    }
}
