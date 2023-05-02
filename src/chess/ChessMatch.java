package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
//Aqui é onde terá as regras do jogo
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		//turno e qual é a cor iniciante
		turn = 1;
		currentPlayer = Color.WHITE;
		//Definindo o tamanho do tabuleiro
		board = new Board(8, 8);
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		//Verificar se o jogador se colocou em check
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessExeception("You can't put yourself in check!");
		}
		//verificar se o oponente ficou em check
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		//testar a jogada que eu fiz deixou em checkMate
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			//Chamando a troca de player
			nextTurn();		
		}
		return (ChessPiece)capturedPiece;
	}
	
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			//Desfazendo a jogada
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessExeception("There is no piece on source position!");
		}
		//Pegamos a peça, fazemos um downcast para acessar a prop getColor
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessExeception("The chosen piece is not yours!");
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
	
	private void nextTurn() {
		turn++;
		//Expressão ternaria
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(
				piece -> ((ChessPiece)piece).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		//isso é para nunca acontecer, se acontecer, meu programa está com algum problema
		throw new IllegalStateException("There is no " + color + " king on the board!");
	}
	
	private boolean testCheck(Color color) {
		//Pegando a posição do rei
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(
				piece -> ((ChessPiece)piece).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			//se esse elemento for verdadeiro, significa que o rei ta em check
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(
				piece -> ((ChessPiece)piece).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i = 0; i < board.getRows(); i ++) {
				for(int j = 0; j < board.getRows(); j ++) {
					//Se o possivel movimento tira do check
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						//aqui vai testar se o rei da minha cor, vai ta em check ainda
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							//significa que tirou meu rei de check
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
	}
}
