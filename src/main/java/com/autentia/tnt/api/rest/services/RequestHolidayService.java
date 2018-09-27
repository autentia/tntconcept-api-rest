package com.autentia.tnt.api.rest.services;

import com.autentia.tnt.api.rest.model.RequestHoliday;
import com.autentia.tnt.api.rest.repository.RequestHolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestHolidayService {
    private RequestHolidayRepository requestHolidayRepository;

    @Autowired
    public RequestHolidayService(RequestHolidayRepository requestHolidayRepository) {
        this.requestHolidayRepository = requestHolidayRepository;
    }

    public List<RequestHoliday> getVacationsByUserId(Integer userId) {
        return requestHolidayRepository.vacationsByUserId(userId);
    }
}
