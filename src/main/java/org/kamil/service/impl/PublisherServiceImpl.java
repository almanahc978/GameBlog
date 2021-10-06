package org.kamil.service.impl;

import java.util.List;

import org.kamil.exception.NoDataFoundException;
import org.kamil.exception.PublisherNotFoundException;
import org.kamil.model.Publisher;
import org.kamil.repository.PlatformRepository;
import org.kamil.repository.PublisherRepository;
import org.kamil.service.IServiceCrud;
import org.kamil.validation.ValidationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements IServiceCrud<Publisher> {

	private final PublisherRepository publisherRepository;
	private final ValidationFacade validatorFacade;

	@Autowired
	public PublisherServiceImpl(PublisherRepository publisherRepository, ValidationFacade validatorFacade) {
		this.publisherRepository = publisherRepository;
		this.validatorFacade = validatorFacade;
	}

	@Override
	public Publisher getById(Integer id) {
		return publisherRepository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
	}

	@Override
	public List<Publisher> getAll() {
		List<Publisher> publishers = publisherRepository.findAll();
		if (publishers.isEmpty()) {
			throw new NoDataFoundException();
		}
		return publishers;
	}

	@Override
	public List<Publisher> getByName(String name) {
		List<Publisher> publishers = publisherRepository.findByName(name);
		if (publishers.isEmpty()) {
			throw new NoDataFoundException();
		}
		return publishers;
	}

	@Override
	public Publisher add(Publisher publisher) {
		validatorFacade.validate(publisher);
		return publisherRepository.save(publisher);
	}

	@Override
	public Publisher update(Publisher newPublisher, Integer id) {
		return publisherRepository.findById(id).map((publisher) -> {
			publisher.setName(newPublisher.getName());
			publisher.setDescription(newPublisher.getDescription());
			return publisherRepository.save(publisher);
		}).orElseGet(() -> {
			newPublisher.setId(id);
			return publisherRepository.save(newPublisher);
		});
	}

	@Override
	public void delete(Integer id) {
		publisherRepository.deleteById(id);
	}

}
