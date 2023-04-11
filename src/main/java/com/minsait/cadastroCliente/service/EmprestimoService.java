package com.minsait.cadastroCliente.service;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.cadastroCliente.controller.MensagemDeRetorno;
import com.minsait.cadastroCliente.dto.EmprestimoDTOResponse;
import com.minsait.cadastroCliente.entity.Cliente;
import com.minsait.cadastroCliente.entity.Emprestimo;
import com.minsait.cadastroCliente.exception.EmprestimoForaDoLimiteException;
import com.minsait.cadastroCliente.exception.EmprestimoNaoEncontradoException;
import com.minsait.cadastroCliente.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	private EmprestimoRepository emprestimoRepository;
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@Autowired
	public EmprestimoService(EmprestimoRepository emprestimoRepository) {

		this.emprestimoRepository = emprestimoRepository;
	}
	
	public EmprestimoDTOResponse transformaEntidadeEmDTOResponse(Long id) throws EmprestimoNaoEncontradoException {
		Emprestimo emprestimo = this.emprestimoRepository.findById(id)
				.orElseThrow(() -> new EmprestimoNaoEncontradoException());
		
		EmprestimoDTOResponse emprestimoDTOResponse = modelMapper.map(emprestimo, EmprestimoDTOResponse.class);
		
		return emprestimoDTOResponse;
	}

	public Emprestimo cadastraEmprestimo(Emprestimo emprestimo) {
		return this.emprestimoRepository.save(emprestimo);
	}

	
	public List<Emprestimo> retornaTodosOsEmprestimos(String cpf) {

		return this.emprestimoRepository.findAllByCpfCliente(cpf);
	}

	
	public Long contaTodosOsEmprestimosPorCPF(String CPFCliente) {
		return this.emprestimoRepository.countByCpfCliente(CPFCliente);
	}
	
	public BigDecimal calculaValorTotalEmprestimosPorCliente(Cliente cliente, BigDecimal emprestimoAtual) throws EmprestimoForaDoLimiteException {
		BigDecimal somaEmprestimos = this.emprestimoRepository.calculaValorTotalEmprestimosPorCliente(cliente.getCpf());
		if(somaEmprestimos == null) {
			somaEmprestimos = new BigDecimal(0);
		}
		somaEmprestimos.add(emprestimoAtual);
		BigDecimal fatorMultiplicador = new BigDecimal(10);
		BigDecimal limiteEmprestimosCliente = cliente.getRendimentoMensal().multiply(fatorMultiplicador);
		int comparacao = limiteEmprestimosCliente.compareTo(somaEmprestimos);
		if(comparacao < 0) {
			throw new EmprestimoForaDoLimiteException();
		}		
		return somaEmprestimos;
	}

	public MensagemDeRetorno deletaEmprestimo(Long id) throws EmprestimoNaoEncontradoException {
		if (this.emprestimoRepository.existsById(id)) {			
			this.emprestimoRepository.deleteById(id);
			MensagemDeRetorno mensagemDeRetorno = new MensagemDeRetorno();
			mensagemDeRetorno.setMensagem("Deletado com sucesso!");
			return mensagemDeRetorno;
		}
		
		throw new EmprestimoNaoEncontradoException();
	}

}
