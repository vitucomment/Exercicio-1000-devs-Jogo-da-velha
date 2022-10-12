package com.github.vitucomment.tictactoe;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class PlayersFunctions {

	static Scanner input = new Scanner(System.in);

	public String getPlayerOne() {
		sepMsg();
		System.out.print("Nome do jogador 01: ");
		String playerOne = input.nextLine();
		return playerOne;
	}

	public String getPlayerTwo() {
		System.out.print("Nome do jogador 02: ");
		String playerTwo = input.nextLine();
		sepMsg();
		return playerTwo;
	}

	public String changePlayer(Map<Integer, String> players, String currentPlayer) {
		String playerOne = players.get(1);
		String playerTwo = players.get(2);
		return (currentPlayer.equalsIgnoreCase(playerOne)) ? (currentPlayer = playerTwo) : (currentPlayer = playerOne);
	}

	public String changePlayerName(String currentNamePlayer, String playerOne, String playerTwo) {
		return (currentNamePlayer.equalsIgnoreCase(playerOne)) ? (currentNamePlayer = playerTwo)
				: (currentNamePlayer = playerOne);
	}

	public int setPlayerMovement(String currentNamePlayer, String currentPlayer) {
		msgJogueNovamente(currentNamePlayer, currentPlayer);
		try {
			input = new Scanner(System.in);
			int playerMovement = getPlayerMovement(currentNamePlayer, currentPlayer);
			return playerMovement;

		} catch (InputMismatchException ex) {
			msgPosicaoInvalida();
			input = new Scanner(System.in);
			return setPlayerMovement(currentNamePlayer, currentPlayer);
		}
	}

	public void posicaoInvalidaCheck(int playerMovement) {
		System.out.println("\nA posicao " + playerMovement + " nao valida ou ja utilizada.\nJogue novamente");
	}

	public boolean continueGame() {
		input.nextLine();
		System.out.print("'S' Para jogar novamente e 'N' para encerrar a partida: ");
		String optionContinue = input.nextLine();
		while (!optionContinue.equalsIgnoreCase("s") && !optionContinue.equalsIgnoreCase("n")) {
			System.out.print("Opcao invalida.\n'S' Para jogar novamente e 'N' para encerrar a partida: ");
			optionContinue = input.nextLine();
		}
		return (optionContinue.equalsIgnoreCase("s")) ? true : false;
	}

	public Map<Integer, String> setPlayers() {
		Map<Integer, String> players = new HashMap<>();
		players.put(1, "x");
		players.put(2, "o");
		return players;
	}

	private static int getPlayerMovement(String currentNamePlayer, String currentPlayer) {
		int playerMovement = input.nextInt();
		while (playerMovement < 0 || playerMovement > 8) {
			msgPosicaoInvalida();
			msgJogueNovamente(currentNamePlayer, currentPlayer);
			playerMovement = input.nextInt();
		}
		return playerMovement;
	}

	public static void msgPosicaoInvalida() {
		System.out.println("Posicao invalida");
		System.out.println("Jogue novamente.");

	}

	private static void msgJogueNovamente(String currentNamePlayer, String currentPlayer) {
		System.out.print("\nVez do jogador [ " + currentNamePlayer + " = " + currentPlayer + " ]: ");
	}

	private static void sepMsg() {
		System.out.println("======================================================================");
	}

}
