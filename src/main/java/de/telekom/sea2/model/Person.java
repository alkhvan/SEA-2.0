package de.telekom.sea2.model;

import de.telekom.sea2.lookup.Salutation;

public class Person implements PersonInterface {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Salutation getSalutation() {
        return salutation;
    }

    @Override
    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }

    @Override
    public long getID() {
        return ID;
    }

    @Override
    public void setID(long ID) {
        this.ID = ID;
    }


}
