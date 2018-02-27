package autentia.apiRestTnt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autentia.apiRestTnt.Model.ProjectRole;

@Repository
public interface ProjectRoleRepository extends JpaRepository<ProjectRole,Integer> {
	
//	List<ProjectRole> findByProjectId(Integer projectId);
}
