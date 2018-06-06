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
import autentia.apiRestTnt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import autentia.apiRestTnt.model.Activity;
import autentia.apiRestTnt.services.ActivityService;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ActivityController {

	private ActivityService activityService;
	private ProjectRoleService projectRoleService;
	private UserService userService;

	@Autowired
	public ActivityController(ActivityService activityService, ProjectRoleService projectRoleService, UserService userService) {
		super();
		this.activityService = activityService;
		this.projectRoleService = projectRoleService;
		this.userService = userService;
	}

	@GetMapping(value="/activity/{activityId}")
	public Activity getActivity(@PathVariable("activityId") Integer activityId) {

		Activity activity = activityService.getActivityById(activityId);
		if (!activity.getUserId().equals(userService.getUserByLogin().getId())){
			throw new AccessDeniedException("Activity of other user");
		}

		return activity;
	}

	@PostMapping(value = "/activity",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Activity addActivity(Integer roleId,@Valid @RequestBody Activity activity) {
		ProjectRole projectRole = projectRoleService.getProjectRoleById(roleId);
		activity.setProjectRole(projectRole);

		return activityService.saveActivity(activity);
	}

	@PutMapping(value = "/activity",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Activity editActivity(Integer roleId,@RequestBody Activity activity){
		ProjectRole projectRole = projectRoleService.getProjectRoleById(roleId);
		activity.setProjectRole(projectRole);

		return activityService.saveActivity(activity);
	}

	@DeleteMapping(value = "/activity/{activityId}")
	public void deleteActivity(@PathVariable("activityId") Integer activityId){
		activityService.deleteActivityById(activityId);
	}

}
