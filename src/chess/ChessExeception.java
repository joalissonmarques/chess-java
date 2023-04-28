package chess;

import boardgame.BoardException;

public class ChessExeception extends BoardException{
	private static final long serialVersionUID = 1L;
	
	public ChessExeception(String message) {
		super(message);
	}

}
