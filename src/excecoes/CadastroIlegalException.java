package excecoes;

public class CadastroIlegalException extends Exception {

	private static final long serialVersionUID = -6311042325227008648L;

	public CadastroIlegalException() {
		super("Não foi possível realizar o cadastro. Verifique os dados informados e tente novamente!");
	}

	public CadastroIlegalException(String message) {
		super(message);
	}

}
