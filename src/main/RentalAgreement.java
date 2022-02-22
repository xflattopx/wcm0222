// RentalAgreement Generates the rental agreement 
// at checkout.

import java.time.LocalDate;

public class RentalAgreement {

    String toolCode;
    Tool tool;
    int rentalDays;
    LocalDate checkoutDate;
    LocalDate dueDate;
    int chargeDays;
    double originalCharge;
    double discountPercent;
    double discountAmount;
    double finalCharge;

    public RentalAgreement(String toolCode, Tool tool, int rentalDays,
            LocalDate checkoutDate, LocalDate dueDate, int chargeDays, double originalCharge, double discountPercent,
            double discountAmount,
            double finalCharge) {
        this.toolCode = toolCode;
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;
        this.originalCharge = originalCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    // generateCopy() Prints out a rental agreement for the customer to view
    public void generateCopy() {
        System.out.println("_______________________________________");
        System.out.println("| Rental Agreement for " + tool.toolCode);
        System.out.format("| Tool Code: %-16s\n", toolCode);
        System.out.format("| Tool Type: %-16s\n", tool.type.toolName);
        System.out.format("| Tool Brand: %-14s\n", tool.brand);
        System.out.format("| Rental Days: %-13s\n", rentalDays);
        System.out.format("| Checkout Date: %-12s\n", checkoutDate);
        System.out.format("| Due Date: %-12s\n", dueDate);
        System.out.format("| Daily Rental Charge: $%.2f\n", tool.type.price);
        System.out.format("| Chargeable Days: %4s\n", chargeDays);
        System.out.format("| Original Charge: $%.2f\n", originalCharge);
        System.out.format("| Discount Percent: %-7s\n", Math.round(discountPercent) + "%");
        System.out.format("| Discount Amount: $%.2f\n", discountAmount);
        System.out.format("| Final Charge: $%.2f\n", finalCharge);
        System.out.println("_______________________________________");
    }

}
