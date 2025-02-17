package com.lab.hospital.resources.exceptions;

public class PacienteNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PacienteNaoEncontradoException(String message) {
        super(message);
    }
}
