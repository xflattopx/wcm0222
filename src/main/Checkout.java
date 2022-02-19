//  Checkout requires the following information to be provided:
//  Tool code: See tool table above
//  Rental day count: The number of days for which the customer wants to rent the tool. (e.g. 4
//  days)
//  Discount percent: As a whole number, 0-100 (e.g. 20 = 20%)
//  Check out date

import java.util.Date;

import java.util.*;

public class Checkout {

    Scanner scanner = new Scanner(System.in);
    List<Tool> tools;
    double discount;
    String date;
    double total = 0;

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

        System.out.println("Please provide tool code: ");
        code = scanner.next();
        System.out.println("How many days would you like to rent: ");
        days = scanner.nextInt();
        System.out.println("Percentage of discount [between 0 - 100]: ");
        discount = scanner.nextInt();
        System.out.println("Month of checkout [0-11]");
        month = scanner.nextInt();
        System.out.println("Day of checkout [1-31]");
        day = scanner.nextInt();
        System.out.println("Year of checkout [1900 - 2022]");
        year = scanner.nextInt();
        total += calculateItemTotal(code, new Date(year, month, day), days, discount);

    }

    // calculateItemTotal() calculates the total of a tool given the following:
    // Tool Code: A string uniquly identify the item.
    // Checkout Date: A date in which the customer intends on checking item out.
    // Rental Days: The number of days the customer intends on renting tool.
    // Discount: A discount in which applies to the total
    public double calculateItemTotal(String toolCode, Date checkoutDate, int rentalDays, double discount) {
        double disPercentage = discount / 100; // Taken off the total after checkout
        // int chargeDays = 0; // Total number of chargeable Days.
        // Date dueDate;
        double discountAmount = 0.0;
        double itemTotal = 0.0;
        Tool tool = new Tool();

        // determine which tool we're using (can be improved with key value pair)
        for (int i = 0; i < tools.size(); i++) {
            // We found the tool.
            if (tools.get(i).toolCode.equals(toolCode)) {
                tool = tools.get(i);
                break;
            }
        }

        if (tool.toolCode.equals(null)) {
            System.out.println("Invalid Tool Code");
            return itemTotal;
        }

        if (disPercentage > -1 || disPercentage < 101)
            discountAmount = (tool.type.price * rentalDays) * disPercentage;
        else
            return itemTotal;

        itemTotal = (rentalDays * tool.type.price) - discountAmount;

        System.out.println("item Price: " + tool.type.price + " Discount: " + discountAmount + " Total: " +
                itemTotal);

        return itemTotal;
    }

}
