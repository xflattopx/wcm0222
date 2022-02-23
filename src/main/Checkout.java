//  Checkout requires the following information to be provided:
//  Tool code: See tool table above
//  Rental day count: The number of days for which the customer wants to rent the tool. (e.g. 4
//  days)
//  Discount percent: As a whole number, 0-100 (e.g. 20 = 20%)
//  Check out date

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Checkout {

    Scanner scanner = new Scanner(System.in);
    List<RentalAgreement> rentalAgreements = new ArrayList<RentalAgreement>(); // Store all our rental agreements
    List<Tool> tools;
    DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00"); // Formatting currency
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/uuuu"); // Formatting dates
    double discount;
    String dateOfCheckout; // The checkout date.
    double total = 0.00; // Calculates total at checkout

    public Checkout() {

    }

    public Checkout(List<Tool> tools) {
        this.tools = tools;
    }

    // checkout() is the main transaction done at checkout between
    // the customer and cashier.
    public void checkout() {
        int input;

        // The checkout handler.
        optionPanel();
        input = scanner.nextInt();
        while (input != 9) {
            if (input == 1) {
                providedInformation();
                pressEnterToContinue();
                optionPanel();
                input = scanner.nextInt();
            } else if (input == 2) {
                showRentalAgreement(rentalAgreements);
                optionPanel();
                input = scanner.nextInt();
            } else {
                System.out.println("Invalid Input...");
                pressEnterToContinue();
                optionPanel();
                input = scanner.nextInt();
            }
        }
    }

    // optionPanel() is the initial interphase to navigate through
    // checkout.
    public void optionPanel() {
        System.out.println("_______________________________");
        System.out.println("|___Welcome To Rent-A-Tool____|");
        System.out.println("|_____________________________|");
        System.out.println("| Checkout              - 1   |");
        System.out.println("| View Rental Agreement - 2   |");
        System.out.println("| Exit                  - 9   |");
        System.out.println("|_____________________________|");
        if (total > 0)
            System.out.format("| Total: $%s                 \n", decimalFormatter.format(total));
        System.out.println("| Enter Value: ");

    }

    // providedInformation() Collects the following information:
    // Tool code, rental day count, discount percent, checkout date
    public void providedInformation() {
        String code;
        int month;
        int day;
        int year;
        int days;
        int discount = 0;
        int temp;

        System.out.print("Please provide tool code: ");
        code = scanner.next();
        System.out.print("How many days would you like to rent: ");
        days = scanner.nextInt();
        System.out.print("Percentage of discount [between 0 - 100]: ");
        // Make sure our percentage is in bounds
        temp = scanner.nextInt();
        try {
            if (temp > -1 || temp < 101)
                discount = temp;
            else {
                throw new IllegalArgumentException("Value: " + temp + " isn't beteen[ 0 - 100 ]");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Value: " + temp + " isn't beteen[ 0 - 100 ]");
        }
        System.out.print("Month of checkout [1-12]: ");
        month = scanner.nextInt();
        System.out.print("Day of checkout [1-31]: ");
        day = scanner.nextInt();
        System.out.print("Year of checkout [1900 - 2022]: ");
        year = scanner.nextInt();
        total += Math.round(calculateItemTotal(code, LocalDate.of(year, month, day), days, discount) * 100.00) / 100.00;

    }

    // calculateItemTotal(toolCode, checkoutDate, rentalDays, discount)
    // Calculates the total of a tool given the following:
    // Tool Code: A string uniquly identify the item.
    // Checkout Date: A date in which the customer intends on checking item out.
    // Rental Days: The number of days the customer intends on renting tool.
    // Discount: A discount in which applies to the total.
    public double calculateItemTotal(String toolCode, LocalDate checkoutDate, int rentalDays, double discount)
            throws IllegalArgumentException {

        Tool tool = new Tool();
        double disPercentage = discount / 100; // Taken off the total after checkout
        double discountAmount = 0;
        double itemTotal = 0;
        double preItemTotal = 0;
        int chargeableDays = 0;
        boolean foundItem = false;
        String formattedCheckoutDate; // our checkoutDate Formatted
        String formattedDueDate; // our due date formatted

        // If discount is out of bounds, return 0;
        if ((discount < 0 || discount > 100))
            throw new IllegalArgumentException("Value: " + discount + " isn't beteen[ 0 - 100 ]");

        // determine which tool we're using
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
        if (disPercentage > -1 || disPercentage < 101) {
            // Math.round(a*100)/100;
            discountAmount = Math.round(((tool.type.price * chargeableDays) * disPercentage) * 100.00) / 100.00;
            System.out.println("discount amount: " + discountAmount);
        } else
            throw new IllegalArgumentException("Value: " + disPercentage + " isn't beteen[ 0 - 100 ]");

        preItemTotal = Math.round((chargeableDays * tool.type.price) * 100.00) / 100.00;
        itemTotal = Math.round(((chargeableDays * tool.type.price) - discountAmount) * 100.00) / 100.00;

        formattedDueDate = checkoutDate.plusDays(rentalDays).format(formatDate);
        formattedCheckoutDate = checkoutDate.format(formatDate);

        // Generate our Rental Agreement
        RentalAgreement itemAgreement = new RentalAgreement(tool.toolCode, tool, rentalDays, formattedCheckoutDate,
                formattedDueDate, chargeableDays,
                decimalFormatter.format(preItemTotal), discount, decimalFormatter.format(discountAmount),
                decimalFormatter.format(itemTotal));
        rentalAgreements.add(itemAgreement);
        itemAgreement.generateCopy();
        System.out.println("Rental Agreement Generated...");

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

        LocalDate theDate = LocalDate.of(date.getYear(), date.getMonthValue(),
                date.getDayOfMonth());
        System.out.println("The checkout date: " + theDate);
        // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i != checkoutDays; i++) {
            LocalDate tempDate = theDate.plusDays(i);
            DayOfWeek day = tempDate.getDayOfWeek();

            // If the item charges holiday handle special case.

            Holiday holiday = new Holiday(tempDate);

            // Do not iterate through the weeks if it's a holiday
            if (holiday.isHoliday()) {
                // If we charge for a holiday, increment daysCharged.
                if (chargeHoliday)
                    daysCharged++;
            } else {

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

        return daysCharged;
    }

    // showRentalAgreement(agreements) prints all our rental agreements out.
    // agreements: Our list of rental agreements.
    public void showRentalAgreement(List<RentalAgreement> agreements) {
        // Print out all our rental agreements!
        if (agreements == null) {
            System.out.println("No Rental Agreements Press Enter to Continue");
            return;
        }
        for (int i = 0; i < agreements.size(); i++) {
            agreements.get(i).generateCopy();
            pressEnterToContinue();
        }
    }

    // Prompts user to press enter key to continue.
    public void pressEnterToContinue() {
        try {
            System.out.println("Press Enter to Continue...");
            System.in.read();
        } catch (Exception e) {
        }
    }

}
