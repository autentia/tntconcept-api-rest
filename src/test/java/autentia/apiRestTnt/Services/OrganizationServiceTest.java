package autentia.apiRestTnt.Services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import autentia.apiRestTnt.Model.Organization;
import autentia.apiRestTnt.Repository.OrganizationRepository;

public class OrganizationServiceTest {
	
	private final OrganizationRepository organizationRepository = mock(OrganizationRepository.class);
	
	private final OrganizationService organizationService = new OrganizationService(organizationRepository);
	
	@Test
	public void getOrganizationsShouldReturnOrganizationsFromRepository() {
		final List<Organization> organizations = Arrays.asList(mock(Organization.class));
		
		when(organizationRepository.findAll()).thenReturn(organizations);
		
		final List<Organization> result = organizationService.getOrganizations();
		
		assertThat(result,is(organizations));
	}

}
