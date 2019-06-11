package com.esa.bmap.service.usermanagement.implement;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.dao.common.BmapUserRepositoryInterface;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.service.usermanagement.interfaces.BmapUserServiceInterface;

@Service(value = "BmapUserServiceInterface")
public class BmapUserServiceImpl implements BmapUserServiceInterface {

	Logger log = LoggerFactory.getLogger(BmapUserServiceInterface.class);
	
	@Autowired
	private BmapUserRepositoryInterface bmapUserRepository;
	
	@Override
	public BmaapUser addBmapUser(BmaapUser user) throws BmapException {
		return this.bmapUserRepository.save(user);
	}

	@Override
	public void deleteBmapUser(Long idBmapUser) throws BmapException {
		this.bmapUserRepository.deleteById(idBmapUser);
	}

	@Override
	public BmaapUser getBmapUserById(Long id) throws BmapException {
		Optional<BmaapUser> user = this.bmapUserRepository.findById(id);
		BmaapUser bUser = null;
		//We check if the user is present
		if(user.isPresent()) {
			bUser = user.get();
		}
		//We return the user
		return bUser;
	}

}
