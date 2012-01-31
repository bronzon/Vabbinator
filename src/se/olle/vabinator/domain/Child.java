package se.olle.vabinator.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Child {
    @Attribute(required = true)
    private String pnr;
    @Attribute(required = true)
    private String name;

    public Child(String pnr, String name) {
        this.pnr = pnr;
        this.name = name;
    }

    public Child() {

    }

    public String getPnr() {
        return pnr;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Child{" +
                "pnr='" + pnr + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
