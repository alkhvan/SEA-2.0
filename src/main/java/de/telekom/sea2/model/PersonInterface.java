package de.telekom.sea2.model;

import de.telekom.sea2.lookup.Salutation;

public interface PersonInterface {
    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    Salutation getSalutation();

    void setSalutation(Salutation salutation);

    long getID();

    void setID(long ID);
}
