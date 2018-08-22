package autentia.apiRestTnt.controller;

import autentia.apiRestTnt.model.RequestHoliday;
import autentia.apiRestTnt.services.RequestHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestHolidayController {

    private RequestHolidayService requestHolidayService;

    @Autowired
    public RequestHolidayController(RequestHolidayService requestHolidayService) {
        this.requestHolidayService = requestHolidayService;
    }

    @RequestMapping("/vacations")
    public List<RequestHoliday> getVacationsByUserId(@RequestParam("userId") Integer userId) {
        return requestHolidayService.getVacationsByUserId(userId );
    }
}