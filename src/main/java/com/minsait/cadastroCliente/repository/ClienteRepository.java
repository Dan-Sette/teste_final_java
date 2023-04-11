package com.minsait.cadastroCliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.cadastroCliente.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
	Optional<Cliente> findById(String cpf);
}
