package autentia.apiRestTnt.services;

import autentia.apiRestTnt.model.Holiday;
import autentia.apiRestTnt.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    private HolidayRepository holidayRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        super();
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> getHolidaysPerYear(Integer year){
        return holidayRepository.getHolidaysPerYear(year);
    }

}
