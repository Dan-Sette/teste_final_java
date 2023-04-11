package com.minsait.cadastroCliente.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.cadastroCliente.controller.MensagemDeRetorno;
import com.minsait.cadastroCliente.entity.Cliente;
import com.minsait.cadastroCliente.exception.ClienteNaoEncontradoException;
import com.minsait.cadastroCliente.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
	
		this.clienteRepository = clienteRepository;
	}

	public Cliente cadastraCliente(Cliente cliente) {
		
		return this.clienteRepository.save(cliente);
	}

	public List<Cliente> retornaTodosOsClientes() {

		return this.clienteRepository.findAll();
	}

	public Optional<Cliente> retornaCliente(String cpf) throws ClienteNaoEncontradoException {
		
		if (this.clienteRepository.existsById(cpf)) {
			return this.clienteRepository.findById(cpf);			
		}
		throw new ClienteNaoEncontradoException(cpf);
	}

	public @Valid Cliente alteraCliente(String cpf,@Valid Cliente cliente) throws ClienteNaoEncontradoException {
		Optional<Cliente> clienteOptional = this.clienteRepository.findById(cpf);
		if (this.clienteRepository.existsById(cpf) && clienteOptional.isPresent()) {
			Cliente clienteASerAlterado = clienteOptional.get();
			cliente.setCpf(cpf);
			if(cliente.getNome() == null) {
				cliente.setNome(clienteASerAlterado.getNome());
			}
			return this.clienteRepository.save(cliente);			
		}
		throw new ClienteNaoEncontradoException(cpf);
	}

	public MensagemDeRetorno deletaCliente(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsById(cpf)) {			
			this.clienteRepository.deleteById(cpf);
			MensagemDeRetorno mensagemDeRetorno = new MensagemDeRetorno();
			mensagemDeRetorno.setMensagem("Deletado com sucesso!");
			return mensagemDeRetorno;
		}
		throw new ClienteNaoEncontradoException(cpf);
	}
}
