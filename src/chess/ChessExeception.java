package chess;

public class ChessExeception extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ChessExeception(String message) {
		super(message);
	}

}