package excecoes;

public class DepositoNegativoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529555310464777724L;

	public DepositoNegativoException() {
		super("O valor informado é inválido para depósito. O valor precisa ser maior que 0.0");
	}

}
