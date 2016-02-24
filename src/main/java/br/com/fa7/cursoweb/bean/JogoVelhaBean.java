package br.com.fa7.cursoweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class JogoVelhaBean {

	private static final String SELECIONAR = "Selecionar";
	private static final String DRAW = "draw";
	private static final String MESSAGE_DEFAULT = "<span class=\"red-text\">Aguardando o nome dos jogadores</span>";
	private static final String ICON_CIRCLE = "<span class=\"fa fa-circle-o fa-5x blue-text\"></span>";
	private static final String ICON_CLOSE = "<span class=\"fa fa-close fa-5x red-text\"></span>";

	private String jogador1;
	private String jogador2;
	private String jogadorAtual;
	private boolean gameStarted;
	private int[][] gameMap = new int[3][3];
	private String message;
	private int gameTurns = 0;

	/**
	 * Verifica se o botão de novo jogo deve ser habilitado
	 *
	 * @return true se o botão deve ser desabilitado
	 */
	public boolean disableButtonSubmit() {
		return (jogador1 == null || "".equals(jogador1.trim()))
				|| (jogador2 == null || "".equals(jogador2.trim()));
	}

	/**
	 * Inicia o jogo
	 */
	public void newGame() {
		this.gameMap = new int[3][3];
		setGameStarted(true);
		updateActualPlayer();
		updateMessage();
	}

	public boolean disableField(int row, int column) {
		if (!gameStarted) {
			return true;
		}
		int fieldValue = gameMap[row][column];
		if (fieldValue == 0) {
			return false;
		}
		return true;
	}

	public String getFieldValue(int row, int column) {
		String icon = getIcon(gameMap[row][column]);
		if (!gameStarted && SELECIONAR.equals(icon)) {
			return "";
		}
		return icon;
	}

	private String getIcon(int fieldValue) {
		if (fieldValue == 0) {
			return SELECIONAR;
		} else if (fieldValue == -1) {
			return ICON_CIRCLE;
		}
		return ICON_CLOSE;
	}

	public void selectField(int row, int column) {
		gameMap[row][column] = getPlayerValue();
		gameTurns++;
		String winner = checkWinner();
		if (winner != null) {
			updateMessage(winner);
			resetGame();
		} else {
			updateActualPlayer();
			updateMessage();
		}
	}

	private void resetGame() {
		setGameStarted(false);
		setJogador1(null);
		setJogador2(null);
		setGameTurns(0);
		setJogadorAtual(null);
	}

	private String checkWinner() {
		String rows = checkRows();
		String columns = checkColumns();
		String diagonals = checkDiagonals();

		if (rows != null) {
			return rows;
		} else if (columns != null) {
			return columns;
		} else if (diagonals != null) {
			return diagonals;
		}

		if (gameTurns == 9) {
			return DRAW;
		}
		return null;
	}

	private String checkDiagonals() {
		int sumFirstDiagonal = gameMap[0][0] + gameMap[1][1] + gameMap[2][2];
		if (sumFirstDiagonal == -3) {
			return jogador1;
		} else if (sumFirstDiagonal == 3) {
			return jogador2;
		}
		int sumSecondDiagonal = gameMap[0][2] + gameMap[1][1] + gameMap[2][0];
		if (sumSecondDiagonal == -3) {
			return jogador1;
		} else if (sumSecondDiagonal == 3) {
			return jogador2;
		}
		return null;
	}

	private String checkColumns() {
		for (int column = 0; column < 3; column++) {
			int sum = gameMap[0][column] + gameMap[1][column] + gameMap[2][column];
			if (sum == -3) {
				return jogador1;
			} else if (sum == 3) {
				return jogador2;
			}
		}
		return null;
	}

	private String checkRows() {
		for (int line = 0; line < 3; line++) {
			int sum = gameMap[line][0] + gameMap[line][1] + gameMap[line][2];
			if (sum == -3) {
				return jogador1;
			} else if (sum == 3) {
				return jogador2;
			}
		}
		return null;
	}

	private void updateActualPlayer() {
		if (jogadorAtual == null) {
			jogadorAtual = jogador1;
		} else if (jogadorAtual.equals(jogador1)) {
			jogadorAtual = jogador2;
		} else {
			jogadorAtual = jogador1;
		}
	}

	private int getPlayerValue() {
		if (jogadorAtual.equals(jogador1)) {
			return -1;
		}
		return 1;
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

	public boolean isGameStarted() {
		return gameStarted;
	}

	private void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public String getMessage() {
		if (message == null) {
			message = MESSAGE_DEFAULT;
		}
		return message;
	}

	private void updateMessage() {
		StringBuilder message = new StringBuilder();
		message.append("É a vez de ");
		message.append("<span class=\"bold orange-text\">");
		message.append(jogadorAtual);
		message.append("</span>");
		message.append(" jogar");
		this.message = message.toString();
	}

	private void updateMessage(String winner) {
		StringBuilder message = new StringBuilder();
		if (DRAW.equals(winner)) {
			message.append("O jogo saiu empatado e não houve vencedor");
		} else {
			message.append("O jogador ");
			message.append("<span class=\"bold orange-text\">");
			message.append(winner);
			message.append("</span>");
			message.append(" venceu");
		}
		this.message = message.toString();
	}

	public void setGameTurns(int gameTurns) {
		this.gameTurns = gameTurns;
	}

	public void setJogadorAtual(String jogadorAtual) {
		this.jogadorAtual = jogadorAtual;
	}
}