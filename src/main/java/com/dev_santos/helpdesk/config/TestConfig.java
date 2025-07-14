package com.dev_santos.helpdesk.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dev_santos.helpdesk.domain.Chamado;
import com.dev_santos.helpdesk.domain.Cliente;
import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.domain.enums.Perfil;
import com.dev_santos.helpdesk.domain.enums.Prioridade;
import com.dev_santos.helpdesk.domain.enums.Status;
import com.dev_santos.helpdesk.repositories.ChamadoRepository;
import com.dev_santos.helpdesk.repositories.ClienteRepository;
import com.dev_santos.helpdesk.repositories.TecnicoRepository;

@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Nahtanm Carvalho", "09876543200", "Nahtanm@gmail.com", "123456");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Mike brown", "27192837189", "mike@gmail.com", "027312");
		
		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", cli1, tec1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
		
	}

}
