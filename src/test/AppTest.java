
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class AppTest {

    double delta = 0.001; // Expected and Result should be within these bounds.

    /***************** Required Tests ******************/

    @Test(expected = IllegalArgumentException.class)
    public void checkoutTestCase1() throws IllegalArgumentException {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(new IllegalArgumentException(),
                checkout.calculateItemTotal("JAKR", LocalDate.of(2015, 9, 3), 5, 101));
    }

    @Test
    public void checkoutTestCase2() throws Exception {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("LADW", new ToolType("Chainsaw", 1.49, true, false, true), "Shihl"));
        Checkout checkout = new Checkout(tools);
        assertEquals(2.68, checkout.calculateItemTotal("LADW", LocalDate.of(2020, 7, 2), 3, 10), delta);

    }

    @Test
    public void checkoutTestCase3() throws Exception {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("CHNS", new ToolType("Ladder", 1.99, true, true, false), "LADW"));
        Checkout checkout = new Checkout(tools);
        assertEquals(5.97, checkout.calculateItemTotal("CHNS", LocalDate.of(2015, 7, 2), 5, 25), delta);
    }

    @Test
    public void checkoutTestCase4() throws Exception {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKD", new ToolType("Jackhammer", 2.99, true, false, false), "DeWalt"));
        Checkout checkout = new Checkout(tools);
        assertEquals(8.97, checkout.calculateItemTotal("JAKD", LocalDate.of(2015, 9, 3), 6, 0), delta);
    }

    @Test
    public void checkoutTestCase5() throws Exception {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(17.94, checkout.calculateItemTotal("JAKR", LocalDate.of(2015, 7, 2), 9, 0), delta);
    }

    @Test
    public void checkoutTestCase6() throws Exception {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(1.49, checkout.calculateItemTotal("JAKR", LocalDate.of(2020, 7, 2), 4, 50), delta);
    }

    /***********************************************/
    /********** Exception Handling Tests ***********/

    @Test(expected = IllegalArgumentException.class)
    public void invalidPercentageTest1() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(new IllegalArgumentException(),
                checkout.calculateItemTotal("JAKR", LocalDate.of(2015, 9, 3), 5, -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPercentageTest2() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(new IllegalArgumentException(),
                checkout.calculateItemTotal("JAKR", LocalDate.of(2015, 9, 3), 1, 100.1));
    }

    /***********************************************/
    /************ Chargeable Days Tests ************/
    @Test
    public void chargeableDaysTest1() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(1, checkout.chargeableDays(LocalDate.of(2020, 7, 2), tools.get(0), 4));
    }

    @Test
    public void chargeableDaysTest2() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, true, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(3, checkout.chargeableDays(LocalDate.of(2020, 7, 2), tools.get(0), 4));
    }

    @Test
    public void chargeableDaysTest3() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, true, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(12, checkout.chargeableDays(LocalDate.of(2020, 7, 5), tools.get(0), 12));
    }

    @Test
    public void chargeableDaysTest4() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, true, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(11, checkout.chargeableDays(LocalDate.of(2022, 7, 3), tools.get(0), 12));
    }

    @Test
    public void chargeableDaysTest5() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, true, true), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(1, checkout.chargeableDays(LocalDate.of(2022, 7, 4), tools.get(0), 1));
    }

    @Test
    public void chargeableDaysTest6() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, true, false), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(0, checkout.chargeableDays(LocalDate.of(2022, 7, 4), tools.get(0), 1));
    }

    @Test
    public void chargeableDaysTest7() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, false, true), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(1, checkout.chargeableDays(LocalDate.of(2020, 7, 3), tools.get(0), 2));
    }

    @Test
    public void chargeableDaysTest8() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, true, true, true), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(12, checkout.chargeableDays(LocalDate.of(2021, 7, 5), tools.get(0), 12));
    }

    @Test
    public void chargeableDaysTest9() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, false, true, true), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(3, checkout.chargeableDays(LocalDate.of(2021, 7, 5), tools.get(0), 7));
    }

    @Test
    public void chargeableDaysTest10() {
        List<Tool> tools = new ArrayList<Tool>();
        tools.add(new Tool("JAKR", new ToolType("Jackhammer", 2.99, false, false, true), "Rigid"));
        Checkout checkout = new Checkout(tools);
        assertEquals(2, checkout.chargeableDays(LocalDate.of(2021, 7, 5), tools.get(0), 100));
    }

    /***********************************************/
    /**************** Holiday Tests ****************/
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
    public void isHolidayIndepenenceTest3() {
        Holiday holiday = new Holiday(LocalDate.of(2021, 7, 5));
        assertEquals(true, holiday.isHoliday());
    }

    @Test
    public void isHolidayIndepenenceTest4() {
        Holiday holiday = new Holiday(LocalDate.of(2021, 7, 4));
        assertEquals(false, holiday.isHoliday());
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

    @Test
    public void isHolidayLaborDayTest3() {
        Holiday holiday = new Holiday(LocalDate.of(2022, 9, 5));
        assertEquals(true, holiday.isHoliday());
    }

    /***********************************************/
    /***********************************************/
}
