package com.nelioalves.cursomcs;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomcs.domain.Categoria;
import com.nelioalves.cursomcs.domain.Produto;
import com.nelioalves.cursomcs.repositories.CategoriaRepository;
import com.nelioalves.cursomcs.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcsApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");
		
		
		//cria os produtos e persiste, depois associa
		Produto p1 = new Produto(null, "Computador", 2000D);
		Produto p2 = new Produto(null, "Impressora", 800D);
		Produto p3 = new Produto(null, "Mouse", 80D);
		
		informatica.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		escritorio.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(informatica);
		p2.getCategorias().add(informatica);
		p3.getCategorias().add(informatica);
		
		p2.getCategorias().add(escritorio);
			
		List<Categoria> categorias = Arrays.asList(informatica, escritorio);
		List<Produto> produtos = Arrays.asList(p1, p2, p3);
		
		categoriaRepository.saveAll(categorias);		
		produtoRepository.saveAll(produtos);
	}
}
