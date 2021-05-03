package common.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime implements Serializable {

    private String time;
    private int year;
    private int month;
    private int day;

    public DateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.time = formatter.format(date);
        String help = this.time.substring(0,10);
        String[] helps = help.split("/");
        this.day = Integer.parseInt(helps[0]);
        this.month = Integer.parseInt(helps[1]);
        this.year = Integer.parseInt(helps[2]);
    }

    public DateTime(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getTimestamp() {
        return time;
    }

    public String getSortableDate() {
        return String.format("%d-%d-%d", year,month,day);
    }

    @Override
    public String toString() {
        return getTimestamp();
    }

    public static String getToday(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formatter.format(date);
    }

    public static int yearsBetween(DateTime date)
    {
        DateTime dateTime = new DateTime();

        int yearsDifference = dateTime.year - date.year;
        if (dateTime.month > date.month)
            {
                return yearsDifference;
            }
        else
            {
                if (dateTime.day > date.day)
                {
                    return yearsDifference;
                }
                return (yearsDifference - 1);
            }
    }


}
