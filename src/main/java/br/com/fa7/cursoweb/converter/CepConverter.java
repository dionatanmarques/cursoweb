package br.com.fa7.cursoweb.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.fa7.cursoweb.exception.CepException;
import br.com.fa7.cursoweb.model.Cep;

@FacesConverter("converters.CepConverter")
public class CepConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		if (value != null && !"".equals(value.trim())) {
			String cep = value.replaceAll("\\-", "");
			try {
				// Nao permite letras
				Long.valueOf(cep);
				// Verifica se tem 8 digitos (5 para regiao e 3 para sufixo)
				if (cep.length() == 8) {
					return new Cep(cep.substring(0, 5), cep.substring(5, 8));
				}
				throw new CepException();
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"O valor informado não é um número de CEP!");
				throw new ConverterException(message);
			} catch (CepException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"O CEP deve conter 8 digitos");
				throw new ConverterException(message);
			}
		}
		return value;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		Cep cep = (value == null ? null : (Cep) value);
		if (cep != null) {
			return cep.getRegiao()+"-"+cep.getSufixo();
		}
		return null;
	}

}
