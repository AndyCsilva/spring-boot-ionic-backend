package com.nelioalves.cursomcs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomcs.domain.Cliente;
import com.nelioalves.cursomcs.repositories.ClienteRepository;
import com.nelioalves.cursomcs.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(final Integer id) {
		Optional<Cliente> findById = clienteRepository.findById(id);
		return findById.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id +", Tipo: "+ Cliente.class.getName()));
	}	
	
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}
}
