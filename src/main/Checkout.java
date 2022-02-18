//  Checkout requires the following information to be provided:
//  Tool code: See tool table above
//  Rental day count: The number of days for which the customer wants to rent the tool. (e.g. 4
//  days)
//  Discount percent: As a whole number, 0-100 (e.g. 20 = 20%)
//  Check out date

import java.util.*;

public class Checkout {

    Scanner scanner = new Scanner(System.in);
    List<Tool> tools;
    double discount;
    String date;

    public Checkout(List<Tool> tools) {
        this.tools = tools;
    }

    // transaction() is the main transaction done at checkout between
    // the customer and cashier.
    public void transaction() {
        int input;
        optionPanel();
        input = scanner.nextInt();
        System.out.println("input ->" + input);
        while (input != 9) {
            if (input == 1) {
                providedInformation();
                input = scanner.nextInt();
            } else if (input == 2) {
                optionPanel();
                input = scanner.nextInt();
            } else if (input == 3) {
                break;
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
        System.out.println("Exit               - 9");
        System.out.println("======================");

    }

    // ProvidedInformation() Collects the following information:
    // Tool code, rental day count, discount percent, checkout date
    public void providedInformation() {
        // Date currentDate = new Date();
        String input = "";
        System.out.println("Please provide tool code: ");
        input = scanner.next();
        System.out.println("How many days would you like to rent: ");
        input = scanner.next();
        System.out.println("Percentage of discount: ");
        input = scanner.next();
        System.out.println("Day of checkout");
        input = scanner.next();
    }

}
