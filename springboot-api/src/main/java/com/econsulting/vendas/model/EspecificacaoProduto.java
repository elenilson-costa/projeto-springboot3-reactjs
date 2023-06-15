package com.econsulting.vendas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EspecificacaoProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_especificacao_produto")
	private int idEspecificacaoProduto;
	@Column(name = "ds_dimensoes")
	private String dimensoes;
	@Column(name = "vl_peso")
	private double peso;
	@Column(name = "ds_cor")
	private String cor;
	@Column(name = "vl_dias_validade")
	private int diasValidade;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProduto")
	private Produto produto;

	public EspecificacaoProduto() {
		super();
	}

	public int getIdEspeficacaoProduto() {
		return idEspecificacaoProduto;
	}

	public void setIdEspeficacaoProduto(int idEspeficacaoProduto) {
		this.idEspecificacaoProduto = idEspeficacaoProduto;
	}

	public String getDimensoes() {
		return dimensoes;
	}

	public void setDimensoes(String dimensoes) {
		this.dimensoes = dimensoes;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getDiasValidade() {
		return diasValidade;
	}

	public void setDiasValidade(int diasValidade) {
		this.diasValidade = diasValidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	

}
