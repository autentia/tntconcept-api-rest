package com.autentia.tnt.api.rest.controller;

import com.autentia.tnt.api.rest.model.Holiday;
import com.autentia.tnt.api.rest.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HolidayController {

    private HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @RequestMapping("/holidays")
    public List<Holiday> getHolidaysPerYear(@RequestParam("year") Integer year) {
        return holidayService.getHolidaysPerYear(year);
    }
}

