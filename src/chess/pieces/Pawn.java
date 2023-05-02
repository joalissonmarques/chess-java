package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position posiAux = new Position(0, 0);
		
		if(getColor() == Color.WHITE) {
			posiAux.setValue(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			//verificar se é a primeira jogada do peao, se ta disponivel e vazia para andar duas casas
			posiAux.setValue(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() - 1, position.getColumn() - 1);
			if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() - 1, position.getColumn() + 1);
			if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
		}
		else {
			posiAux.setValue(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux) && getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() + 1, position.getColumn() - 1);
			if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() + 1, position.getColumn() + 1);
			if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
