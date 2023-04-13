package com.minsait.cadastroCliente.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@Entity
public class Cliente {

	private String nome;
	@Id
	@NotNull(message = "O CPF não pode ser nulo")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF deve conter apenas dígitos numéricos")
	private String cpf;

	@NotNull(message = "O telefone não pode ser nulo")
	private String telefone;
	
	@Embedded
	@Valid
	private Endereco endereco;
	
	@NotNull(message = "Rendimento mensal não pode ser nulo")
	@Positive(message = "Rendimento mensal deve ser positivo")
	private BigDecimal rendimentoMensal;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Emprestimo> emprestimo;
    
	public Cliente() {}
	
    public Cliente(String nome, String cpf, String telefone, Endereco endereco, BigDecimal rendimentoMensal) {
    	this.nome = nome;
    	this.cpf = cpf;
    	this.telefone = telefone;
    	this.endereco = endereco;
    	this.rendimentoMensal = rendimentoMensal;
    }

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	

	public BigDecimal getRendimentoMensal() {
		return rendimentoMensal;
	}

	public void setRendimentoMensal(BigDecimal rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
    
    
}
