package com.autentia.tnt.api.rest.controller;

import com.autentia.tnt.api.rest.model.Holiday;
import com.autentia.tnt.api.rest.services.HolidayService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HolidayControllerTest {
    private final HolidayService holidayService = mock(HolidayService.class);

    private final HolidayController holidayController = new HolidayController(holidayService);

    @Test
    public void getHolidaysPerYearShouldReturnAllHolidaysOfThatYear() {
        final int year = 2018;
        final List<Holiday> holidaysToReturn = Arrays.asList(mock(Holiday.class));

        when(holidayService.getHolidaysPerYear(year)).thenReturn(holidaysToReturn);

        final List<Holiday> result = holidayController.getHolidaysPerYear(year);
        assertThat(result, is(holidaysToReturn));
    }
}
