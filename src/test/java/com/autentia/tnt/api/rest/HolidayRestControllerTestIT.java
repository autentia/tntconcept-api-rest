package java.com.autentia.tnt.api.rest;

import com.autentia.tnt.api.rest.controller.HolidayController;
import com.autentia.tnt.api.rest.model.Holiday;
import com.autentia.tnt.api.rest.repository.HolidayRepository;
import com.autentia.tnt.api.rest.services.HolidayService;
import com.autentia.tnt.api.rest.services.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class HolidayRestControllerTestIT {

    @Value("${local.server.port}")
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate("admin", "adminadmin");

    private HolidayRepository holidayRepository;
    private final HolidayService holidayService = new HolidayService(holidayRepository);
    @Autowired
    private final HolidayController holidayController = new HolidayController(holidayService);

    @Test
    public void shouldReturnAllHolidaysOfThatYear() {
        final Integer year = 2018;

        List<Holiday> holidayList = holidayController.getHolidaysPerYear(year);

        final ResponseEntity<Holiday[]> response = restTemplate.getForEntity(getBaseUrl() + "/api/holidays?year=" + year,
                Holiday[].class);

        final Holiday[] result = response.getBody();

        assertEquals(result.length, holidayList.size());
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}