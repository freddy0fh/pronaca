package com.praxmed.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praxmed.clinica.entity.PsUser;
import com.praxmed.service.ClinicaService;

@RestController
@RequestMapping("/cli")
public class ClinicaController {

	private static final Logger logger = LoggerFactory.getLogger(ClinicaController.class);
	private ClinicaService clinicaService;

	@Autowired
	public ClinicaController(ClinicaService especialidadMedicoService) {
		this.clinicaService = especialidadMedicoService;
	}

	@GetMapping("/all")
	public List<PsUser> findAll() {
		return clinicaService.findAll();
	}

	@GetMapping("/pkey")
	public String getPublicKey() {
		logger.info("get keu");
		return clinicaService.getPublicKey();
	}

}
