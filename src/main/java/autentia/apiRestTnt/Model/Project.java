package autentia.apiRestTnt.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Project")
public class Project {
	
	@Id
	private Integer id;
	
	@Column
    private String name;
    
	@Column
    private Boolean open;
	
	@Column
    private Integer organizationId;
	
	@OneToMany(mappedBy= "projectId")
	private List<ProjectRole> projectRoles;
    
	
    
    public List<ProjectRole> getProjectRoles() {
		return projectRoles;
	}

	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}

	public Project() { 
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public Integer getId(){
    	return id;
    }
    
    public String getName(){
    	return name;
    }

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	


}
