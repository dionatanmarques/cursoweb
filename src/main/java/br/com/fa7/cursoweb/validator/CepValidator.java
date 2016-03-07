package br.com.fa7.cursoweb.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.fa7.cursoweb.model.Cep;

@FacesValidator("validators.CepValidator")
public class CepValidator implements Validator {

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value != null && !"".equals(value)) {
			Cep cep = (Cep)value;
			if (cep.getRegiao().length() != 5 || cep.getSufixo().length() != 3) {
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
		message.setDetail("CEP Inválido");
		throw new ValidatorException(message);
	}
}