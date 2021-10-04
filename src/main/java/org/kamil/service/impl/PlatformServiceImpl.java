package org.kamil.service.impl;

import java.util.List;

import org.kamil.model.Platform;
import org.kamil.repository.PlatformRepository;
import org.kamil.service.IServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImpl implements IServiceCrud<Platform>{

	private final PlatformRepository platformRepository;
	
	@Autowired
	public PlatformServiceImpl(PlatformRepository platformRepository) {
		this.platformRepository = platformRepository;
	}
	
	@Override
	public Platform getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Platform> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Platform> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Platform add(Platform entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Platform update(Platform entity, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
