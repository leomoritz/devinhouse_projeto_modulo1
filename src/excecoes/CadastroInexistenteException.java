package excecoes;

public class CadastroInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public CadastroInexistenteException() {
		super("O cadastro informado não foi encontrado no sistema ou não existe!");
	}

}
