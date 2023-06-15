package com.econsulting.vendas.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name="produtos")
public class Produto {
	
	@Column(name="id_produto")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="descricao", nullable = false)
	private String descricao;
	@Column(name="data_cadastro")
	private Date dataCadastro;
	@Column(name="preco_venda")
	private double precoVenda;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "produto")
	private EspecificacaoProduto especificacaoProduto;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return id == other.id;
	}
	
	public Produto() {
		super();
	}

	public Produto(Long id, String descricao, Date dataCadastro, double precoVenda) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.precoVenda = precoVenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EspecificacaoProduto getEspecificacaoProduto() {
		return especificacaoProduto;
	}

	public void setEspecificacaoProduto(EspecificacaoProduto especificacaoProduto) {
		this.especificacaoProduto = especificacaoProduto;
	}
	

}
