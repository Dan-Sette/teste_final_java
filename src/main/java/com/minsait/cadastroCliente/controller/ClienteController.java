package com.minsait.cadastroCliente.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.cadastroCliente.dto.ClienteDTO;
import com.minsait.cadastroCliente.entity.Cliente;
import com.minsait.cadastroCliente.exception.ClienteNaoEncontradoException;
import com.minsait.cadastroCliente.service.ClienteService;

@RestController
@RequestMapping("api/v1/cadastro/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	ModelMapper modelMapper = new ModelMapper();
	
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO cadastraCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente clienteRequest = ClienteDTO.retornaCliente(clienteDTO);
		Cliente clienteSalvo = this.clienteService.cadastraCliente(clienteRequest);
		
		return ClienteDTO.retornaCliente(clienteSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> retornaTodosOsClientes(){
		List<Cliente> listaDeClientes =  this.clienteService.retornaTodosOsClientes();
		List<ClienteDTO> listaDeClientesDTO =	listaDeClientes.stream()
												.map(cliente -> modelMapper
												.map(cliente, ClienteDTO.class))
												.collect(Collectors.toList());
		
		return ResponseEntity.ok(listaDeClientesDTO);
	}
	
	@GetMapping("/{cpf}")
	public ClienteDTO retornarCliente(@PathVariable String cpf) throws ClienteNaoEncontradoException{
		Optional<Cliente> clienteOptional =  this.clienteService.retornaCliente(cpf);
		Cliente clienteParaRetornar = clienteOptional.get();
		return ClienteDTO.retornaCliente(clienteParaRetornar);
	}
	
	@PutMapping("/{cpf}")
	public ClienteDTO alteraLivro(@PathVariable String cpf, @Valid @RequestBody ClienteDTO cliente) throws ClienteNaoEncontradoException {
		Cliente clienteRequest = ClienteDTO.retornaCliente(cliente);

		Cliente clienteAlterado = this.clienteService.alteraCliente(cpf, clienteRequest);
		return ClienteDTO.retornaCliente(clienteAlterado);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cpf}")
	public void deletaCliente(@PathVariable String cpf) throws ClienteNaoEncontradoException {

		this.clienteService.deletaCliente(cpf);
	}

}
