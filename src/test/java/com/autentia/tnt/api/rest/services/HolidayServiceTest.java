package com.autentia.tnt.api.rest.services;

import com.autentia.tnt.api.rest.model.Holiday;
import com.autentia.tnt.api.rest.repository.HolidayRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HolidayServiceTest {
    private final HolidayRepository holidayRepository = mock(HolidayRepository.class);
    private final HolidayService holidayService = new HolidayService(holidayRepository);

    @Test
    public void getHolidaysPerYearFromRepository() {
        final List<Holiday> holidaysToReturn = Arrays.asList(mock(Holiday.class));
        when(holidayRepository.getHolidaysPerYear(2018)).thenReturn(holidaysToReturn);

        final List<Holiday> result = holidayService.getHolidaysPerYear(2018);
        assertThat(result, is(equalTo(holidaysToReturn)));
    }
}
