package se.olle.vabinator.domain.settings;

import se.olle.vabinator.domain.Person;

public class PersonalSettings {

    public static final PersonalSettings DEFAULT_PERSONAL_SETTINGS = new PersonalSettings(new Person("pnr", "elliot"), Person.EMPTY_PERSON);

    public PersonalSettings(Person child, Person parent) {
        this.child = child;
        this.parent = parent;
    }

    private Person child;
    private Person parent;

    public Person getChild() {
        return child;
    }

    public void setChild(Person child) {
        this.child = child;
    }

    public Person getParent() {
        return parent;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }

}
