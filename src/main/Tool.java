// A tool consists of the following:
// Tool Code: Unique identifier for a tool instance.
// Tool Type: The type of tool. Specifies daily rental charge and number of days
// rented.
// Brand: Brand of the tool.

public class Tool {

    public String toolCode;
    public ToolType type;
    public String brand;

    public Tool() {
    }

    public Tool(String code, ToolType type, String brand) {
        this.toolCode = code;
        this.type = type;
        this.brand = brand;
    }
}