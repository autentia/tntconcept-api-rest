/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.api.rest.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.autentia.tnt.api.rest.model.Organization;
import com.autentia.tnt.api.rest.repository.OrganizationRepository;

public class OrganizationServiceTest {

    private final OrganizationRepository organizationRepository = mock(OrganizationRepository.class);

    private final OrganizationService organizationService = new OrganizationService(organizationRepository);

    @Test
    public void getOrganizationsShouldReturnOrganizationsFromRepository() {
        final List<Organization> organizations = Arrays.asList(mock(Organization.class));

        when(organizationRepository.findAll()).thenReturn(organizations);

        final List<Organization> result = organizationService.getOrganizations();

        assertThat(result, is(organizations));
    }

    @Test
    public void getOrganizationByNameShouldReturnOrganizationFromRepository() {
        final String name = "Organization";
        final Organization organization = mock(Organization.class);

        when(organizationRepository.findByName(name)).thenReturn(organization);

        final Organization result = organizationService.getOrganizationByName(name);

        assertThat(result, is(organization));
    }

}
