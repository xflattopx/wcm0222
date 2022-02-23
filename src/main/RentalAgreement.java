// RentalAgreement Generates the rental agreement 
// at checkout.

public class RentalAgreement {

    String toolCode;
    Tool tool;
    int rentalDays;
    String checkoutDate;
    String dueDate;
    int chargeDays;
    String originalCharge;
    double discountPercent;
    String discountAmount;
    String finalCharge;

    public RentalAgreement(String toolCode, Tool tool, int rentalDays,
            String checkoutDate, String dueDate, int chargeDays, String originalCharge, double discountPercent,
            String discountAmount,
            String finalCharge) {
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
        System.out.println("| Rental Agreement for " + tool.toolCode);
        System.out.format("| Tool Code: %s\n", toolCode);
        System.out.format("| Tool Type: %s\n", tool.type.toolName);
        System.out.format("| Tool Brand: %s\n", tool.brand);
        System.out.format("| Rental Days: %s\n", rentalDays);
        System.out.format("| Checkout Date: %s\n", checkoutDate);
        System.out.format("| Due Date: %s\n", dueDate);
        System.out.format("| Daily Rental Charge: $%.2f\n", tool.type.price);
        System.out.format("| Chargeable Days: %s\n", chargeDays);
        System.out.format("| Original Charge: $%s\n", originalCharge);
        System.out.format("| Discount Percent: %-7s\n", Math.round(discountPercent) + "%");
        System.out.format("| Discount Amount: $%s\n", discountAmount);
        System.out.format("| Final Charge: $%s\n", finalCharge);
        System.out.println("|____________________________________");
    }

}
