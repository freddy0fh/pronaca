package com.praxmed.service;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praxmed.clinica.entity.PsUser;
import com.praxmed.clinica.repo.PsUserRepo;

@Service
public class ClinicaService {

	private static final Logger logger = LoggerFactory.getLogger(ClinicaService.class);

	@Autowired
	private PsUserRepo psUserRepo;

	KeyPairGenerator kpg;
	KeyPair kp;

	public ClinicaService() {
		try {
			this.kpg = KeyPairGenerator.getInstance("RSA");
			this.kpg.initialize(2048);
			this.kp = this.kpg.genKeyPair();
			this.kp.getPrivate();

		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
	}

	public String getPublicKey() {
		try {
			KeyFactory fact = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec pub = new RSAPublicKeySpec(BigInteger.ZERO, BigInteger.ZERO);
			pub = fact.getKeySpec(this.kp.getPublic(), RSAPublicKeySpec.class);
			return pub.getModulus().toString(16);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "-1";

	}

	public List<PsUser> findAll() {
		// TODO Auto-generated method stub
		return psUserRepo.findAll();
	}

}
