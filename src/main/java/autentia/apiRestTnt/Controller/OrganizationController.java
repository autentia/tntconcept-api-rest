package autentia.apiRestTnt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import autentia.apiRestTnt.Model.Organization;
import autentia.apiRestTnt.Services.OrganizationService;

@RestController
@RequestMapping("/api")
public class OrganizationController {
	
	private OrganizationService organizationService;
	
	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		super();
		this.organizationService = organizationService;
	}

	@GetMapping("/organizations")
	public List<Organization> getOrganizations(){
		return organizationService.getOrganizations();
	}

}
