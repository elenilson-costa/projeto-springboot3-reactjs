package com.econsulting.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.econsulting.vendas.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Modifying
	@Query(value = "INSERT INTO movimento_estoque (tp_operacao, id_item_pedido, id_produto, qt_movimento) VALUES (:tpOperacao, :idItemPedido, :idProduto, :qtMovimento)"
			, nativeQuery = true)
	void movimentoEstoque(@Param("tpOperacao") String tpOperacao, @Param("idItemPedido") Long idItemPedido, @Param("idProduto") Long idProduto, @Param("qtMovimento") int qtMovimento);

}
