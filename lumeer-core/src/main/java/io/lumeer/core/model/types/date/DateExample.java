package io.lumeer.core.model.types.date;

public class DateExample {

    private long date;

    public DateExample() {
        this(214321617L);
    }

    public DateExample(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateExample{" +
                "date=" + date +
                '}';
    }
}
