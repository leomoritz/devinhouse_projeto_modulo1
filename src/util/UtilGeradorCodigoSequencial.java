package util;

/**
 * Classe utilitária abstrata criada apenas para gerar um novo código sequêncial com atributo estático para manter
 * os valores e possibilitar a geração sequencial de inteiros.
 * 
 * @author Leonidas.Rosa
 *
 */

public abstract class UtilGeradorCodigoSequencial {
	
	private static int id = 1;
	
	public static int gerarCodigoSequencial() {
		return id++;
	}

}
