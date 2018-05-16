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

package autentia.apiRestTnt.controller;

import autentia.apiRestTnt.model.ProjectRole;
import autentia.apiRestTnt.services.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import autentia.apiRestTnt.model.Activity;
import autentia.apiRestTnt.services.ActivityService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ActivityController {

	private ActivityService activityService;
	private ProjectRoleService projectRoleService;

	@Autowired
	public ActivityController(ActivityService activityService, ProjectRoleService projectRoleService) {
		super();
		this.activityService = activityService;
		this.projectRoleService = projectRoleService;
	}

	//Controlar que la actividad corresponde con el usuario
	@GetMapping(value="/activity/{activityId}")
	public Activity getActivity(@PathVariable("activityId") Integer activityId) {
		return activityService.getActivityById(activityId);
	}

	@PostMapping(value = "/activity",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Activity addActivity(Integer roleId,@Valid @RequestBody Activity activity) {
		ProjectRole projectRole = projectRoleService.getProjectRoleById(roleId);
		System.out.println("roleId = [" + roleId + "], activity = [" + activity + "]");
		System.out.println("projectRole = " + projectRole.getId());
		activity.setProjectRole(projectRole);
		System.out.println("activity = " + activity.getDuration());

		return activityService.saveActivity(activity);
	}

	@PutMapping(value = "/activity")
	public Activity editActivity(@RequestBody Activity activity){
		return activityService.saveActivity(activity);
	}

	@DeleteMapping(value = "/activity/{activityId}")
	public void deleteActivity(@PathVariable("activityId") Integer activityId){
		activityService.deleteActivityById(activityId);
	}

}
