package excecoes;

public class CadastroJaExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public CadastroJaExisteException() {
		super("O cadastro informado já existe no sistema!");
	}

}
