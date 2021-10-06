package org.kamil.service.impl;

import java.util.List;

import org.kamil.exception.NoDataFoundException;
import org.kamil.exception.PlatformNotFoundException;
import org.kamil.model.Platform;
import org.kamil.repository.PlatformRepository;
import org.kamil.service.IServiceCrud;
import org.kamil.validation.ValidationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceImpl implements IServiceCrud<Platform> {

	private final PlatformRepository platformRepository;
	private final ValidationFacade validationFacade;

	@Autowired
	public PlatformServiceImpl(PlatformRepository platformRepository, ValidationFacade validationFacade) {
		this.platformRepository = platformRepository;
		this.validationFacade = validationFacade;
	}

	@Override
	public Platform getById(Integer id) {
		return platformRepository.findById(id).orElseThrow(() -> new PlatformNotFoundException(id));
	}

	@Override
	public List<Platform> getAll() {
		List<Platform> platforms = platformRepository.findAll();
		if (platforms.isEmpty()) {
			throw new NoDataFoundException();
		}
		return platforms;
	}

	@Override
	public List<Platform> getByName(String name) {
		List<Platform> platforms = platformRepository.findByName(name);
		if (platforms.isEmpty()) {
			throw new NoDataFoundException();
		}

		return platforms;
	}

	@Override
	public Platform add(Platform platform) {
		validationFacade.validate(platform);
		return platformRepository.save(platform);
	}

	@Override
	public Platform update(Platform newPlatform, Integer id) {
		return platformRepository.findById(id).map((platform) -> {
			platform.setName(newPlatform.getName());
			platform.setDescription(newPlatform.getDescription());
			return platformRepository.save(platform);
		}).orElseGet(() -> {
			newPlatform.setId(id);
			return platformRepository.save(newPlatform);
		});
	}

	@Override
	public void delete(Integer id) {
		platformRepository.deleteById(id);
	}

}
