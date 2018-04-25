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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Services.ActivityService;

@RestController
@RequestMapping("/api")
public class ActivityController {

	private ActivityService activityService;

	@Autowired
	public ActivityController(ActivityService activityService) {
		super();
		this.activityService = activityService;
	}

	//Controlar que la actividad corresponde con el usuario
	@GetMapping(value="/activities/{activityId}")
	public Activity getActivity(@PathVariable("activityId") Integer activityId) {
		return activityService.getActivityById(activityId);
	}

	@PostMapping(value = "/activities")
	public Activity addActivity(@RequestBody Activity activity) {
		return activityService.saveActivity(activity);
	}

	@PutMapping(value = "/activities")
	public Activity editActivity(@RequestBody Activity activity){
		return activityService.saveActivity(activity);
	}

	@DeleteMapping(value = "/activities/{activityId}")
	public void deleteActivity(@PathVariable("activityId") Integer activityId){
		activityService.deleteActivityById(activityId);
	}

}
