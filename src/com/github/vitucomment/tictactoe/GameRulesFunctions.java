package com.github.vitucomment.tictactoe;

public class GameRulesFunctions {

	public boolean isEmpty(int position, String[] boardData) {
		return boardData[position].equalsIgnoreCase(" ");
	}

	public boolean isOldLady(String[] boardData) {
		for (String position : boardData) {
			if (position.equalsIgnoreCase(" "))
				return false;
		}
		return true;
	}

	public boolean isWinner(String[] boardData) {
		return ((boardData[0] == boardData[1] && boardData[1] == boardData[2] && !boardData[2].equalsIgnoreCase(" "))
				|| (boardData[3] == boardData[4] && boardData[4] == boardData[5] && !boardData[5].equalsIgnoreCase(" "))
				|| (boardData[6] == boardData[7] && boardData[7] == boardData[8] && !boardData[6].equalsIgnoreCase(" "))
				|| (boardData[0] == boardData[3] && boardData[3] == boardData[6] && !boardData[6].equalsIgnoreCase(" "))
				|| (boardData[1] == boardData[4] && boardData[4] == boardData[7] && !boardData[7].equalsIgnoreCase(" "))
				|| (boardData[2] == boardData[5] && boardData[5] == boardData[8] && !boardData[8].equalsIgnoreCase(" "))
				|| (boardData[0] == boardData[4] && boardData[4] == boardData[8] && !boardData[8].equalsIgnoreCase(" "))
				|| (boardData[2] == boardData[4] && boardData[4] == boardData[6]
						&& !boardData[6].equalsIgnoreCase(" "))) ? true : false;
	}
}
