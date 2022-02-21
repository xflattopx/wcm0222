// Point of Sale Tool Rental Tool
// Author - Chase Moore

import java.time.LocalDate;
import java.util.*;

public class MainHandler {

    public static void main(String[] args) {
        // Create our tools
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("CHNS", new ToolType("Ladder", 1.99, true, true, false)));
        tools.add(new Tool("LADW", new ToolType("Chainsaw", 1.49, true, false, true)));
        tools.add(new Tool("JAKD", new ToolType("Jackhammer", 2.99, true, false, false)));
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        tools.add(new Tool("TEST", new ToolType("TestHammer", 2.99, true, false, true)));
        Checkout checkout = new Checkout(tools);

        // checkout.chargeableDays(LocalDate.of(2014, 7, 2), tools.get(4), 7);
        // checkout.transaction(); // Transaction has began at checkout

    }
}
