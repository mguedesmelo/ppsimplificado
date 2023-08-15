package br.com.pp.simplificado.exception;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5271264973005114027L;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Habilitar i18n
	}

	public BusinessException(String message) {
		super(message);
		// TODO Habilitar i18n
	}
}
