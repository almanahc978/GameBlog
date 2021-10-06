package org.kamil.controller;

import org.kamil.service.impl.PlatformServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlatformController {

	private PlatformServiceImpl platformServiceImpl;
	
	@Autowired
	public PlatformController(PlatformServiceImpl platformServiceImpl) {
		this.platformServiceImpl = platformServiceImpl;
	}
}
