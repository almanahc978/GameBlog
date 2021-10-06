package org.kamil.controller;

import org.kamil.service.impl.PublisherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublisherController {

	private PublisherServiceImpl publisherServiceImpl;

	@Autowired
	public PublisherController(PublisherServiceImpl publisherServiceImpl) {
		this.publisherServiceImpl = publisherServiceImpl;
	}
}
