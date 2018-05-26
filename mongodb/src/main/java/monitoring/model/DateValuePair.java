package monitoring.model;

import java.util.Date;

public class DateValuePair <T> {
    private Date date;
    private T value;

    public DateValuePair(Date date, T value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public T getValue() {
        return value;
    }
}
