package com.nelioalves.cursomcs.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomcs.domain.Categoria;
import com.nelioalves.cursomcs.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		return categoriaService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable("id") Integer id) {
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok(categoria);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable("id") Integer id) {
		obj.setId(id);
		obj = categoriaService.update(obj);
 		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
 		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		categoriaService.delete(id);
 		return ResponseEntity.noContent().build();
	}
}
