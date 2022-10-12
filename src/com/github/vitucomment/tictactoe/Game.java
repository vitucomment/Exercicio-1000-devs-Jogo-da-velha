package com.github.vitucomment.tictactoe;

import java.io.IOException;
import java.util.Map;


public class Game {
	public static void main(String[] args) throws InterruptedException {		
		
		BoardFunctions boardFunctions = new BoardFunctions();
		PlayersFunctions playersFunctions = new PlayersFunctions();
		GameRulesFunctions gameRulesFunctions = new GameRulesFunctions();
		ScoreFunctions scoreFunctions = new ScoreFunctions();
		
		String[] boardData = boardFunctions.setBoardDefault();
		
		String playerOne = playersFunctions.getPlayerOne();
		String playerTwo = playersFunctions.getPlayerTwo();

		Map<Integer, String> players = playersFunctions.setPlayers();

		String currentPlayer = players.get(1);
		String currentNamePlayer = playerOne;

		int scorePlayerOne = 0;
		int scorePlayerTwo = 0;
 
		while (true) {
			while (gameRulesFunctions.isWinner(boardData) == false && gameRulesFunctions.isOldLady(boardData) == false) {
				limpaConsole();
				boardFunctions.drewBoard(boardData);

				int playerMovement = playersFunctions.setPlayerMovement(currentNamePlayer, currentPlayer);

				if (gameRulesFunctions.isEmpty(playerMovement, boardData)) {
					boardData[playerMovement] = currentPlayer;
					gameRulesFunctions.isWinner(boardData);
					gameRulesFunctions.isOldLady(boardData);
					currentPlayer = playersFunctions.changePlayer(players, currentPlayer);
					currentNamePlayer = playersFunctions.changePlayerName(currentNamePlayer, playerOne, playerTwo);
					limpaConsole();
				} else {
					playersFunctions.posicaoInvalidaCheck(playerMovement);
					limpaConsole();
				}
			}

			if (gameRulesFunctions.isWinner(boardData) == true) {
				boardFunctions.drewBoard(boardData);
				
				currentNamePlayer = scoreFunctions.imprimeVencedorDaRodada(playerOne, playerTwo, currentNamePlayer);

				if (currentNamePlayer == playerOne)
					scorePlayerOne += 3;
				else
					scorePlayerTwo += 3;
				scoreFunctions.showScore(scorePlayerOne, scorePlayerTwo, playerOne, playerTwo);
				currentPlayer = playersFunctions.changePlayer(players, currentPlayer);
				currentNamePlayer = playersFunctions.changePlayerName(currentNamePlayer, playerOne, playerTwo);
			} else {
				scorePlayerOne += 1;
				scorePlayerTwo += 1;
				scoreFunctions.showScore(scorePlayerOne, scorePlayerTwo, playerOne, playerTwo);
				currentPlayer = playersFunctions.changePlayer(players, currentPlayer);
				currentNamePlayer = playersFunctions.changePlayerName(currentNamePlayer, playerOne, playerTwo);
				System.out.println("____DEU VELHA____");
			}

			if (playersFunctions.continueGame() == false) {

				scoreFunctions.showScore(scorePlayerOne, scorePlayerTwo, playerOne, playerTwo);
				scoreFunctions.showFinalScore(scorePlayerOne, scorePlayerTwo, playerOne, playerTwo);
				break;
			} else {
				boardData = boardFunctions.restartBoard(boardData);
			}
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
