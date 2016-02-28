package br.com.fa7.cursoweb.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.fa7.cursoweb.business.UsuarioBusiness;
import br.com.fa7.cursoweb.model.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	@ManagedProperty("#{usuarioBusiness}")
	private UsuarioBusiness usuarioBusiness;
	private Usuario usuario;
	 
	public UsuarioBean() {
		usuario = new Usuario();
	}

	public UsuarioBusiness getUsuarioBusiness() {
		return usuarioBusiness;
	}

	public void setUsuarioBusiness(UsuarioBusiness usuarioBusiness) {
		this.usuarioBusiness = usuarioBusiness;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return usuarioBusiness.selecionarTodos();
	}

	public String novo() {
		usuario = new Usuario();
		return "usuariosEditar";
	}

	public String salvar() {
		usuarioBusiness.salvarUsuario(usuario);
		return "usuarios";
	}

	public String excluir() {
		usuarioBusiness.excluirUsuario(usuario);
		return "usuarios";
	}
}
