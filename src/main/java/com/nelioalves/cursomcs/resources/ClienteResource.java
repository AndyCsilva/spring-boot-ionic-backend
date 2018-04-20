package com.nelioalves.cursomcs.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomcs.domain.Cliente;
import com.nelioalves.cursomcs.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> listar() {
		return clienteService.buscarTodos();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id) {
		Cliente cliente = clienteService.buscar(id);
		return ResponseEntity.ok(cliente);
	}
}
