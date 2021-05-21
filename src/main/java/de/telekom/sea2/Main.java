package de.telekom.sea2;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        SeminarApp seminarApp = new SeminarApp();
        seminarApp.run(args);
    }
}
