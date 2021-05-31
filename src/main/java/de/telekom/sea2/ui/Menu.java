package de.telekom.sea2.ui;

import de.telekom.sea2.lookup.Salutation;
import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    PersonRepository personRepository = new PersonRepository();
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public Menu() throws SQLException, ClassNotFoundException {
    }

    public void showMenu() {
        System.out.println(ANSI_GREEN +"1 - create new person in Seminar DB" + ANSI_GREEN);
        System.out.println(ANSI_GREEN + "2 - update person in Seminar DB" + ANSI_GREEN);
        System.out.println(ANSI_RED + "3 - remove person from Seminar DB by ID" + ANSI_RED);
        System.out.println(ANSI_RED + "4 - remove all persons from Seminar DB" + ANSI_RED);
        System.out.println(ANSI_BLUE + "5 - get person from Seminar DB by ID" + ANSI_BLUE);
        System.out.println(ANSI_BLUE + "6 - get all persons from Seminar DB" + ANSI_BLUE);
        System.out.println(ANSI_BLACK + "0 - exit" + ANSI_BLACK);
    }
    public String inputLine() {

        String result = "";
        Scanner scanner = new Scanner(System.in);
        result = scanner.nextLine();
        return result;
    }
    public void keepAsking() throws IllegalAccessException, SQLException, ClassNotFoundException {
        String result;
        do {
            showMenu();
            result = checkMenu();
        }
        while (!result.equals("0"));

    }
    public String checkMenu() throws IllegalAccessException, SQLException, ClassNotFoundException {
        String result;
        result = inputLine();
        switch (result) {
            case "1":
                System.out.println("It's 1. Input person.");
                inputPerson();
                break;
            case "2":
                System.out.println("It's 2. Update the person.");
                updatePerson();
                break;
            case "3":
                System.out.println("It's 3. Remove the person. Enter the ID.");
                removePerson();
                break;
            case "4":
                System.out.println("It's 4. Remove all persons.");
                removeAllPerson();
                break;
            case "5":
                System.out.println("It's 5. Get the information about the person. Enter the ID.");
                getPerson();
                break;
            case "6":
                System.out.println("It's 6. Get all persons from the list.");
                getAllPersons();
                break;
            case "0":
                System.out.println("It's 0. Exit.");
                break;
            default:
                System.out.println("Please chose an valid number. To show all options input 1. 0 to exit.");
        }
        return result;
    }

    private String inputMenu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        var input = scanner.nextLine();
        return input;
    }

    private void inputPerson() throws IllegalAccessException, SQLException, ClassNotFoundException {
        Person p = new Person();
        System.out.println("Input id: ");
        long id = Long.parseLong(scanner.nextLine());
        try{p.setID(id);}
        catch (Exception e){
            System.out.println(e.getMessage());}

        System.out.println("Input salutation: ");
        Salutation salutation = Salutation.fromString(scanner.nextLine());
        try{p.setSalutation(salutation);}
        catch (Exception e){
            System.out.println(e.getMessage());}

        System.out.println("Input firstname: ");
        String firstname = scanner.nextLine();
        try{ p.setName(firstname);}
        catch (Exception e){
            System.out.println(e.getMessage());}

        System.out.println("Input lastname: ");
        String surname = scanner.nextLine();
        try{ p.setSurname(surname);}
        catch (Exception e){
            System.out.println(e.getMessage());}
        personRepository.create(p);
    }
    private void updatePerson() throws IllegalAccessException, SQLException, ClassNotFoundException {
        Person person = new Person();
        personRepository.update(person);
    }

    private void getAllPersons () throws SQLException, ClassNotFoundException, IllegalAccessException {
      try{personRepository.getAll();}
        catch (Exception e){
            System.out.println(e.getMessage());}
    }

    private void getPerson () throws SQLException, ClassNotFoundException {
        Person p = new Person();
        System.out.println("Input id to get the person`s info: ");
        long id = Long.parseLong(scanner.nextLine());
        try{ p.setID(id);}
        catch (Exception e){
            System.out.println(e.getMessage());}
         personRepository.get(p);
    }

    private void removePerson () throws SQLException, ClassNotFoundException {
        Person p = new Person();
        System.out.println("Input id to change the person`s info: ");
        long id = Long.parseLong(scanner.nextLine());
        try{ p.setID(id);}
        catch (Exception e){
            System.out.println(e.getMessage());}
        personRepository.delete(p);
    }
    private void removeAllPerson () throws SQLException, ClassNotFoundException {
        personRepository.deleteAll();
    }

}
