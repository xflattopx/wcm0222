// ToolType specifies the prices and which days the rental charge
// applies.
// Tool Name: Name of the tool.
// Daily Charge: Charges the customer everyday.
// Weekday Charge: Charges the customer during the week.
// Weekend Charge: Charges the customer during the weekend.

public class ToolType {

    public String toolName;
    public double price;
    public boolean weekdayCharge;
    public boolean weekendCharge;
    public boolean holidayCharge;

    public ToolType(String toolName, double price, boolean week, boolean weekend, boolean holiday) {
        this.toolName = toolName;
        this.price = price;
        this.weekdayCharge = week;
        this.weekendCharge = weekend;
        this.holidayCharge = holiday;
    }
}
