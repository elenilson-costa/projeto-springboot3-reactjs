package com.econsulting.vendas.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.econsulting.vendas.model.Produto;
import com.econsulting.vendas.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> create(@RequestBody Produto produto) {
		return new ResponseEntity<Produto>(service.create(produto), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto){
		try {
			return new ResponseEntity<Produto>( service.update(produto), HttpStatus.OK ) ;
		} catch (Exception e) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		try{
			Produto produto = service.findById(id);
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
	}
		
	@GetMapping("/findAll")
	public List<Produto> findAll() {
		return service.findAll();
	}
	
	
}
