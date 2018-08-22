package autentia.apiRestTnt.services;

import autentia.apiRestTnt.model.Holiday;
import autentia.apiRestTnt.model.RequestHoliday;
import autentia.apiRestTnt.repository.RequestHolidayRepository;
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
