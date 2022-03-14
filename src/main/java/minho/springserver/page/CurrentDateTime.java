package minho.springserver.page;
import java.time.LocalDate;
import java.time.LocalTime;

/* model의 member는 public 또는 pivate(+getter)로 만들어져야 합니다. */
public class CurrentDateTime {
    public int year;
    public int month;
    public int date;
    public int hour;
    public int minute;
    public int second;

    CurrentDateTime () {
        LocalDate currentDate = LocalDate.now();
        this.year = currentDate.getYear();
        this.month = currentDate.getMonthValue();
        this.date = currentDate.getDayOfMonth();

        LocalTime currentTime = LocalTime.now();
        this.hour = currentTime.getHour();
        this.minute = currentTime.getMinute();
        this.second = currentTime.getSecond();
    }
}
