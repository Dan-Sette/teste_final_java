package com.minsait.cadastroCliente.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.minsait.cadastroCliente.relacionamento.Relacionamento;

public class EmprestimoDTOResponse {

	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	private LocalDateTime dataInicial;
	private LocalDateTime dataFinal;
	private Relacionamento relacionamento;
	
	public EmprestimoDTOResponse() {}

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
