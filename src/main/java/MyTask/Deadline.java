package MyTask;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deadline extends MyTask {

    //protected String by;
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    public String getBy(){
        String timeString = "";
        String Date = by.getDayOfMonth() + " of " + by.getMonth().toString() + " " + by.getYear();
        LocalTime Time = by.toLocalTime();
        //LocalTime Noon =
        if(Time.isAfter(LocalTime.NOON)){
            timeString = (Time.getHour() - 12) + "." + Time.getMinute() + "pm";
        } else {
            timeString = Time.getHour() + "." + Time.getMinute() + "am";
        }
        Date = Date + ", " + timeString;
        return Date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}

