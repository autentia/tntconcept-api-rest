package autentia.apiRestTnt.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import autentia.apiRestTnt.Model.Organization;
import autentia.apiRestTnt.Repository.OrganizationRepository;

@Service
public class OrganizationService {

	private OrganizationRepository organizationRepository;
	
	@Autowired
	public OrganizationService(OrganizationRepository organizationRepository) {
		super();
		this.organizationRepository = organizationRepository;
	}

//	public Organization getOrganizationById(Integer organizationId) {
//		return organizationRepository.findOne(organizationId);
//	}
	
	public List<Organization> getOrganizations(){
		return organizationRepository.findAll();
	}
}
