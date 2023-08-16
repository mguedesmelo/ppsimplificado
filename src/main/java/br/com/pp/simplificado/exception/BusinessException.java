package br.com.pp.simplificado.exception;

import br.com.pp.simplificado.configuration.MessageResources;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5271264973005114027L;

	public BusinessException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		// TODO Testar mudando o locale
		return MessageResources.getText(super.getMessage());
	}
}
