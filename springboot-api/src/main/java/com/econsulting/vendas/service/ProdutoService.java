package com.econsulting.vendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econsulting.vendas.model.EspecificacaoProduto;
import com.econsulting.vendas.model.Produto;
import com.econsulting.vendas.repository.EspecificacaoProdutoRespository;
import com.econsulting.vendas.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private EspecificacaoProdutoRespository especificacaoProdutoRespository;
	
	public Produto create(Produto produto) {
		
		Produto result = repository.save(produto);
		
		result.getEspecificacaoProduto().setProduto(result);
		
		EspecificacaoProduto especificacaoProduto = especificacaoProdutoRespository.save(result.getEspecificacaoProduto());
		
		produto.setEspecificacaoProduto(especificacaoProduto);
		
		return repository.save(produto);
		
	}

	public Produto update(Produto produto) {
		@SuppressWarnings("unused")
		Produto result = repository.findById(produto.getId()).get();
		return repository.save(produto);		
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Produto> findAll(){
		return repository.findAll();
	}
	
	public Produto findById(Long id) {
		return repository.findById(id).get();
	}
	
}
