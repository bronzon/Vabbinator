package se.olle.vabinator.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;

@Root
public class Person {
    @Transient
    public static final Person EMPTY_PERSON = new Person("", "");
    @Element(required = false)
    private String pnr;
    @Element(required = false)
    private String name;

    public Person(String pnr, String name) {
        this.pnr = pnr;
        this.name = name;
    }

    public Person() {
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pnr='" + pnr + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
