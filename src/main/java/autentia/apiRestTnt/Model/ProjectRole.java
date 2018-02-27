package autentia.apiRestTnt.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ProjectRole {
	
	@Id
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private Integer projectId;

	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getProjectId() {
		return projectId;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
