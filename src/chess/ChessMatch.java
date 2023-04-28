package chess;

import boardgame.Board;

public class ChessMatch {
//Aqui é onde terá as regras do jogo
	
	private Board board;
	
	public ChessMatch() {
		//Definindo o tamanho do tabuleiro
		board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				//fazendo um downcast, para interpretar como uma peça de xadrez
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
}
