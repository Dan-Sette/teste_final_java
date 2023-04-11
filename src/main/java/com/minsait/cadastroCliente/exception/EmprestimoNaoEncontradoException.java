package com.minsait.cadastroCliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmprestimoNaoEncontradoException extends Exception{
	private static final long serialVersionUID = 1L;

	public EmprestimoNaoEncontradoException() {
		super(String.format("Emprestimo não foi encontrado"));
	}
}
