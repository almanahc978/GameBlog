package org.kamil.controller;

import org.kamil.service.impl.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = {"application/hall+json"})
public class PublisherController {

	private PublisherService publisherServiceImpl;

	@Autowired
	public PublisherController(PublisherService publisherServiceImpl) {
		this.publisherServiceImpl = publisherServiceImpl;
	}
}
