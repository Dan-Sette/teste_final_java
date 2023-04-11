package com.minsait.cadastroCliente.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.minsait.cadastroCliente.relacionamento.Relacionamento;

@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "CPF do cliente não pode ser nullo")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF do cliente deve conter apenas dígitos numéricos")
	private String CPFCliente;
	
	@NotNull(message = "Valor do empréstimo não pode ser nullo")
	private BigDecimal valorInicial;

	private BigDecimal valorFinal;
	
	@NotNull(message = "O relacionamento não pode ser nullo")

	private LocalDateTime dataInicial;
	private LocalDateTime dataFinal;
	
	@ManyToOne
	@JoinColumn(name= "cpfClienteId")
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
	private Relacionamento relacionamento;


	public Emprestimo() {}

	public Emprestimo(String cpfCliente, BigDecimal valorInicial, BigDecimal valorFinal,
			Relacionamento relacionamento, LocalDateTime dataInicial, LocalDateTime dataFinal, Cliente cliente) {
		this.CPFCliente = cpfCliente;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
		this.relacionamento = relacionamento;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.cliente = cliente;
	}
	
	public void fazCalculoEmprestimo(Long numeroDeEmprestimos) {

		switch(this.getRelacionamento()) {
			case Bronze:
				this.valorFinal = Relacionamento.Bronze.calculaValorFinalEmprestimo(this.valorInicial, numeroDeEmprestimos); 
				break;
			case Prata:
				this.valorFinal = Relacionamento.Prata.calculaValorFinalEmprestimo(this.valorInicial, numeroDeEmprestimos);
				break;
			case Ouro:
				this.valorFinal = Relacionamento.Ouro.calculaValorFinalEmprestimo(this.valorInicial, numeroDeEmprestimos);
				break;
		}
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Relacionamento getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(Relacionamento relacionamento) {
		this.relacionamento = relacionamento;
	}

}
