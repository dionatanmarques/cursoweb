package br.com.fa7.cursoweb.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fa7.cursoweb.business.UsuarioBusiness;
import br.com.fa7.cursoweb.business.UsuarioInvalidoException;
import br.com.fa7.cursoweb.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioLogadoBean implements Serializable {

	private static final long serialVersionUID = -2505438864756384720L;

	@ManagedProperty("#{usuarioBusiness}")
	private UsuarioBusiness usuarioBusiness;
	private Usuario usuario;
	private String cpf;
	private String senha;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String autenticarUsuario() {
		try {
			usuario = usuarioBusiness.autenticarUsuario(cpf, senha);
			return "pages/index.xhtml?faces-redirect=true";
		} catch (UsuarioInvalidoException e) {
			FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			return null;
		}
	}

	public UsuarioBusiness getUsuarioBusiness() {
		return usuarioBusiness;
	}

	public void setUsuarioBusiness(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}

	public boolean isLogged() {
		return this.usuario != null;
	}
}