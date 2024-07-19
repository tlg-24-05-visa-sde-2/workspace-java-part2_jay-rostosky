package com.entertainment;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TelevisionTest {
    private Television tv1;
    private Television tv2;

    @Before
    public void setUp() {
        tv1 = new Television("Sony", 50, DisplayType.PLASMA);  // channel is 3
        tv2 = new Television("Sony", 50, DisplayType.PLASMA);  // channel is 3
    }

    // --------
    // equals()
    // --------


    // ---------------
    // changeChannel()
    // ---------------

    @Test(expected=InvalidChannelException.class)
    public void changeChannel_shouldThrowInvalidChannelException_whenInvalid_upperBound()
    throws Exception {
        tv1.changeChannel(1000);
    }

    @Test
    public void changeChannel_shouldThrowInvalidChannelException_whenInvalid_lowerBound() {
        try {
            tv1.changeChannel(0);  // should throw exception
            fail("Should have thrown InvalidChannelException");
        }
        catch (InvalidChannelException e) {
            String expected = "Invalid channel: 0. Allowed range: [1,999].";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void changeChannel_shouldStoreValue_whenValid_upperBound() throws Exception {
        tv1.changeChannel(999);
        assertEquals(999, tv1.getCurrentChannel());
    }

    @Test
    public void changeChannel_shouldStoreValue_whenValid_lowerBound() throws Exception {
        tv1.changeChannel(1);
        assertEquals(1, tv1.getCurrentChannel());
    }

    // -----------
    // setVolume()
    // -----------


    @Test
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalid_upperBound() {
        try {
            tv1.setVolume(101);  // should throw exception
            fail("Should have thrown IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            String expected = "Invalid volume: 101. Allowed range: [0,100].";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalid_lowerBound() {
        tv1.setVolume(-1);  // should throw exception
    }

    @Test
    public void setVolume_shouldStoreValue_whenValid_upperBound() {
        tv1.setVolume(100);
        assertEquals(100, tv1.getVolume());
    }

    @Test
    public void setVolume_shouldStoreValue_whenValid_lowerBound() {
        tv1.setVolume(0);
        assertEquals(0, tv1.getVolume());
    }
}