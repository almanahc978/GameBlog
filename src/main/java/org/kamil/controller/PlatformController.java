package org.kamil.controller;

import org.kamil.service.impl.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = {"application/hall+json"})
public class PlatformController {

	private PlatformService platformServiceImpl;
	
	@Autowired
	public PlatformController(PlatformService platformServiceImpl) {
		this.platformServiceImpl = platformServiceImpl;
	}
}
