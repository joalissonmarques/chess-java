package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
//Aqui é onde terá as regras do jogo
	
	private Board board;
	
	public ChessMatch() {
		//Definindo o tamanho do tabuleiro
		board = new Board(8, 8);
		initialSetup();
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
	//Aqui é para mostrar as possibilidades de movimentos da peça
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition,ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		//Validar se tem posição
		validateSourcePosition(source);
		//Validar a posição de destino
		validateTargetPosition(source, target);
		//Operação que irá realizar o movimento da peça
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessExeception("There is no piece on source position!");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessExeception("There is no possible moves for the chosen peice!");
		}
	}
	
	public void validateTargetPosition(Position source, Position target) {
		//Se para a peça de origem a posição de destino não é um movimento possivel, não podemos mexer
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessExeception("The chosen piece can't move to target position!");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
	}
}
