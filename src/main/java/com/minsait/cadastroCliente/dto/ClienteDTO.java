package com.minsait.cadastroCliente.dto;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.minsait.cadastroCliente.entity.Cliente;
import com.minsait.cadastroCliente.entity.Endereco;

public class ClienteDTO {
	private String nome;
	@Id
	@NotNull(message = "O CPF não pode ser nulo")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF deve conter apenas dígitos numéricos")
	private String cpf;
	
	@NotNull(message = "O telefone não pode ser nulo")
    @Pattern(regexp = "\\([0-9]{2}\\)[0-9]{4,5}-[0-9]{4}", message = "O telefone deve ter o formato (XX)XXXX-XXXX ou (XX)XXXXX-XXXX")
	private String telefone;
	
	@Embedded
	@Valid
	private Endereco endereco;
	
	
	
	public ClienteDTO() {}

	public ClienteDTO(	String nome,
						@NotNull(message = "O CPF não pode ser nulo")
						@Pattern(regexp = "[0-9]{11}", message = "O CPF deve conter apenas dígitos numéricos")
						String cpf,
						@NotNull(message = "O telefone não pode ser nulo")
						@Pattern(regexp = "\\([0-9]{2}\\)[0-9]{4,5}-[0-9]{4}", message = "O telefone deve ter o formato (XX)XXXX-XXXX ou (XX)XXXXX-XXXX")
						String telefone,
						@Valid Endereco endereco,
						@NotNull(message = "Rendimento mensal não pode ser nulo")
						@Positive(message = "Rendimento mensal deve ser positivo")
						BigDecimal rendimentoMensal) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.rendimentoMensal = rendimentoMensal;
	}
	
	public static ClienteDTO retornaCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO(	cliente.getNome(),
												cliente.getCpf(),
												cliente.getTelefone(),
												cliente.getEndereco(),
												cliente.getRendimentoMensal());
		return clienteDTO;
	}
	
	public static Cliente retornaCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente(	clienteDTO.getNome(),
										clienteDTO.getCpf(),
										clienteDTO.getTelefone(),
										clienteDTO.getEndereco(),
										clienteDTO.getRendimentoMensal());
		return cliente;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getRendimentoMensal() {
		return rendimentoMensal;
	}

	public void setRendimentoMensal(BigDecimal rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}

	@NotNull(message = "Rendimento mensal não pode ser nulo")
	@Positive(message = "Rendimento mensal deve ser positivo")
	private BigDecimal rendimentoMensal;
}
