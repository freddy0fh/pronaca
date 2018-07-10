package com.praxmed.clinica.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.praxmed.clinica.entity.PsUser;

@Repository
public interface PsUserRepo extends JpaRepository<PsUser, Long>, PagingAndSortingRepository<PsUser, Long> {

	public PsUser findByUsuarioAndActivo(String usuario,Boolean activo);
}
