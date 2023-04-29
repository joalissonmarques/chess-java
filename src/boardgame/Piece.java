package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	//Aqui não recebe a posição, pq a peça começa como nula, pois não tem movimentação
	public Piece(Board board) {
		this.board = board;
		//Se quiser pode não colocar nada, padrão java, ele instancia como null
		position = null;
	}
	//Não colocamos o Set, pois não queremos que a peça seja alterada
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		//Hook methods, pegando uma função  abstract
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		//Pecorrer a matriz para saber se a peça tem como fazer algum movimento
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
