package autentia.apiRestTnt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autentia.apiRestTnt.Model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer>{
	
//	@Query("SELECT p FROM Project p WHERE p.organizationId= :organizationId AND p.open is true")
//	List<Project> getOpenProjectsByOrganization(@Param("organizationId") Integer organizationId);
	
}
