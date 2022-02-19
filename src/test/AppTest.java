
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.*;

public class AppTest {

    double delta = 0.001; // Expecte dand Result should be within these bounds.

    @Test
    @Deprecated
    public void checkoutTestCase1() {
        List<Tool> tools = new ArrayList<Tool>();
        Checkout checkout = new Checkout(tools);
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        assertEquals("0.0", checkout.calculateItemTotal("JAKR", new Date(2015, 8, 3), 5, 101), delta);
    }

    @Test
    @Deprecated
    public void checkoutTestCase2() {
        List<Tool> tools = new ArrayList<Tool>();
        Checkout checkout = new Checkout(tools);
        tools.add(new Tool("LADW", new ToolType("Chainsaw", 1.49, true, false, true)));
        assertEquals(0, checkout.calculateItemTotal("LADW", new Date(2020, 6, 2), 3, 10), delta);

    }

    @Test
    @Deprecated
    public void checkoutTestCase3() {
        List<Tool> tools = new ArrayList<Tool>();
        Checkout checkout = new Checkout(tools);
        tools.add(new Tool("CHNS", new ToolType("Ladder", 1.99, true, true, false)));
        assertEquals(0, checkout.calculateItemTotal("CHNS", new Date(2020, 6, 2), 3, 10), delta);
    }

    @Test
    @Deprecated
    public void checkoutTestCase4() {
        List<Tool> tools = new ArrayList<Tool>();
        Checkout checkout = new Checkout(tools);
        tools.add(new Tool("JAKD", new ToolType("Jackhammer", 2.99, true, false, false)));
        assertEquals(0, checkout.calculateItemTotal("JAKD", new Date(2015, 8, 3), 3, 10), delta);
    }

    @Test
    @Deprecated
    public void checkoutTestCase5() {
        List<Tool> tools = new ArrayList<Tool>();
        Checkout checkout = new Checkout(tools);
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        assertEquals(0, checkout.calculateItemTotal("JAKR", new Date(2015, 6, 2), 3, 10), delta);
    }

    @Test
    @Deprecated
    public void checkoutTestCase6() {
        List<Tool> tools = new ArrayList<Tool>();
        Checkout checkout = new Checkout(tools);
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        assertEquals(0, checkout.calculateItemTotal("JAKR", new Date(2020, 6, 2), 3, 10), delta);
    }
}
