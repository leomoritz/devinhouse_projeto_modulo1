package excecoes;

public class DepositoNegativoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529555310464777724L;

	public DepositoNegativoException() {
		super("O valor informado � inv�lido para dep�sito. O valor precisa ser maior que 0.0");
	}

}
