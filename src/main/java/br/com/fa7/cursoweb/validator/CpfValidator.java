package br.com.fa7.cursoweb.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.fa7.cursoweb.util.ValidacaoHelper;

@FacesValidator("validators.CpfValidator")
public class CpfValidator implements Validator {
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value != null && !"".equals(value)) {
			String valor = value.toString();
			if (!ValidacaoHelper.validaCpf(valor)) {
				throwException();
			}
		} else {
			throwException();
		}
	}

	private void throwException() {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setSummary("Erro de Validação");
		message.setDetail("CPF Inválido");
		throw new ValidatorException(message);
	}
}
