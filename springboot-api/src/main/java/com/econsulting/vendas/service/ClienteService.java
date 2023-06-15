package com.econsulting.vendas.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econsulting.vendas.model.Cliente;
import com.econsulting.vendas.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente create(Cliente cliente) {
		return repository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		@SuppressWarnings("unused")
		Cliente result = repository.findById(cliente.getIdCliente()).get();
		return repository.save(cliente);		
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Cliente findById(Long id) {
		return repository.findById(id).get();
	}
	
	public List<Cliente> findClientesAtivos(){
		return repository.findClientesAtivos();
	}
	
	public List<Cliente> findByDataCadastro(Instant dataInicial, Instant dataFinal){
		return repository.findByDataCadastro(dataInicial, dataFinal);
	}
	
}
