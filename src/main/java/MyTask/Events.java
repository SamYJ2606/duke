package MyTask;

import java.time.LocalDate;
import java.time.LocalTime;

public class Events extends MyTask{

    //protected String by;
    protected LocalDate Date;
    protected LocalTime Start;
    protected LocalTime End;

    public Events(String description, String by){
        super(description);
        String[] byArray = by.split(" ");
        this.Date = LocalDate.parse(byArray[0]);
        byArray = byArray[1].split("-");
        this.Start = LocalTime.parse(byArray[0]);
        this.End = LocalTime.parse(byArray[1]);
    }

    public String getBy(){
        String dateString = Date.getDayOfMonth() + " of " + Date.getMonth().toString() + " " + Date.getYear();
        String startString = " ";
        String endString = " ";
        if(Start.isAfter(LocalTime.NOON)){
            startString = (Start.getHour() - 12) + "." + Start.getMinute() + "pm";
        } else {
            startString = Start.getHour() + "." + Start.getMinute() + "am";
        }
        if(End.isAfter(LocalTime.NOON)){
            endString = (End.getHour() - 12) + "." + End.getMinute() + "pm";
        } else {
            endString = End.getHour() + "." + End.getMinute() + "am";
        }
        dateString = dateString + ", " + startString + "-" + endString;
        return dateString;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}

