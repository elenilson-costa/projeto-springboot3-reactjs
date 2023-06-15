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

import com.econsulting.vendas.model.Pedido;
import com.econsulting.vendas.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
		return new ResponseEntity<Pedido>(service.create(pedido), HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Pedido> update(@RequestBody Pedido pedido){
		try {
			return new ResponseEntity<Pedido>( service.update(pedido), HttpStatus.OK ) ;
		} catch (Exception e) {
			return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		try{
			Pedido pedido = service.findById(id);
			return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
		}
	}
		
	@GetMapping("/findAll")
	public List<Pedido> findAll() {
		return service.findAll();
	}
	
	
}
