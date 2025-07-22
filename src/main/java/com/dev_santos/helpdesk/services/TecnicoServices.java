package com.dev_santos.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev_santos.helpdesk.domain.Pessoa;
import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.repositories.PessoaRepository;
import com.dev_santos.helpdesk.repositories.TecnicoRepository;
import com.dev_santos.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoServices {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(Tecnico tecnico) {
		validarCpfEmail(tecnico);
		return repository.save(tecnico);

	}

	private void validarCpfEmail(Tecnico tecnico) throws RuntimeException{
		Optional<Pessoa> entity = pessoaRepository.findByCpf(tecnico.getCpf());
		if(entity.isPresent() && entity.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		entity = pessoaRepository.findByEmail(tecnico.getEmail());
		if(entity.isPresent() && entity.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
