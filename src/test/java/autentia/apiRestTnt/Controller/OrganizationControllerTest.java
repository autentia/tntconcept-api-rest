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
