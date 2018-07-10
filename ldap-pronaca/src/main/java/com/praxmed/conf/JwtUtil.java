package com.praxmed.conf;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.praxmed.util.Constant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	static void addAuthentication(HttpServletResponse res, String username) {
		Date now = new Date();
		Date exp = new Date(System.currentTimeMillis());
		Calendar fechaExpiracion = GregorianCalendar.getInstance();
		fechaExpiracion.setTime(new Date(System.currentTimeMillis()));
		fechaExpiracion.add(Calendar.MINUTE, Constant.NUM_DIAS_EXPIRACION);
		exp = fechaExpiracion.getTime();
		String token = Jwts.builder().setIssuedAt(now).setNotBefore(now)
				// Vamos a asignar un tiempo de expiracion de 1 dia
				.setExpiration(exp).setSubject(username)
				// hash con el que firmaremos la clave
				.signWith(SignatureAlgorithm.HS512, "Passw0rd").compact();
		// agregamos el encabezado al token
		res.addHeader("Authorization", "Bearer " + token);
	}

	static Authentication ghetAuthentication(HttpServletRequest request) {
		// obtenemos el token que viene en el encabezado de la peticion
		String token = request.getHeader("Authorization");

		// si hay un token presente, entonces lo validamos
		if (token != null) {
			String user = Jwts.parser().setSigningKey("Passw0rd").parseClaimsJws(token.replace("Bearer", ""))// este
																												// metodo
																												// es el
																												// qeu
																												// valida
					.getBody().getSubject();
			// Recordamos que para las demas peticiones que no sean /login
			// no requerimos una autenticacion por username/password
			// por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin
			// password
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;
	}
}
