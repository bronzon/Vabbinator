package se.olle.vabinator.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Root
public class VabEvent {
    @Attribute(required = false)
    private boolean reported = false;
    @Attribute(required = false)
    private boolean closed = false;
    @Attribute(required = false)
    private boolean sentTestimonial = false;
    @Element
    private Child child;
    @Attribute
    private Date dateOfReport;
    @Attribute
    private long id;
    @ElementList(required = false)
    private List<Date> dates = new ArrayList<Date>();


    public VabEvent(Child child, Date dateOfReport) {
        this.child = child;
        this.dateOfReport = dateOfReport;
    }

    public VabEvent() {
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public boolean isReported() {
        return reported;
    }

    public Child getChild() {
        return child;
    }

    public Date getDateOfReport() {
        return dateOfReport;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VabEvent vabEvent = (VabEvent) o;

        if (id != vabEvent.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }


    public void addDate(Date date) {
        dates.add(date);
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isSentTestimonial() {
        return sentTestimonial;
    }

    public void setSentTestimonial(boolean sentTestimonial) {
        this.sentTestimonial = sentTestimonial;
    }

    public List<Date> getDates() {
        return dates;
    }
}
