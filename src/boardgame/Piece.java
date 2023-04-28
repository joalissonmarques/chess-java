package boardgame;

public class Piece {

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
	
}
