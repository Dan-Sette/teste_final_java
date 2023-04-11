package com.minsait.cadastroCliente.relacionamento;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Relacionamento {

	Bronze(1) {
		@Override
		public BigDecimal calculaValorFinalEmprestimo(BigDecimal valorInicial, Long numeroDeEmprestimos) {
			BigDecimal fatorMultiplicador = new BigDecimal(1.80); 
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	},

	Prata(2) {
		@Override
		public BigDecimal calculaValorFinalEmprestimo(BigDecimal valorInicial, Long numeroDeEmprestimos) {
			int comparacao = valorInicial.compareTo(new BigDecimal("5000.00"));
			BigDecimal fatorMultiplicador;
			if(comparacao > 0) {
				fatorMultiplicador = new BigDecimal(1.40);
			} else {
				fatorMultiplicador = new BigDecimal(1.60);
			}
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	},

	Ouro(3) {
		@Override
		public BigDecimal calculaValorFinalEmprestimo(BigDecimal valorInicial, Long numeroDeEmprestimos) {
			BigDecimal fatorMultiplicador;
			if(numeroDeEmprestimos <= 1) {
				fatorMultiplicador = new BigDecimal(1.2);				
			} else {
				fatorMultiplicador = new BigDecimal(1.3);
			}
			return valorInicial.multiply(fatorMultiplicador, MathContext.DECIMAL32);
		}
	};

	private int codigo;
	
	private Relacionamento(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public abstract BigDecimal calculaValorFinalEmprestimo(BigDecimal valorInicial, Long numeroDeEmprestimos);
}
