package autentia.apiRestTnt.repository;


import autentia.apiRestTnt.model.RequestHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestHolidayRepository extends JpaRepository<RequestHoliday, Long> {

    @Query("SELECT a from RequestHoliday a WHERE a.userId = :userId")
    List<RequestHoliday> vacationsByUserId(@Param("userId") Integer userId);
}
