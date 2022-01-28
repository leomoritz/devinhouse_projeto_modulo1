package excecoes;

public class TransacaoIlegalException extends Exception {

	private static final long serialVersionUID = -6311042325227008648L;

	public TransacaoIlegalException() {
		super("Não foi possível realizar a transação. Entre em contato com sua agência para ajuda.");
	}

	public TransacaoIlegalException(String message) {
		super(message);
	}

}
