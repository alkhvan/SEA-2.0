package de.telekom.sea2.model;

import de.telekom.sea2.lookup.Salutation;

public class Person {
    private String name;
    private String surname;
    private long ID;
    private Salutation salutation;

    public Person() {
    }

    public Person(long ID, Salutation salutation, String name, String surname) {
        this.ID = ID;
        this.salutation = salutation;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Salutation getSalutation() {
        return salutation;
    }

    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }


}
