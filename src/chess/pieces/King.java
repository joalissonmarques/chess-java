package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}
	//Esse metodo vai me informar se minha peça pode se mover ou não
	private boolean canMove(Position position) {
		ChessPiece pieceChess = (ChessPiece)getBoard().piece(position);
		return pieceChess == null || pieceChess.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position posi = new Position(0, 0);
		
		//Acima above
		posi.setValue(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		//baixo below
		posi.setValue(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		//esquerda left
		posi.setValue(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		//Direita right
		posi.setValue(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		//NW
		posi.setValue(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		//NE
		posi.setValue(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		//SW
		posi.setValue(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		//SE
		posi.setValue(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		
		return mat;
	}
}
