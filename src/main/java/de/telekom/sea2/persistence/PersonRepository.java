package de.telekom.sea2.persistence;
import de.telekom.sea2.lookup.Salutation;
import de.telekom.sea2.model.Person;
import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

public class PersonRepository {
    private Person[] persons;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    public static final String ANSI_BLUE = "\u001B[34m";
    private Scanner scanner = new Scanner(System.in);
    final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public PersonRepository() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/personrepository", "akhvan", "seapass");
        this.statement = connection.createStatement();
    }

    public void create(Person p) throws SQLException, ClassNotFoundException {
        try {
            final String requestInsert = "INSERT into personen (ID, SALUTATION, NAME, SURNAME) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requestInsert);

            preparedStatement.setLong(1, getNextFreeID());
            preparedStatement.setString(2, p.getSalutation().toString());
            preparedStatement.setString(3, p.getName());
            preparedStatement.setString(4, p.getSurname());

            boolean result;
            result = preparedStatement.execute();
            System.out.println(result);
            preparedStatement.close();
            //         connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showUpdateOption() {
        System.out.println(ANSI_BLUE + "1 - Update the whole information" + ANSI_BLUE );
        System.out.println(ANSI_BLUE + "2 - Update the person`s name" + ANSI_BLUE);
        System.out.println(ANSI_BLUE + "3 - Update the person`s surname" + ANSI_BLUE);
        System.out.println(ANSI_BLUE + "0 - exit to the main menu" + ANSI_BLUE);
    }
    public void update(Person person) throws IllegalAccessException, SQLException, ClassNotFoundException {
        String result;
        do {
            showUpdateOption();
            result = updateOp(person);
        }
        while (!result.equals("0"));

    }

     public  String scannerRes (){
         String result = "";
         Scanner scanner = new Scanner(System.in);
         result = scanner.nextLine();
         return result;
     }

    public String updateOp(Person person) throws SQLException, ClassNotFoundException, IllegalAccessException {
           String result=  scannerRes();
        switch (result) {
            case "1":
                System.out.println("It's 1. Update salutation, name, surname.");
                updateAll(person);
                break;
            case "2":
                System.out.println("It's 2. Update name of the person.");
                updateName(person);
                break;
            case "3":
                System.out.println("It's 3. Update surname of the person.");
                updateSurname(person);
                break;
            case "0":
                System.out.println("It's 0. Exit.");
                break;
            default:
                System.out.println("Please chose an valid number. press 0 to exit.");

        }
        return result;
    }

    public void updateAll(Person person) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Person p = new Person();
        System.out.println("Input id to change the person`s info: ");
        long id = Long.parseLong(scanner.nextLine());
        try{person.setID(id);}
        catch (Exception e){
            System.out.println(e.getMessage());}

        System.out.println("Input new salutation: ");
        Salutation salutation = Salutation.fromString(scanner.nextLine());
        try {person.setSalutation(salutation);}
        catch (Exception e){
            System.out.println(e.getMessage());}

        System.out.println("Input new firstname: ");
        String firstname = scanner.nextLine();
        try {person.setName(firstname);}
        catch (Exception e){
            System.out.println(e.getMessage());}

        System.out.println("Input new lastname: ");
        String surname = scanner.nextLine();
        person.setSurname(surname);
        try{id = person.getID();}
        catch (Exception e){
            System.out.println(e.getMessage());}
        try {
            final String requestUpdate = "UPDATE personen SET salutation = ?, name = ?, surname =? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestUpdate);
            //         preparedStatement.setLong(1, id);
            preparedStatement.setLong(4, person.getID());
            preparedStatement.setString(1, person.getSalutation().toString());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateName (Person person) throws SQLException, ClassNotFoundException {
        System.out.println("Input id to change the person`s info: ");
        long id = Long.parseLong(scanner.nextLine());
        try {person.setID(id);}
        catch (Exception e){
            System.out.println(e.getMessage());}
        System.out.println("Input new firstname: ");
        String firstname = scanner.nextLine();
        try {person.setName(firstname);}
        catch (Exception e){
            System.out.println(e.getMessage());}
        try {id = person.getID();}
        catch (Exception e){
            System.out.println(e.getMessage());}
        try {
            final String requestUpdate = "UPDATE personen SET name = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestUpdate);
            preparedStatement.setLong(2, person.getID());
            preparedStatement.setString(1, person.getName());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSurname (Person person) throws SQLException, ClassNotFoundException {
        System.out.println("Input id to change the person`s info: ");
        long id = Long.parseLong(scanner.nextLine());
        try {person.setID(id);}
        catch (Exception e){
            System.out.println(e.getMessage());}


        System.out.println("Input new lastname: ");
        String surname = scanner.nextLine();
        person.setSurname(surname);
        id = person.getID();
        try {
            final String requestUpdate = "UPDATE personen SET surname = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestUpdate);
            preparedStatement.setLong(2, person.getID());
            preparedStatement.setString(1, person.getSurname());

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

    public boolean get(Person person) throws SQLException, ClassNotFoundException {
        long id = person.getID();
        try {
            final String requestGet = "SELECT * FROM personen WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestGet);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getLong(1));
                System.out.println("Salutation: " + resultSet.getString(2));
                System.out.println("Name: " + resultSet.getString(3));
                System.out.println("Surname: " + resultSet.getString(4));
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Person[] getAll() throws SQLException, ClassNotFoundException, IllegalAccessException {
        String resultSet="SELECT count(ID) from personen";
        int countPersons =0;
        try {
            ResultSet rs = statement.executeQuery(resultSet);
            while(rs.next())
                countPersons=rs.getInt(1);}
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Count of persons are: " + countPersons);
        String result = "select * from personen";
        Person[] persons = new Person[countPersons];
        ResultSet resultSet2 = statement.executeQuery(result);
        int i = 0;
        while (resultSet2.next()) {
            Person person = new Person();
            person.setID(resultSet2.getLong(1));
            person.setSalutation(Salutation.fromString(resultSet2.getString(2)));
            person.setName(resultSet2.getString(3));
            person.setSurname(resultSet2.getString(4));
            persons[i] = person;
            i++;
        }
        for (int j = 0; j < countPersons; j++) {
            System.out.println("ID: " + persons[j].getID());
            System.out.println("Salutation: " + persons[j].getSalutation());
            System.out.println("Name: " + persons[j].getName());
            System.out.println("Surname: " + persons[j].getSurname());
        }
        resultSet2.close();
        return persons;
    }


    private long getNextFreeID() throws SQLException {
        long id = 0;
        String query = "SELECT * FROM personen";
        try (Statement st = connection.createStatement()) {
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getLong(1) > id) {
                    id = resultSet.getLong(1);
                }
            }
        } catch (Exception e) {
            System.out.println("next free ID was not found");
            resultSet.close();
        }
        resultSet.close();
        return ++id;
    }

}