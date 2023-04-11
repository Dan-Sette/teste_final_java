package com.minsait.cadastroCliente.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.minsait.cadastroCliente.entity.Cliente;
import com.minsait.cadastroCliente.entity.Emprestimo;
import com.minsait.cadastroCliente.relacionamento.Relacionamento;

public class EmprestimoDTO {

	@NotNull(message = "CPF do cliente não pode ser nullo")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF do cliente deve conter apenas dígitos numéricos")
	private String CPFCliente;

	@NotNull(message = "Valor do empréstimo não pode ser nullo")
	private BigDecimal valorInicial;

	private BigDecimal valorFinal;

	@NotNull(message = "O relacionamento não pode ser nullo")
	private Relacionamento relacionamento;

	private LocalDateTime dataInicial;
	private LocalDateTime dataFinal;
	private Cliente cliente;

	public EmprestimoDTO() {}
	
	public EmprestimoDTO(
			@NotNull(message = "CPF do cliente não pode ser nullo")
			@Pattern(regexp = "[0-9]{11}", message = "O CPF do cliente deve conter apenas dígitos numéricos")
			String cpfCliente,
			@NotNull(message = "Valor do empréstimo não pode ser nullo")
			BigDecimal valorInicial,
			BigDecimal valorFinal,
			@NotNull(message = "O relacionamento não pode ser nullo")
			Relacionamento relacionamento,
			LocalDateTime dataInicial,
			LocalDateTime dataFinal,
			Cliente cliente) {
		super();
		this.CPFCliente = cpfCliente;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
		this.relacionamento = relacionamento;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.cliente = cliente;
	}
	
	public EmprestimoDTO retornaEmprestimo(Emprestimo emprestimo) {
		EmprestimoDTO emprestimoDTO = new EmprestimoDTO(
											emprestimo.getCpfCliente(),
											emprestimo.getValorInicial(),
											emprestimo.getValorFinal(),
											emprestimo.getRelacionamento(),
											emprestimo.getDataInicial(),
											emprestimo.getDataFinal(),
											emprestimo.getCliente());
		return emprestimoDTO;
	}

	public Emprestimo retornaEmprestimo(EmprestimoDTO emprestimoDTO) {
		Emprestimo emprestimo = new Emprestimo(
										emprestimoDTO.getCpfCliente(),
										emprestimoDTO.getValorInicial(),
										emprestimoDTO.getValorFinal(),
										emprestimoDTO.getRelacionamento(),
										emprestimoDTO.getDataInicial(),
										emprestimoDTO.getDataFinal(),
										emprestimoDTO.getCliente());
		return emprestimo;
	}

	public String getCpfCliente() {
		return CPFCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.CPFCliente = cpfCliente;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}

	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
