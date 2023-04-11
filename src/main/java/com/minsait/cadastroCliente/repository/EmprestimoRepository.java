package com.minsait.cadastroCliente.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.minsait.cadastroCliente.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

	@Query("SELECT COUNT(e) FROM Emprestimo e WHERE e.cliente.cpf = :cpfCliente")
	Long countByCpfCliente(@Param("cpfCliente") String cpfCliente);
	
	@Query("SELECT SUM(e.valorInicial) FROM Emprestimo e WHERE e.cliente.cpf = :cpfCliente")
	BigDecimal calculaValorTotalEmprestimosPorCliente(@Param("cpfCliente") String cpfCliente);

	@Query("SELECT e FROM Emprestimo e WHERE e.cliente.cpf = :cpf")
	List<Emprestimo> findAllByCpfCliente(@Param("cpf") String cpf);
}
