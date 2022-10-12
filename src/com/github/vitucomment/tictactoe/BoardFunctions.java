package com.github.vitucomment.tictactoe;

import java.util.Arrays;

public class BoardFunctions {

	public void drewBoard(String[] boardData) {
		System.out.println("\n======================================================================");
		System.out.println("\t Mapa do tabuleiro \t\n");
		System.out.println(boardData[0] + " | " + boardData[1] + " | " + boardData[2] + "\t\t 0 | 1 | 2");
		System.out.println("---------		 ---------");
		System.out.println(boardData[3] + " | " + boardData[4] + " | " + boardData[5] + "\t\t 3 | 4 | 5");
		System.out.println("---------		 ---------");
		System.out.println(boardData[6] + " | " + boardData[7] + " | " + boardData[8] + "\t\t 6 | 7 | 8\n");
		System.out.println("======================================================================");

	}

	public String[] restartBoard(String[] boardData) {
		for (int p = 0; p < 9; p++) {
			boardData[p] = " ";
		}
		return boardData;
	}

	public String[] setBoardDefault() {
		String emptyString = " ";
		String[] boardData = new String[9];
		Arrays.fill(boardData, emptyString);
		return boardData;

	}
}
