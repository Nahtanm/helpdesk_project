package com.dev_santos.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev_santos.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
