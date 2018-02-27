package autentia.apiRestTnt.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrganizationTest {
	
	private Organization organization;
	
	@Before
	public void setUp() {
		this.organization = new Organization();
	}
	
	@Test
	public void getAndSetOrganizationTest() {
		organization.setName("Organization");
		List<Project> projectList = new ArrayList<>();
		projectList.add(new Project());
		organization.setProjects(projectList);
	}

}
