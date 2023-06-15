package com.econsulting.vendas.repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.econsulting.vendas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE ativo = 'S'")
	List<Cliente> findClientesAtivos();
	
	@Query(value = "SELECT * FROM clientes c WHERE date_format(c.dt_cadastro, '%Y-%m-%d') BETWEEN date_format(:dataInicial, '%Y-%m-%d') AND date_format(:dataFinal, '%Y-%m-%d')", 
			  nativeQuery = true)
	List<Cliente> findByDataCadastro(@Param("dataInicial") Instant dataInicial, @Param("dataFinal") Instant dataFinal);
	
//	@Modifying
//	@Query("update User u set u.status = :status where u.name = :name")
//	int updateUserSetStatusForName(@Param("status") Integer status, 
//	  @Param("name") String name);
}
