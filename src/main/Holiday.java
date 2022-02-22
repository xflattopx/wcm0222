// Holiday is Independence day or Labor day
// We also validate if the holiday falls on a given day.
// We count observed holidays as actual holidays.
// Example: July 4th falls on Saturday, holiday is Fri, July 3rd.

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Holiday {

    LocalDate date;

    Holiday(LocalDate date) {
        this.date = date;
    }

    // independenceDay() returns the date in which
    // independence day falls on.
    // theYear: The year in which we want the holiday.
    public LocalDate independenceDay(int theYear) {
        int nMonth = 7; // July
        LocalDate julyDate = LocalDate.of(theYear, nMonth, 4);
        DayOfWeek day = julyDate.getDayOfWeek();
        switch (day) {
            case SUNDAY: // Sunday
                return LocalDate.of(theYear, nMonth, 5);
            case SATURDAY: // Saturday
                return LocalDate.of(theYear, nMonth, 3);
            default: // Weekday
                return LocalDate.of(theYear, nMonth, 4);
        }
    }

    // LocalDate(theYear) returns the date in which
    // labor day falls on.
    // theYear: The year in which we want the holiday.
    public LocalDate laborDay(int theYear) {
        // The first Monday in September

        int nMonth = 9; // September
        LocalDate octoberDate = LocalDate.of(theYear, 8, 31);
        DayOfWeek day = octoberDate.getDayOfWeek();
        switch (day) {
            case SUNDAY: // Sunday
                return LocalDate.of(theYear, nMonth, 1);
            case MONDAY: // Monday
                return LocalDate.of(theYear, nMonth, 7);
            case TUESDAY: // Tuesday
                return LocalDate.of(theYear, nMonth, 6);
            case WEDNESDAY: // Wednesday
                return LocalDate.of(theYear, nMonth, 5);
            case THURSDAY: // Thursday
                return LocalDate.of(theYear, nMonth, 4);
            case FRIDAY: // Friday
                return LocalDate.of(theYear, nMonth, 3);
            default: // Saturday
                return LocalDate.of(theYear, nMonth, 2);

        }
    }

    // isHoliday() Determines if the date is a holiday or not.
    public boolean isHoliday() {
        LocalDate independenceDay = independenceDay(date.getYear());
        LocalDate laborDay = laborDay(date.getYear());
        // System.out.println("independence day: " + independenceDay + " labor day: " +
        // laborDay + " date: " + date);
        if (date.equals(independenceDay) || date.equals(laborDay))
            return true;
        return false;
    }
}