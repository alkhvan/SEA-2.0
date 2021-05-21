package de.telekom.sea2.persistence;
import de.telekom.sea2.SeminarApp;
import de.telekom.sea2.lookup.Salutation;
import de.telekom.sea2.model.Person;

import java.sql.Connection;
import java.sql.*;


import static de.telekom.sea2.lookup.Salutation.*;


public class PersonRepository  {
    private Person[] persons;
    private Connection connection;
    private Statement statement;
    final String DRIVER="com.mysql.cj.jdbc.Driver";

    public PersonRepository() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/personrepository", "akhvan", "seapass");
        this.statement = connection.createStatement();
    }

    public void create(Person p)throws SQLException, ClassNotFoundException{
         try{
 //         final String requestSelect = "select * from personen";
          final String requestInsert ="INSERT into personen (ID, SALUTATION, NAME, SURNAME) values (?,?,?,?)";
          PreparedStatement preparedStatement = connection.prepareStatement(requestInsert);

          preparedStatement.setLong(1, p.getID());
          preparedStatement.setString(2, p.getSalutation().toString());
          preparedStatement.setString(3, p.getName());
          preparedStatement.setString(4, p.getSurname());

          boolean result ;
          result = preparedStatement.execute( );
          System.out.println(result);
          preparedStatement.close();
 //         connection.close();
      }
      catch (SQLException e) {
          e.printStackTrace();
      }
    }
    public void update(Person person) throws SQLException, ClassNotFoundException {
        long id = person.getID();
        try {
            final String requestUpdate = "UPDATE personen SET salutation = ?, name = ?, surname =? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestUpdate);
   //         preparedStatement.setLong(1, id);
            preparedStatement.setLong(4, person.getID());
            preparedStatement.setString(1, person.getSalutation().toString());
            preparedStatement.setString(2,person.getName());
            preparedStatement.setString(3,person.getSurname());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean delete(Person person) throws SQLException, ClassNotFoundException {
        long id = person.getID();
        try {
            final String requestDelete = "DELETE from personen where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestDelete);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Person[] deleteAll() throws SQLException, ClassNotFoundException {
        try {
            final String requestDelete = "DELETE from personen";
            PreparedStatement preparedStatement = connection.prepareStatement(requestDelete);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }
    public boolean get (Person person) throws SQLException, ClassNotFoundException {
        long id = person.getID();
        try {
            final String requestGet = "SELECT * FROM personen WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestGet);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery( );
            while(resultSet.next()) {
                System.out.println("ID: "+ resultSet.getLong(1));
                System.out.println("Salutation: "+ resultSet.getString(2));
                System.out.println("Name: "+ resultSet.getString(3));
                System.out.println("Surname: "+ resultSet.getString(4));
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Person[] getAll () throws SQLException, ClassNotFoundException, IllegalAccessException {
        ResultSet resultSet = statement.executeQuery("SELECT ID FROM personen");
        int countPersons =0;
        while (resultSet.next()) {
            countPersons = resultSet.getInt(1);
        }
        System.out.println("Count of persons are: " + countPersons);

        String result = "select * from personen";
        Person[] persons = new Person[countPersons];
        ResultSet resultSet2 = statement.executeQuery(result);
        int i =0;
        while(resultSet2.next()) {
            Person person = new Person();
            person.setID(resultSet2.getLong(1));
            person.setSalutation(Salutation.fromString(resultSet2.getString(2)));
            person.setName(resultSet2.getString(3));
            person.setSurname(resultSet2.getString(4));
            persons[i]=person;
            i++;

        }
        for (int j =0; j<countPersons; j++){
            System.out.println("ID: " + persons[j].getID());
            System.out.println("Salutation: " + persons[j].getSalutation());
            System.out.println("Name: " + persons[j].getName());
            System.out.println("Surname: " + persons[j].getSurname());
        }
        return persons;
    }
}