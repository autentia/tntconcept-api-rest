/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package autentia.apiRestTnt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import autentia.apiRestTnt.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
	
	@Query("SELECT a FROM Activity a WHERE a.userId= :userId AND a.startDate BETWEEN :startDay AND :endDay")
	List<Activity> getActivitiesByDay(@Param("startDay") Date startDay, @Param("endDay") Date endDay,
			@Param("userId")Integer userId);
	
	@Query("SELECT SUM(a.duration/60) FROM Activity a WHERE a.userId= :userId AND a.startDate BETWEEN :startDay AND :endDay")
	Integer calculateHours(@Param("startDay")Date startDay,@Param("endDay") Date endDay,
			@Param("userId") Integer userId);

	@Query("DELETE FROM Activity a WHERE a.id = :id")
	void deleteById(@Param("id") Integer id);
}
