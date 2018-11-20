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

package com.autentia.tnt.api.rest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.tnt.api.rest.controller.OrganizationController;
import com.autentia.tnt.api.rest.model.Organization;
import com.autentia.tnt.api.rest.repository.OrganizationRepository;
import com.autentia.tnt.api.rest.services.OrganizationService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class OrganizationRestControllerTestIT {

    @Value("${local.server.port}")
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate("admin", "adminadmin");

    private OrganizationRepository organizationRepository;

    private final OrganizationService organizationService = new OrganizationService(organizationRepository);

    @Autowired
    private final OrganizationController organizationController = new OrganizationController(organizationService);

    @Test
    public void shouldReturnAllOrganizationsDetails() {

        List<Organization> organizations = organizationController.getOrganizations();

        final ResponseEntity<Organization[]> response = restTemplate.getForEntity(getBaseUrl() + "/api/organizations",
                Organization[].class);

        final Organization[] result = response.getBody();

        assertSame(result[0].getId(), organizations.get(0).getId());
        assertEquals(result[0].getName(), organizations.get(0).getName());

        assertEquals(result.length, organizations.size());
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }
}
