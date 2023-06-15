package com.econsulting.vendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econsulting.vendas.model.ItemPedido;
import com.econsulting.vendas.model.Pedido;
import com.econsulting.vendas.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	public Pedido create(Pedido pedido) {
		
		Pedido result = repository.save(pedido);

		result.getItensPedido().forEach((it) -> it.setPedido(result));
		
		Pedido saved = repository.save(result);

		for (ItemPedido item : saved.getItensPedido()) {
			repository.movimentoEstoque("SAIDA", item.getIdItemPedido(), item.getIdProduto(), item.getQuantidade());
		}
		
		return saved;

	}

	public Pedido update(Pedido pedido) {
		@SuppressWarnings("unused")
		Pedido result = findById(pedido.getIdPedido());
		return repository.save(pedido);		
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Pedido> findAll(){
		return repository.findAll();
	}
	
	public Pedido findById(Long id) {
		return repository.findById(id).get();
	}
	
}
