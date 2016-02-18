package br.com.fa7.cursoweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class JogoVelhaBean {

	private String jogador1;
	private String jogador2;
	private String jogadorAtual;

	/**
	 * Verifica se o botão de novo jogo deve ser habilitado
	 *
	 * @return true se o botão deve ser desabilitado
	 */
	public boolean disableButtonSubmit() {
		return (jogador1 == null || "".equals(jogador1.trim()))
				|| (jogador2 == null || "".equals(jogador2.trim()));
	}

	public void newGame() {
		setJogadorAtual(jogador1);
	}

	public String getJogador1() {
		return jogador1;
	}

	public void setJogador1(String jogador1) {
		this.jogador1 = jogador1;
	}

	public String getJogador2() {
		return jogador2;
	}

	public void setJogador2(String jogador2) {
		this.jogador2 = jogador2;
	}

	public String getJogadorAtual() {
		return jogadorAtual;
	}

	public void setJogadorAtual(String jogadorAtual) {
		this.jogadorAtual = jogadorAtual;
	}
}