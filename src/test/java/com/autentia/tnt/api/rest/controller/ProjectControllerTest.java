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

package com.autentia.tnt.api.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.autentia.tnt.api.rest.model.Project;
import com.autentia.tnt.api.rest.services.ProjectService;

public class ProjectControllerTest {

    private final ProjectService projectService = mock(ProjectService.class);

    private final ProjectController projectController = new ProjectController(projectService);

    @Test
    public void getProjectByIdShouldReturnProjectFromService() {
        final Project projectToReturn = mock(Project.class);
        final Project returnedProject = mock(Project.class);

        when(projectService.getProjectById(projectToReturn.getId())).thenReturn(returnedProject);

        final Project result = projectController.getProjectById(projectToReturn.getId());
        assertThat(result, is(returnedProject));
    }


}
