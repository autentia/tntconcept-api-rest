package com.autentia.tnt.api.rest.repository;

import com.autentia.tnt.api.rest.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query("SELECT h FROM Holiday h WHERE YEAR(h.date)= :year")
    List<Holiday> getHolidaysPerYear(@Param("year")Integer year);

}
