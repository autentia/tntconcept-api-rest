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

package com.autentia.tnt.api.rest.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.autentia.tnt.api.rest.model.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Transactional
public class ProjectRepositoryTestIT {

    @Autowired
    ProjectRepository projectRepository;


    @Test
    public void findOneShouldReturnProjectFromDB() {
        final Integer id = 1;
        Project resultProject = projectRepository.findById(id).get();

        assertTrue(resultProject.getId() == id);
    }

    @Test
    public void saveShouldReturnProjectAfterSaving() {
        Project projectToSave = new Project();
        projectToSave.setName("Permiso extraordinario");

        Project savedProject = projectRepository.save(projectToSave);

        assertEquals(savedProject, projectToSave);

    }

    @Test
    public void findByNameShouldReturnProjectFromDB() {
        final String name = "Vacaciones";
        Project resultProject = projectRepository.findByName(name);

        assertEquals(resultProject.getName(), "Vacaciones");
    }


}
