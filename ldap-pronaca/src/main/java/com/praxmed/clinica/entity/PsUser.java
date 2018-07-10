package com.praxmed.clinica.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WB_USUARIOS_TEST")
public class PsUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146134778789560008L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "USUARIO", unique = true)
	private String usuario;

	@Column(name = "contrasenia")
	private String contrasenia;

	@Column(name = "rol")
	private byte rol;

	@Column(name = "activo")
	private boolean activo;

	public PsUser() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public byte getRol() {
		return 3;
	}

	public void setRol(byte rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		return true;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
