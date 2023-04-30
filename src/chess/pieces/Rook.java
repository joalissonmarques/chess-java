package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position posiAux = new Position(0, 0);
		//Verificar acima (above)
		posiAux.setValue(position.getRow() - 1, position.getColumn());
		//Enquanto a posição existir e estiver vaga, ta true
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			//Sendo assim, a peça pode se mover para lá
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setRow(posiAux.getRow() - 1);
		}
		//Se a posição ainda for true e possui no caminho uma peça inimiga
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		//Verificar a esquerda (left)
		posiAux.setValue(position.getRow(), position.getColumn() - 1);
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setColumn(posiAux.getColumn() - 1);
		}
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		//Verificar a direita (right)
		posiAux.setValue(position.getRow(), position.getColumn() + 1);
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setColumn(posiAux.getColumn() + 1);
		}
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		//Verificar para baixo (below)
		posiAux.setValue(position.getRow() + 1, position.getColumn());
		while(getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
			posiAux.setRow(posiAux.getRow() + 1);
		}
		if(getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
			mat[posiAux.getRow()][posiAux.getColumn()] = true;
		}
		
		return mat;
	}
}
