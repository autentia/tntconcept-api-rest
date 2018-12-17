package com.autentia.tnt.api.rest.controller;

import com.autentia.tnt.api.rest.model.Holiday;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.HolidayService;
import com.autentia.tnt.api.rest.services.UserService;
import org.junit.Test;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HolidayControllerTest {
    private final HolidayService holidayService = mock(HolidayService.class);
    private final UserService userService = mock(UserService.class);

    private final HolidayController holidayController = new HolidayController(holidayService, userService);

    @Test
    public void getHolidaysPerYearShouldReturnAllHolidaysOfThatYear() {
        final int year = 2018;
        final List<Holiday> holidaysToReturn = Arrays.asList(mock(Holiday.class));
        final User userMock = mock(User.class);
        final Principal principalMock = mock(Principal.class);

        when(principalMock.getName()).thenReturn("testName");
        when(holidayService.getHolidaysPerYear(year, userMock)).thenReturn(holidaysToReturn);
        when(userService.getUserByLogin("testName")).thenReturn(userMock);
        when(userMock.getHiringDate()).thenReturn(new Date());

        final List<Holiday> result = holidayController.getHolidaysPerYear(year, principalMock);
        assertThat(result, is(holidaysToReturn));
    }
}
