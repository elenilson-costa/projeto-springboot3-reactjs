package com.econsulting.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.econsulting.vendas.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
