package com.autentia.tnt.api.rest.controller;

import com.autentia.tnt.api.rest.model.RequestHoliday;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.RequestHolidayService;
import com.autentia.tnt.api.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestHolidayController {

    private RequestHolidayService requestHolidayService;
    private UserService userService;

    @Autowired
    public RequestHolidayController(RequestHolidayService requestHolidayService, UserService userService) {
        this.requestHolidayService = requestHolidayService;
        this.userService = userService;
    }

    @RequestMapping("/vacations")
    public List<RequestHoliday> getVacationsByUserId() {
        User user = userService.getUserByLogin();
        return requestHolidayService.getVacationsByUserId(user.getId());
    }
}