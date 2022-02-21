
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class AppTest {

    double delta = 0.001; // Expecte dand Result should be within these bounds.

    @Test
    public void checkoutTestCase1() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        Checkout checkout = new Checkout(tools);
        assertEquals(0., checkout.calculateItemTotal("JAKR", LocalDate.of(2015, 9, 3), 5, 101), delta);
    }

    @Test
    public void checkoutTestCase2() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("LADW", new ToolType("Chainsaw", 1.49, true, false, true)));
        Checkout checkout = new Checkout(tools);
        assertEquals(2.682, checkout.calculateItemTotal("LADW", LocalDate.of(2020, 7, 2), 3, 10), delta);

    }

    @Test
    public void checkoutTestCase3() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("CHNS", new ToolType("Ladder", 1.99, true, true, false)));
        Checkout checkout = new Checkout(tools);
        assertEquals(5.97, checkout.calculateItemTotal("CHNS", LocalDate.of(2015, 7, 2), 5, 25), delta);
    }

    @Test
    public void checkoutTestCase4() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKD", new ToolType("Jackhammer", 2.99, true, false, false)));
        Checkout checkout = new Checkout(tools);
        assertEquals(8.97, checkout.calculateItemTotal("JAKD", LocalDate.of(2015, 9, 3), 6, 0), delta);
    }

    @Test
    public void checkoutTestCase5() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        Checkout checkout = new Checkout(tools);
        assertEquals(17.94, checkout.calculateItemTotal("JAKR", LocalDate.of(2015, 7, 2), 9, 0), delta);
    }

    @Test
    public void checkoutTestCase6() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        Checkout checkout = new Checkout(tools);
        assertEquals(1.495, checkout.calculateItemTotal("JAKR", LocalDate.of(2020, 7, 2), 4, 50), delta);
    }

    @Test
    public void chargeableDaysTest() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false)));
        Checkout checkout = new Checkout(tools);
        assertEquals(1, checkout.chargeableDays(LocalDate.of(2020, 7, 2), tools.get(0), 4));
    }

    @Test
    public void chargeableDaysTest2() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, true, false)));
        Checkout checkout = new Checkout(tools);
        assertEquals(3, checkout.chargeableDays(LocalDate.of(2020, 7, 2), tools.get(0), 4));
    }

    @Test
    public void isHolidayIndependenceDayTest1() {
        Holiday holiday = new Holiday(LocalDate.of(2020, 7, 4));
        assertEquals(false, holiday.isHoliday());
    }

    @Test
    public void isHolidayIndependenceDayTest2() {
        Holiday holiday = new Holiday(LocalDate.of(2020, 7, 3));
        assertEquals(true, holiday.isHoliday());
    }

    @Test
    public void isHolidayLaborDayTest1() {
        Holiday holiday = new Holiday(LocalDate.of(2020, 9, 3));
        assertEquals(false, holiday.isHoliday());
    }

    @Test
    public void isHolidayLaborDayTest2() {
        Holiday holiday = new Holiday(LocalDate.of(2020, 9, 7));
        assertEquals(true, holiday.isHoliday());
    }
}
