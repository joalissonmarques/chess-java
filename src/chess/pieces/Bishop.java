package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position posiAux = new Position(0, 0);
		//NOROESTE NW
		posiAux.setValue(position.getRow() - 1, position.getColumn() - 1);
		//Enquanto a posição existir e estiver vaga, ta true
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			//Sendo assim, a peça pode se mover para lá
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setValue(posiAux.getRow() - 1, posiAux.getColumn() - 1);
		}
		
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		//NORDESTE NE 
		posiAux.setValue(position.getRow() - 1, position.getColumn() + 1);
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setValue(posiAux.getRow() - 1, posiAux.getColumn() + 1);
		}
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		//SUDESTE SE
		posiAux.setValue(position.getRow() + 1, position.getColumn() + 1);
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setValue(posiAux.getRow() + 1, posiAux.getColumn() + 1);
		}
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		//SUDOESTE SW
		posiAux.setValue(position.getRow() + 1, position.getColumn() - 1);
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setValue(posiAux.getRow() + 1, posiAux.getColumn() - 1);
		}
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		
		return mat;
	}
}
