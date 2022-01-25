package util;

/**
 * Classe utilit�ria abstrata criada apenas para gerar um novo c�digo sequ�ncial com atributo est�tico para manter
 * os valores e possibilitar a gera��o sequencial de inteiros.
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
