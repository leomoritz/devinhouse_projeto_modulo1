package excecoes;

public class CadastroJaExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public CadastroJaExisteException() {
		super("O cadastro com o CPF e Tipo de Conta informados já existe no sistema!");
	}

}
