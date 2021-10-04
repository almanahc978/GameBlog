package org.kamil.service.impl;

import java.util.List;

import org.kamil.model.Publisher;
import org.kamil.repository.PublisherRepository;
import org.kamil.service.IServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements IServiceCrud<Publisher> {

	private final PublisherRepository publisherRepository;

	@Autowired
	public PublisherServiceImpl(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}

	@Override
	public Publisher getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publisher> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publisher> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher add(Publisher entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher update(Publisher entity, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
