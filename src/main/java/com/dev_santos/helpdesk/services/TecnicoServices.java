package com.dev_santos.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev_santos.helpdesk.domain.Pessoa;
import com.dev_santos.helpdesk.domain.Tecnico;
import com.dev_santos.helpdesk.domain.dtos.TecnicoDTO;
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

	public Tecnico create(TecnicoDTO tecnico) {
		validarCpfEmail(tecnico);
		return repository.save(new Tecnico(tecnico));
	}

	public Tecnico update(TecnicoDTO tecnico, Integer id) {
		tecnico.setId(id);
		Tecnico entity = findById(id);
		entity = new Tecnico(tecnico);
		return repository.save(entity);
	}

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		if(tecnico.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviços e não pode ser deletado!");
		}
		repository.deleteById(id);
	}
	
	private void validarCpfEmail(TecnicoDTO tecnico){
		Optional<Pessoa> entity = pessoaRepository.findByCpf(tecnico.getCpf());
		if (entity.isPresent() && entity.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		entity = pessoaRepository.findByEmail(tecnico.getEmail());
		if (entity.isPresent() && entity.get().getId() != tecnico.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
