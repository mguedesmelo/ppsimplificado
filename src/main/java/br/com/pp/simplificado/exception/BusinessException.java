package br.com.pp.simplificado.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5271264973005114027L;
    private Locale locale = LocaleContextHolder.getLocale();

	@Autowired
    private ResourceBundleMessageSource source;

	public BusinessException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		// TODO Habilitar i18n
//		return this.source.getMessage(super.getMessage(), null, locale);
		return super.getMessage();
	}
}
