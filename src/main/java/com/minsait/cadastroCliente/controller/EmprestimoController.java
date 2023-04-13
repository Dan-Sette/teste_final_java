package com.minsait.cadastroCliente.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.cadastroCliente.dto.ClienteDTO;
import com.minsait.cadastroCliente.dto.EmprestimoDTO;
import com.minsait.cadastroCliente.dto.EmprestimoDTOResponse;
import com.minsait.cadastroCliente.entity.Cliente;
import com.minsait.cadastroCliente.entity.Emprestimo;
import com.minsait.cadastroCliente.exception.ClienteNaoEncontradoException;
import com.minsait.cadastroCliente.exception.EmprestimoForaDoLimiteException;
import com.minsait.cadastroCliente.exception.EmprestimoNaoEncontradoException;
import com.minsait.cadastroCliente.service.EmprestimoService;

@RestController
@RequestMapping("/api/v1/financeira/clientes/")
public class EmprestimoController {

	private EmprestimoService emprestimoService;
	private ClienteController clienteController;
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public EmprestimoController(EmprestimoService emprestimoService, ClienteController clienteController) {
		this.emprestimoService = emprestimoService;
		this.clienteController = clienteController;
	}
	
	@PostMapping("{CPFCliente}/emprestimos")
	@ResponseStatus(HttpStatus.CREATED)
	public EmprestimoDTO cadastraEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDTO) throws ClienteNaoEncontradoException, EmprestimoForaDoLimiteException {
		Emprestimo emprestimo = emprestimoDTO.retornaEmprestimo(emprestimoDTO);
		Optional<ClienteDTO> clienteDTOOptional = Optional.ofNullable(this.clienteController.retornarCliente(emprestimo.getCpfCliente()));
		ClienteDTO clienteDTO = clienteDTOOptional.get();
		Cliente cliente = ClienteDTO.retornaCliente(clienteDTO);
		Emprestimo emprestimoParaRetorno;
		this.emprestimoService.calculaValorTotalEmprestimosPorCliente(cliente, emprestimo.getValorInicial());
		if(clienteDTOOptional.isPresent()) {
			emprestimo.setCliente(cliente);
			emprestimo.setDataInicial(LocalDateTime.now());
			emprestimo.setDataFinal(emprestimo.getDataInicial().plusDays(60));
			emprestimo.setRelacionamento(emprestimoDTO.getRelacionamento());
			Long numeroDeEmprestimos = this.emprestimoService.contaTodosOsEmprestimosPorCPF(emprestimo.getCpfCliente());
			emprestimo.fazCalculoEmprestimo(numeroDeEmprestimos);
			emprestimoParaRetorno = this.emprestimoService.cadastraEmprestimo(emprestimo);

		} else {
			throw new ClienteNaoEncontradoException(emprestimo.getCpfCliente());
		}
		return emprestimoDTO.retornaEmprestimo(emprestimoParaRetorno);
	}
	
	@GetMapping("/{CPFCliente}/emprestimos")
	public ResponseEntity<List<EmprestimoDTO>> retornaTodosOsEmprestimosPorCPF(@PathVariable String CPFCliente) {
		List<Emprestimo> listaDeEmprestimos =  this.emprestimoService.retornaTodosOsEmprestimos(CPFCliente);
		List<EmprestimoDTO> listaDeEmprestimoDTO =	listaDeEmprestimos.stream()
												.map(cliente -> modelMapper
												.map(cliente, EmprestimoDTO.class))
												.collect(Collectors.toList());
		
		return ResponseEntity.ok(listaDeEmprestimoDTO);
	}
	
	@GetMapping("/{CPFCliente}/emprestimos/{id}")
	public EmprestimoDTOResponse retornaEmprestimo(@PathVariable String CPFCliente,@PathVariable Long id) throws EmprestimoNaoEncontradoException {
		
		EmprestimoDTOResponse emprestimoDTOResponse = this.emprestimoService.transformaEntidadeEmDTOResponse(id);
		return emprestimoDTOResponse;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{CPFCliente}/emprestimos/{id}")
	public void deletaEmprestimo(@PathVariable Long id) throws EmprestimoNaoEncontradoException {

		this.emprestimoService.deletaEmprestimo(id);
	}

}
