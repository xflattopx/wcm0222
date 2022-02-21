//  Checkout requires the following information to be provided:
//  Tool code: See tool table above
//  Rental day count: The number of days for which the customer wants to rent the tool. (e.g. 4
//  days)
//  Discount percent: As a whole number, 0-100 (e.g. 20 = 20%)
//  Check out date

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class Checkout {

    Scanner scanner = new Scanner(System.in);
    List<Tool> tools;
    double discount;
    String dateOfCheckout; // The checkout date.
    double total = 0; // Calculates total at checkout

    public Checkout() {

    }

    public Checkout(List<Tool> tools) {
        this.tools = tools;
    }

    // transaction() is the main transaction done at checkout between
    // the customer and cashier.
    public void transaction() {
        int input;
        optionPanel();
        input = scanner.nextInt();
        while (input != 9) {
            if (input == 1) {
                providedInformation();
                optionPanel();
                input = scanner.nextInt();
            } else if (input == 2) {
                optionPanel();
                input = scanner.nextInt();
            } else if (input == 3) {
                optionPanel();
                System.out.println("Running total: " + total);
                input = scanner.nextInt();

            } else {
                System.out.println("Invalid Input");
                optionPanel();
                input = scanner.nextInt();
            }
        }
    }

    // optionPanel() is the initial interphase to navigate through
    // checkout.
    public void optionPanel() {
        System.out.println("Welcome To Rent-A-Tool");
        System.out.println("======================");
        System.out.println("To Checkout a Tool - 1");
        System.out.println("Return to Menu     - 2");
        System.out.println("To View Total      - 3");
        System.out.println("Exit               - 9");
        System.out.println("======================");

    }

    // ProvidedInformation() Collects the following information:
    // Tool code, rental day count, discount percent, checkout date
    @Deprecated
    public void providedInformation() {
        // Date currentDate = new Date();
        String code;
        int month;
        int day;
        int year;
        int days;
        int discount;
        int temp;

        System.out.println("Please provide tool code: ");
        code = scanner.next();
        System.out.println("How many days would you like to rent: ");
        days = scanner.nextInt();
        System.out.println("Percentage of discount [between 0 - 100]: ");
        // Make sure our percentage is in bounds
        temp = scanner.nextInt();
        if (temp > -1 || temp < 101) {
            discount = temp;
        } else {
            System.out.println("Invalid discount.  Returning to menu.");
            return;
        }

        System.out.println("Month of checkout [1-12]");
        month = scanner.nextInt();
        System.out.println("Day of checkout [1-31]");
        day = scanner.nextInt();
        System.out.println("Year of checkout [1900 - 2022]");
        year = scanner.nextInt();
        total += calculateItemTotal(code, LocalDate.of(year, month, day), days, discount);

    }

    // calculateItemTotal() calculates the total of a tool given the following:
    // Tool Code: A string uniquly identify the item.
    // Checkout Date: A date in which the customer intends on checking item out.
    // Rental Days: The number of days the customer intends on renting tool.
    // Discount: A discount in which applies to the total

    public double calculateItemTotal(String toolCode, LocalDate checkoutDate, int rentalDays, double discount) {
        double disPercentage = discount / 100; // Taken off the total after checkout
        // Date dueDate;
        double discountAmount = 0.0;
        double itemTotal = 0.00;
        Tool tool = new Tool();
        int chargeableDays = 0;
        boolean foundItem = false;

        // If discount is out of bounds, return 0;
        if ((discount < 0 || discount > 100)) {
            System.out.println("Invalid discount.");
            return itemTotal;
        }

        // determine which tool we're using (can be improved with key value pair)
        for (int i = 0; i < tools.size(); i++) {
            // We found the tool.
            if (tools.get(i).toolCode.equals(toolCode)) {
                tool = tools.get(i);
                foundItem = true;
                break;
            }
        }

        // If we didn't find the item.
        if (foundItem == false) {
            System.out.println("Invalid Tool Code");
            return itemTotal;
        }

        // Calculates the chargeable days.
        chargeableDays = chargeableDays(checkoutDate, tool, rentalDays);

        // Make sure our percentage is in bounds
        if (disPercentage > -1 || disPercentage < 101)
            discountAmount = (tool.type.price * chargeableDays) * disPercentage;
        else
            return itemTotal;

        itemTotal = (chargeableDays * tool.type.price) - discountAmount;

        System.out.println("item Price: " + tool.type.price + " Discount: " + discountAmount + " Total: " +
                itemTotal);

        return itemTotal;
    }

    // chargeableDays(checkoutDate, tool, checkoutDays) Calculates how many
    // chargeable days
    // to charge the customer given the following:
    // date: The day in which the customer checked out the item.
    // tool: The tool they're checking out.
    // checkoutDays: How many days is the customer checking it out.
    public int chargeableDays(LocalDate date, Tool tool, int checkoutDays) {
        int daysCharged = 0;
        boolean chargeWeek = tool.type.weekdayCharge;
        boolean chargeWeekend = tool.type.weekendCharge;
        boolean chargeHoliday = tool.type.holidayCharge;
        boolean isHoliday = false;
        LocalDate theDate = LocalDate.of(date.getYear(), date.getMonthValue(),
                date.getDayOfMonth());
        System.out.println("The checkout date: " + theDate);
        // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i != checkoutDays; i++) {
            LocalDate tempDate = theDate.plusDays(i);
            DayOfWeek day = tempDate.getDayOfWeek();

            // If the item charges holiday handle special case.

            Holiday holiday = new Holiday(tempDate);
            isHoliday = holiday.isHoliday();

            // Do not iterate through the weeks if it's a holiday
            if (isHoliday) {
                // If we charge for a holiday, increment daysCharged.
                if (chargeHoliday) {
                    daysCharged++;
                }
            } else {
                System.out.println("Non-Holiday Dates: " + tempDate + " Day: " + day);
                switch (day) {

                    case SUNDAY:
                        if (chargeWeekend)
                            daysCharged++;
                        break;
                    case MONDAY:
                        if (chargeWeek)
                            daysCharged++;
                        break;
                    case TUESDAY:
                        if (chargeWeek)
                            daysCharged++;
                        break;
                    case WEDNESDAY:
                        if (chargeWeek)
                            daysCharged++;
                        break;
                    case THURSDAY:
                        if (chargeWeek)
                            daysCharged++;
                        break;
                    case FRIDAY:
                        if (chargeWeek)
                            daysCharged++;
                        break;
                    default: // Saturday
                        if (chargeWeekend)
                            daysCharged++;
                        break;
                }
            }
        }

        System.out.println("chargeableDays: " + daysCharged);
        System.out.println("Due Date: " + theDate);

        // String output = sdf.format(calendar.getTime());
        // System.out.println(output);

        return daysCharged;
    }

}
