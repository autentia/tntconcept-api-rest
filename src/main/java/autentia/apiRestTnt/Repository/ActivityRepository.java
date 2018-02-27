package autentia.apiRestTnt.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autentia.apiRestTnt.Model.Activity;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
	
	@Query("SELECT a FROM Activity a WHERE a.userId= :userId AND a.startDate BETWEEN :startDay AND :endDay")
	List<Activity> getActivitiesByDay(@Param("startDay") Date startDay, @Param("endDay") Date endDay,
			@Param("userId")Integer userId);
	
	@Query("SELECT SUM(a.duration/60) FROM Activity a WHERE a.userId= :userId AND a.startDate BETWEEN :startDay AND :endDay")
	Integer calculateHours(@Param("startDay")Date startDay,@Param("endDay") Date endDay,
			@Param("userId") Integer userId);
}
