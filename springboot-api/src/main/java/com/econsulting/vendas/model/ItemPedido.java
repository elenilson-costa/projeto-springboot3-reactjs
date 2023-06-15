package com.econsulting.vendas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name="itens_pedido")
@Table(name="itens_pedido")
public class ItemPedido{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_item_pedido", updatable = false, nullable = false)
	private Long idItemPedido;
	@Column(name="id_produto")
	private Long idProduto;
	@Column(name="quantidade")
	private int quantidade;
	@Column(name="preco_venda")
	private double precoVenda;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idPedido")
	private Pedido pedido;

	
	public ItemPedido() {
		super();
	}


	public Long getIdItemPedido() {
		return idItemPedido;
	}


	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}


	public Long getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public double getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	
}
