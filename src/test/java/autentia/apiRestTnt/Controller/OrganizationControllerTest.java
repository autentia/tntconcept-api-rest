package autentia.apiRestTnt.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import autentia.apiRestTnt.Model.Organization;
import autentia.apiRestTnt.Services.OrganizationService;

public class OrganizationControllerTest {
	
	private final OrganizationService organizationService = mock(OrganizationService.class);
	
	private final OrganizationController organizationController = new OrganizationController(organizationService);
	
	@Test
	public void getOrganizationsShouldReturnOrganizationsFromService() {
		final List<Organization> organizations = Arrays.asList(mock(Organization.class));
		
		when(organizationService.getOrganizations()).thenReturn(organizations);
		
		final List<Organization> result = organizationController.getOrganizations();
		
		assertThat(result,is(organizations));
	}
}
