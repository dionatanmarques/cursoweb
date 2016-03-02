package br.com.fa7.cursoweb.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.fa7.cursoweb.model.Cep;

@FacesConverter("converters.CepConverter")
public class CepConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		if (value != null && !value.equals("")) {
			String[] cepPartes = value.split("-");
			try {
				// Testa se somente existem numeros.
				Long.valueOf(cepPartes[0]);
				Long.valueOf(cepPartes[1]);
				return new Cep(cepPartes[0], cepPartes[1]);
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"O valor informado não é um número de CEP!");
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
