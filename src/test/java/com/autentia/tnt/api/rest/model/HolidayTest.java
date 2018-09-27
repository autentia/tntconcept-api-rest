package com.autentia.tnt.api.rest.model;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class HolidayTest {

    private Holiday holiday;

    @Before
    public void setUp(){
        this.holiday = new Holiday();
    }

    @Test
    public void getAndSetHoliday () throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        holiday.setDate(format.parse("2018-08-08"));
        holiday.setDepartmentId(1);
        holiday.setDescription("Test description");
        holiday.setOwnerId(1);

        assertEquals(holiday.getDate(), format.parse("2018-08-08"));
        assertEquals(holiday.getDescription(),"Test description");
        assertSame(holiday.getDepartmentId(),1);
        assertSame(holiday.getOwnerId(), 1);
    }

}
