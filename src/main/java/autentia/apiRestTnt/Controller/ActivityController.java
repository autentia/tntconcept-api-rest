package autentia.apiRestTnt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value="/activities/{activityId}")
	public Activity getActivity(@PathVariable("activityId") Integer activityId) {
		return activityService.getActivityById(activityId);
	}

	 @GetMapping(value="/activities")
	 public List<Activity> getActivities() {
		 return activityService.getActivities();
	 }

	@PostMapping(value = "/addActivity")
	public Activity addActivity(@RequestBody Activity activity) {
		return activityService.saveActivity(activity);
	}

}
