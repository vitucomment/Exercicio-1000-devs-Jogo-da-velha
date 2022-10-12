package com.github.vitucomment.tictactoe;

public class ScoreFunctions {

	public String imprimeVencedorDaRodada(String playerOne, String playerTwo, String currentNamePlayer) {
		PlayersFunctions playersFunction = new PlayersFunctions();
		currentNamePlayer = playersFunction.changePlayerName(currentNamePlayer, playerOne, playerTwo);
		System.out.println("\nO vencedor da rodada: " + currentNamePlayer);
		return currentNamePlayer;
	}

	public void showScore(int scorePlayerOne, int scorePlayerTwo, String playerOne, String playerTwo) {
		sepMsg();
		System.out.println("_____PLACAR DO JOGO_____");
		System.out.println("[ " + playerOne + " ] => " + scorePlayerOne);
		System.out.println("[ " + playerTwo + " ] => " + scorePlayerTwo + "\n");
		sepMsg();

	}

	public void showFinalScore(int scorePlayerOne, int scorePlayerTwo, String playerOne, String playerTwo) {
		if (scorePlayerOne > scorePlayerTwo)
			System.out.println("O VENCEDOR FINAL FOI O JOGADOR: " + playerOne);
		else if (scorePlayerTwo > scorePlayerOne)
			System.out.println("O VENCEDOR FINAL FOI O JOGADOR: " + playerTwo);
		else
			System.out.println("O JOGO TERMINOU EMPATADO.");
	}

	private static void sepMsg() {
		System.out.println("======================================================================");
	}

}
