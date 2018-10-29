/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L. Copyright (C) 2007 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.api.rest.controller;

import com.autentia.tnt.api.rest.model.Activity;
import com.autentia.tnt.api.rest.model.DTO.ActivityDTO;
import com.autentia.tnt.api.rest.model.ProjectRole;
import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.ActivityService;
import com.autentia.tnt.api.rest.services.ProjectRoleService;
import com.autentia.tnt.api.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ActivityController {

	private ActivityService activityService;

	private UserService userService;

	@Autowired
	public ActivityController(ActivityService activityService, UserService userService) {
		super();
		this.activityService = activityService;
		this.userService = userService;
	}

	@GetMapping(value = "/activity/{activityId}")
	public Activity getActivity(@PathVariable("activityId") Integer activityId) {

		Activity activity = activityService.getActivityById(activityId);
		userService.checkAuthorizationById(activity.getUserId());

		return activity;
	}

	@PostMapping(value = "/activity", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Activity addActivity( @Valid @RequestBody ActivityDTO activityDTO) {
		return activityService.saveActivity(activityDTO);
	}

	@PutMapping(value = "/activity", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Activity editActivity(@RequestBody ActivityDTO activityDTO) {
		return activityService.saveActivity(activityDTO);
	}

	@DeleteMapping(value = "/activity/{activityId}")
	public void deleteActivity(@PathVariable("activityId") Integer activityId) {

		Activity activity = activityService.getActivityById(activityId);
		userService.checkAuthorizationById(activity.getUserId());

		activityService.deleteActivityById(activityId);
	}

}
