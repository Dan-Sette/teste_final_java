package com.minsait.cadastroCliente.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
public class Endereco {
	
	@NotNull(message = "Rua não pode ser nulo")
	private String rua;

	@NotNull(message = "O número não pode ser nulo")
	private int numero;
	
	@NotNull(message = "CEP não pode ser nulo")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido. Deve estar no formato XXXXX-XXX")
	private String CEP;

	public Endereco() {}

	public Endereco(String rua, int numero, String CEP) {
		this.rua = rua;
		this.numero = numero;
		this.CEP = CEP;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

}
