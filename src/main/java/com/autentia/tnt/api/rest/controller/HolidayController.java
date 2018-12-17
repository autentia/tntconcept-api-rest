package com.autentia.tnt.api.rest.controller;

import com.autentia.tnt.api.rest.model.Holiday;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.HolidayService;
import com.autentia.tnt.api.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HolidayController {

    private HolidayService holidayService;
    private UserService userService;

    @Autowired
    public HolidayController(HolidayService holidayService, UserService userService) {

        this.holidayService = holidayService;
        this.userService = userService;
    }

    @RequestMapping("/holidays")
    public List<Holiday> getHolidaysPerYear(@RequestParam("year") Integer year, Principal principal) {
        User user = userService.getUserByLogin(principal.getName());
        return holidayService.getHolidaysPerYear(year, user);
    }
}

