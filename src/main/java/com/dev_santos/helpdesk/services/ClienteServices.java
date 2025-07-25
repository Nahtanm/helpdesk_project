package com.dev_santos.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev_santos.helpdesk.domain.Cliente;
import com.dev_santos.helpdesk.domain.Pessoa;
import com.dev_santos.helpdesk.domain.dtos.ClienteDTO;
import com.dev_santos.helpdesk.repositories.ClienteRepository;
import com.dev_santos.helpdesk.repositories.PessoaRepository;
import com.dev_santos.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.dev_santos.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findByid(Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
	}

	public Cliente create(Cliente cliente) {
		validarCpfEmail(cliente);
		return clienteRepository.save(cliente);
	}

	public void delete(Integer id) {
		Cliente cliente = findByid(id);
		if (cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}
		clienteRepository.deleteById(id);
	}

	public Cliente update(ClienteDTO clienteDTO, Integer id) {
		clienteDTO.setId(id);
		Cliente entity = findByid(id);
		entity = new Cliente(clienteDTO);
		return clienteRepository.save(entity);
	}

	private void validarCpfEmail(Cliente cliente) {
		Optional<Pessoa> entity = pessoaRepository.findByCpf(cliente.getCpf());
		if (entity.isPresent()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		entity = pessoaRepository.findByEmail(cliente.getEmail());
		if (entity.isPresent()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

}
