package com.praxmed.clinica.dto;

import java.io.Serializable;

public class User implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String password;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
