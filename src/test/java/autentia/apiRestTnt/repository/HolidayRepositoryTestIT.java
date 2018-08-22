package autentia.apiRestTnt.repository;

import autentia.apiRestTnt.model.Holiday;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class HolidayRepositoryTestIT {

    @Autowired
    HolidayRepository holidayRepository;


    @Test
    public void getHolidaysPerYearShouldReturnAllHolidaysOfThatYear() {
        final int year = 2018;
        List<Holiday> holidaysList = holidayRepository.getHolidaysPerYear(year);

        assertEquals(3, holidaysList.size());
    }
}