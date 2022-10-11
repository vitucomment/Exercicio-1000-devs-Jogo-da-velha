package com.github.vitucomment.tictactoe;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		Scanner input = new Scanner(System.in);
		Scanner inputInt = new Scanner(System.in);

		String emptyString = " ";
		String[] boardData = new String[9];

		Arrays.fill(boardData, emptyString);

		System.out.print("Nome do jogador 01: ");
		String playerOne = input.nextLine();
		System.out.print("Nome do jogador 02: ");
		String playerTwo = input.nextLine();

		Map<Integer, String> players = new HashMap<>();
		players.put(1, "x");
		players.put(2, "o");

		String currentPlayer = players.get(1);
		String currentNamePlayer = playerOne;

		int scorePlayerOne = 0;
		int scorePlayerTwo = 0;

		while (true) {
			while (isWinner(boardData) == false && isOldLady(boardData) == false) {
				limpaConsole();
				drewBoard(boardData);

				int playerMovement = capturaJogada(inputInt, currentNamePlayer, currentPlayer);

				if (isEmpty(playerMovement, boardData)) {
					boardData[playerMovement] = currentPlayer;
					isWinner(boardData);
					isOldLady(boardData);
					currentPlayer = changePlayer(players, currentPlayer);
					currentNamePlayer = changePlayerName(currentNamePlayer, playerOne, playerTwo);
					limpaConsole();
				} else {
					posicaoInvalidaCheck(playerMovement);
					limpaConsole();
				}
			}

			if (isWinner(boardData) == true) {
				drewBoard(boardData);
				currentNamePlayer = imprimeVencedorDaRodada(playerOne, playerTwo, currentNamePlayer);

				if (currentNamePlayer == playerOne)
					scorePlayerOne += 3;
				else
					scorePlayerTwo += 3;
				showScore(scorePlayerOne, scorePlayerTwo, playerOne, playerTwo);
				currentPlayer = changePlayer(players, currentPlayer);
				currentNamePlayer = changePlayerName(currentNamePlayer, playerOne, playerTwo);
			} else {
				scorePlayerOne += 1;
				scorePlayerTwo += 1;
				showScore(scorePlayerOne, scorePlayerTwo, playerOne, playerTwo);
				currentPlayer = changePlayer(players, currentPlayer);
				currentNamePlayer = changePlayerName(currentNamePlayer, playerOne, playerTwo);
				System.out.println("____DEU VELHA____");
			}

			if (continueGame(input) == false) {

				showScore(scorePlayerOne, scorePlayerTwo, playerOne, playerTwo);
				if (scorePlayerOne > scorePlayerTwo)
					System.out.println("O VENCEDOR FINAL FOI O JOGADOR: " + playerOne);
				else if (scorePlayerTwo < scorePlayerOne)
					System.out.println("O VENCEDOR FINAL FOI O JOGADOR: " + playerTwo);
				else
					System.out.println("O JOGO TERMINOU EMPATADO.");
				break;
			} else {
				boardData = restartBoard(boardData);
			}
		}

	}

	private static String imprimeVencedorDaRodada(String playerOne, String playerTwo, String currentNamePlayer) {
		currentNamePlayer = changePlayerName(currentNamePlayer, playerOne, playerTwo);
		System.out.println("\nO vencedor da rodada: " + currentNamePlayer);
		return currentNamePlayer;
	}

	private static void posicaoInvalidaCheck(int playerMovement) throws InterruptedException {
		System.out.println("A posicao " + playerMovement + " nao valida ou ja utilizada.\n" + "Jogue novamente em: ");
		for (int t = 3; t >= 1; t--) {
			Thread.sleep(1000);
			System.out.println(t + "...");
		}
	}

	public static boolean isEmpty(int position, String[] boardData) {
		return boardData[position].equalsIgnoreCase(" ");
	}

	public static boolean isOldLady(String[] boardData) {
		for (String position : boardData) {
			if (position.equalsIgnoreCase(" "))
				return false;
		}
		return true;
	}

	public static boolean isWinner(String[] boardData) {
		if ((boardData[0] == boardData[1] && boardData[1] == boardData[2] && !boardData[2].equalsIgnoreCase(" "))
				|| (boardData[3] == boardData[4] && boardData[4] == boardData[5] && !boardData[5].equalsIgnoreCase(" "))
				|| (boardData[6] == boardData[7] && boardData[7] == boardData[8] && !boardData[6].equalsIgnoreCase(" "))
				|| (boardData[0] == boardData[3] && boardData[3] == boardData[6] && !boardData[6].equalsIgnoreCase(" "))
				|| (boardData[1] == boardData[4] && boardData[4] == boardData[7] && !boardData[7].equalsIgnoreCase(" "))
				|| (boardData[2] == boardData[5] && boardData[5] == boardData[8] && !boardData[8].equalsIgnoreCase(" "))
				|| (boardData[0] == boardData[4] && boardData[4] == boardData[8] && !boardData[8].equalsIgnoreCase(" "))
				|| (boardData[2] == boardData[4] && boardData[4] == boardData[6]
						&& !boardData[6].equalsIgnoreCase(" "))) {
			return true;
		} else {
			return false;
		}
	}

	public static String changePlayer(Map<Integer, String> players, String currentPlayer) {
		String playerOne = players.get(1);
		String playerTwo = players.get(2);
		if (currentPlayer.equalsIgnoreCase(playerOne)) {
			return currentPlayer = playerTwo;
		} else {
			return currentPlayer = playerOne;
		}
	}

	public static String changePlayerName(String currentNamePlayer, String playerOne, String playerTwo) {
		if (currentNamePlayer.equalsIgnoreCase(playerOne)) {
			return currentNamePlayer = playerTwo;
		} else {
			return currentNamePlayer = playerOne;
		}
	}

	public static void drewBoard(String[] boardData) {
		System.out.println("\n\t Mapa do tabuleira \t\n");
		System.out.println(boardData[0] + " | " + boardData[1] + " | " + boardData[2] + "\t\t 0 | 1 | 2");
		System.out.println(boardData[3] + " | " + boardData[4] + " | " + boardData[5] + "\t\t 3 | 4 | 5");
		System.out.println(boardData[6] + " | " + boardData[7] + " | " + boardData[8] + "\t\t 6 | 7 | 8");
	}

	public static void showScore(int scorePlayerOne, int scorePlayerTwo, String playerOne, String playerTwo) {
		System.out.println("_____PLACAR DO JOGO_____");
		System.out.println("[ " + playerOne + " ] => " + scorePlayerOne);
		System.out.println("[ " + playerTwo + " ] => " + scorePlayerTwo + "\n");
	}

	public static boolean continueGame(Scanner input) {
		System.out.println("'S' Para jogar novamente e 'N' para encerrar a partida.");
		String optionContinue = input.nextLine();
		while (!optionContinue.equalsIgnoreCase("s") && !optionContinue.equalsIgnoreCase("n")) {
			System.out.println("Opcao invalida.\n'S' Para jogar novamente e 'N' para encerrar a partida.");
			optionContinue = input.nextLine();
		}
		if (optionContinue.equalsIgnoreCase("s"))
			return true;
		else
			return false;
	}

	public static String[] restartBoard(String[] boardData) {
		for (int p = 0; p < 9; p++) {
			boardData[p] = " ";
		}
		return boardData;
	}

	public static int capturaJogada(Scanner input, String currentNamePlayer, String currentPlayer) {
		System.out.print("\nVez do jogador [ " + currentNamePlayer + " = " + currentPlayer + " ]: ");
		try {
			input = new Scanner(System.in);
			int playerMovement = input.nextInt();
			while (playerMovement < 0 || playerMovement > 8) {
				System.out.println("O jogador " + currentNamePlayer + " digitou um valor invalido do tabuleiro.");
				System.out.println("\nVez do jogador [ " + currentNamePlayer + " = " + currentPlayer + " ]: ");
				playerMovement = input.nextInt();
			}
			return playerMovement;
		} catch (InputMismatchException ex) {
			System.out.println("Digite um valor valido.");
			input = new Scanner(System.in);
			return capturaJogada(input, currentNamePlayer, currentPlayer);
		}
	}

	public static void limpaConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {
		}

	}
}
