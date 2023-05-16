package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

	public Knight(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "N";
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
		
		posi.setValue(position.getRow() - 1, position.getColumn() - 2);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValue(position.getRow() - 2, position.getColumn() - 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValue(position.getRow() - 2, position.getColumn() + 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValue(position.getRow() - 1, position.getColumn() + 2);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValue(position.getRow() + 1, position.getColumn() + 2);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValue(position.getRow() + 2, position.getColumn() + 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValue(position.getRow() + 2, position.getColumn() - 1);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}

		posi.setValue(position.getRow() + 1, position.getColumn() - 2);
		if(getBoard().positionExists(posi) && canMove(posi)) {
			mat[posi.getRow()][posi.getColumn()] = true;
		}
		
		return mat;
	}
}
