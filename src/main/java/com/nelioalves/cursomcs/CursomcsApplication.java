package com.nelioalves.cursomcs;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomcs.domain.Categoria;
import com.nelioalves.cursomcs.domain.Cidade;
import com.nelioalves.cursomcs.domain.Cliente;
import com.nelioalves.cursomcs.domain.Endereco;
import com.nelioalves.cursomcs.domain.Estado;
import com.nelioalves.cursomcs.domain.Pagamento;
import com.nelioalves.cursomcs.domain.PagamentoComBoleto;
import com.nelioalves.cursomcs.domain.PagamentoComCartao;
import com.nelioalves.cursomcs.domain.Pedido;
import com.nelioalves.cursomcs.domain.Produto;
import com.nelioalves.cursomcs.domain.enums.EstadoPagamento;
import com.nelioalves.cursomcs.domain.enums.TipoCliente;
import com.nelioalves.cursomcs.repositories.CategoriaRepository;
import com.nelioalves.cursomcs.repositories.CidadeRepository;
import com.nelioalves.cursomcs.repositories.ClienteRepository;
import com.nelioalves.cursomcs.repositories.EnderecoRepository;
import com.nelioalves.cursomcs.repositories.EstadoRepository;
import com.nelioalves.cursomcs.repositories.PagamentoRepository;
import com.nelioalves.cursomcs.repositories.PedidoRepository;
import com.nelioalves.cursomcs.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcsApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");

		// cria os produtos e persiste, depois associa
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

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria.silva@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardins", "38220834", cli1, c1);
		Endereco end2 = new Endereco(null, "Avendida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
	}
}
