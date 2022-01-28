package excecoes;

public class TransacaoIlegalException extends Exception {

	private static final long serialVersionUID = -6311042325227008648L;

	public TransacaoIlegalException() {
		super("N�o foi poss�vel realizar a transa��o. Entre em contato com sua ag�ncia para ajuda.");
	}

	public TransacaoIlegalException(String message) {
		super(message);
	}

}
