package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	//testar se nessa posicao existe uma torre e se essa torre pode fazer um rook
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
		
		//Special move castling
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//ver se as duas posições estão vagas e não foi feito movimento
			Position posiTorre1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastling(posiTorre1)) {
				//testar se as casas estão vazias
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			
			//rook grande
			Position posiTorre2 = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastling(posiTorre2)) {
				//testar se as casas estão vazias
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}
		
		return mat;
	}
}
