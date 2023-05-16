package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position posiAux = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			posiAux.setValue(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			// verificar se é a primeira jogada do peao, se ta disponivel e vazia para andar
			// duas casas
			posiAux.setValue(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux) && getMoveCount() == 0
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}

			// Special move en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		} else {
			posiAux.setValue(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(posiAux) && !getBoard().thereIsAPiece(posiAux) && getMoveCount() == 0
					&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}
			posiAux.setValue(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(posiAux) && isThereOpponentPiece(posiAux)) {
				mat[posiAux.getRow()][posiAux.getColumn()] = true;
			}

			// Special move en passant balck
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
