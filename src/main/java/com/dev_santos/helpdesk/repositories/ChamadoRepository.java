package com.dev_santos.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev_santos.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
