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

package autentia.apiRestTnt.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autentia.apiRestTnt.Model.ActivitiesDay;
import autentia.apiRestTnt.Services.ActivityService;

@RestController
@RequestMapping("/api")
public class ActivitiesDayController {
	
	private ActivityService activityService;
	
	@Autowired
	public ActivitiesDayController(ActivityService activityService) {
		super();
		this.activityService = activityService;
	}

	@GetMapping("/activitiesByDates")
	public List<ActivitiesDay> getActivitiesByDates(@RequestParam(name = "startDate", required = true)
    @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(name = "endDate",required = true)
	@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endDate){
		
		List<ActivitiesDay> activitiesDayList = new ArrayList<>();
		LocalDateTime localDateTimeIncrement = startDate;
		
		while((startDate.isEqual(localDateTimeIncrement)) || (endDate.isAfter(localDateTimeIncrement)) || (endDate.isEqual(localDateTimeIncrement))) {
			System.out.println(localDateTimeIncrement.toString());
			ActivitiesDay activitiesDay = getActivitiesByDay(localDateTimeIncrement);
			if(activitiesDay.getTotal_hours()!=null) {
				activitiesDayList.add(activitiesDay);
			}
			localDateTimeIncrement = localDateTimeIncrement.plusDays(1);
		}
		
		return activitiesDayList;
	}
	
	@GetMapping("/activitiesByDay")
	public ActivitiesDay getActivitiesByDay(@RequestParam (name="date", required = true)
	@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime date){
		
		ActivitiesDay activitiesDay = new ActivitiesDay();
		activitiesDay.setDate(Date.from(date.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Date startDay = Date.from(date.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDay = Date.from(date.plusDays(1).toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
		activitiesDay.setTotal_hours(activityService.calculateHours(startDay,endDay, 1));
		activitiesDay.setActivities(activityService.getActivitiesByDay(startDay,endDay,1));
		return activitiesDay;
	}
}
