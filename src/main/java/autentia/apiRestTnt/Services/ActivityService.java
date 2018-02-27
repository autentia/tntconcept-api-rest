package autentia.apiRestTnt.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autentia.apiRestTnt.Model.Activity;
import autentia.apiRestTnt.Repository.ActivityRepository;

@Service
public class ActivityService {

	ActivityRepository activityRepository;
	
	@Autowired
	public ActivityService(ActivityRepository activityRepository) {
		super();
		this.activityRepository = activityRepository;
	}

	public Activity getActivityById(Integer activityId) {
		return activityRepository.findOne(activityId);
	}
	
	public List<Activity> getActivitiesByDay(Date startDay, Date endDay,Integer userId) {
		return activityRepository.getActivitiesByDay(startDay,endDay, userId);
	}
	
	public List<Activity> getActivities(){
		return activityRepository.findAll();
	}
	
	public Activity saveActivity(Activity activity) {
		return activityRepository.save(activity);
	}
	
	public Integer calculateHours(Date startDay, Date endDay, Integer userId) {
		return activityRepository.calculateHours(startDay, endDay, userId);
	}
}
