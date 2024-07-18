package com.javatunes.personnel;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

public class HourlyEmployeeTest {
    // business object(s) under test - the "fixture"
    private HourlyEmployee emp;

    @Before
    public void setUp() {
        emp = new HourlyEmployee("Amilia", Date.valueOf("2010-02-02"), 37.5, 25.0);
    }

    @Test
    public void testPayTaxes() {
        assertEquals(234.375, emp.payTaxes(), .001);  // should be 25% of rate * hours
    }

    @Test
    public void testPay() {
        assertEquals(937.5, emp.pay(), .001);   // should be rate * hours
    }
}