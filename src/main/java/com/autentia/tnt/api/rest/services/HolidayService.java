package com.autentia.tnt.api.rest.services;

import com.autentia.tnt.api.rest.model.Holiday;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.repository.HolidayRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HolidayService {

	private HolidayRepository holidayRepository;

	@Autowired
	public HolidayService(HolidayRepository holidayRepository) {
		super();
		this.holidayRepository = holidayRepository;
	}

	public List<Holiday> getHolidaysPerYear(Integer year, User user) {
		final List<Holiday> holidays = holidayRepository.getHolidaysPerYear(year);
		return verifyHolidays(holidays, year, user.getHiringDate());
	}

	private List<Holiday> verifyHolidays(List<Holiday> holidaysList, Integer year, Date hiringDate) {

		LocalDate firstDayOfYear = LocalDate.now().withYear(year).with(TemporalAdjusters.firstDayOfYear());

		if (isWeekend(firstDayOfYear)) {
			firstDayOfYear = getNextWorkingDay(firstDayOfYear);
		}

		if (compensationDaysAreNotAvailable(hiringDate, firstDayOfYear)) {
			return removeCompensationDays(holidaysList);
		}

		return holidaysList;

	}

	private Boolean compensationDaysAreNotAvailable(Date hiringDate, LocalDate firstDayOfYear) {
		LocalDate hiringDateParsed = LocalDate
				.from(Instant.ofEpochMilli(hiringDate.getTime()).atZone(ZoneId.systemDefault()));

		return !(hiringDateParsed.isEqual(firstDayOfYear) || hiringDateParsed.isBefore(firstDayOfYear));

	}

	private Boolean isWeekend(LocalDate date) {
		final DayOfWeek dayOfWeek = date.getDayOfWeek();
		final Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		return weekend.contains(dayOfWeek);
	}

	private LocalDate getNextWorkingDay(LocalDate date) {
		LocalDate dateToIterate = date.plusDays(1);
		boolean workingDayFound = false;

		while (!workingDayFound) {
			if (isWeekend(dateToIterate)) {
				dateToIterate = dateToIterate.plusDays(1);
			} else {
				workingDayFound = true;
			}
		}
		return dateToIterate;
	}

	private List<Holiday> removeCompensationDays(List<Holiday> holidayList) {
		return holidayList.stream()
				.filter(holiday -> !holiday.getDescription().toLowerCase().contains("horas"))
				.collect(Collectors.toList());
	}
}
