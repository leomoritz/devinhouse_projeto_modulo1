package excecoes;

public class CadastroInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public CadastroInexistenteException() {
		super("O cadastro informado n�o foi encontrado no sistema ou n�o existe!");
	}

}
