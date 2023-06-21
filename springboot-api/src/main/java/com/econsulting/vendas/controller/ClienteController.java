package com.econsulting.vendas.controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.econsulting.vendas.model.Cliente;
import com.econsulting.vendas.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(service.create(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente){
		try {
			return new ResponseEntity<Cliente>( service.update(cliente), HttpStatus.OK ) ;
		} catch (Exception e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		try{
			Cliente cliente = service.findById(id);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}
		
	@GetMapping("/findAll")
	public List<Cliente> findAll() {
		return service.findAll();
	}
	
	@GetMapping("findAtivos")
	public List<Cliente> findClientesAtivos(){
		return service.findClientesAtivos();
	}
	
	@GetMapping("findByDataCadastro/{dataInicial}/{dataFinal}")
	public List<Cliente> findByDataCadastro(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date dataInicial
			                                , @PathVariable @DateTimeFormat(iso = ISO.DATE) Date dataFinal){
		return service.findByDataCadastro(dataInicial.toInstant(), dataFinal.toInstant());
	}
	
	
}
