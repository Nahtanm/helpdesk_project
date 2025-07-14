package com.dev_santos.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev_santos.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
