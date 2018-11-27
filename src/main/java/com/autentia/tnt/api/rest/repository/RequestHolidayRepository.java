package com.autentia.tnt.api.rest.repository;


import com.autentia.tnt.api.rest.model.RequestHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestHolidayRepository extends JpaRepository<RequestHoliday, Long> {
    @Query("SELECT a from RequestHoliday a WHERE a.userId = :userId AND (a.state = 'ACCEPT' or  a.state = 'PENDING')")
    List<RequestHoliday> vacationsByUserId(@Param("userId") Integer userId);
}
