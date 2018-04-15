package com.nelioalves.cursomcs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomcs.domain.Categoria;
import com.nelioalves.cursomcs.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(final Integer id) {
		Optional<Categoria> findById = categoriaRepository.findById(id);
		return findById.orElse(null);	
	}
	
	public List<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}
}
