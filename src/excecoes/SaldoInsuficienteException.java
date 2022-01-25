package excecoes;

public class SaldoInsuficienteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8358399202066822126L;

	public SaldoInsuficienteException() {
		super("Saldo insuficiente para esta operação!");
	}	

}
