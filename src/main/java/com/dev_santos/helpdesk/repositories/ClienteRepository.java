package com.dev_santos.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev_santos.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}