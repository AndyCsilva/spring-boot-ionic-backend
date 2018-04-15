package com.nelioalves.cursomcs;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomcs.domain.Categoria;
import com.nelioalves.cursomcs.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcsApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");
		
		List<Categoria> categorias = Arrays.asList(informatica, escritorio);
		categoriaRepository.saveAll(categorias);		
	}
}
