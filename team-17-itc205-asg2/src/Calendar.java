import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {

    private static Calendar calendarObj;
    private static java.util.Calendar currentCalendar;
    private Calendar() {
        currentCalendar = java.util.Calendar.getInstance();
    }

    public static Calendar instance() {
        if (calendarObj == null) {
            calendarObj = new Calendar();
        }
        return calendarObj;
    }

    public void incrementDate(int days) {
        currentCalendar.add(java.util.Calendar.DATE, days);
    }

    public synchronized void setDate(Date date) {
        try {
            currentCalendar.setTime(date);
            currentCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
            currentCalendar.set(java.util.Calendar.MINUTE, 0);
            currentCalendar.set(java.util.Calendar.SECOND, 0);
            currentCalendar.set(java.util.Calendar.MILLISECOND, 0);
        } catch (Exception setDateError) {
            throw new RuntimeException(setDateError);
        }
    }

    public synchronized Date getCurrentDate() {
        try {
            currentCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
            currentCalendar.set(java.util.Calendar.MINUTE, 0);
            currentCalendar.set(java.util.Calendar.SECOND, 0);
            currentCalendar.set(java.util.Calendar.MILLISECOND, 0);
            return currentCalendar.getTime();
        } catch (Exception getDateError) {
            throw new RuntimeException(getDateError);
        }
    }

    public synchronized Date getDueDate(int loanPeriod) {
        Date currentDate = getCurrentDate();
        currentCalendar.add(java.util.Calendar.DATE, loanPeriod);
        Date dueDate = currentCalendar.getTime();
        currentCalendar.setTime(currentDate);
        return dueDate;
    }

    public synchronized long getDaysDifference(Date targetDate) {
        long diffMillis = getCurrentDate().getTime() - targetDate.getTime();
        long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
        return diffDays;
    }
}