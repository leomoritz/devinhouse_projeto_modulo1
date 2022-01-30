package util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class UtilValidadorNome {

	public static Boolean validadorNome(String nome) {

		if (getValidadorNome().matcher(removerAcentos(nome)).matches()) {
			return true;
		}

		return false;

	}

	private static Pattern getValidadorNome() {

		return Pattern.compile("([a-zA-Z])+\s((([a-zA-Z]|\\.)+\s?)+)\n?");

	}

	/*
	 * Utilizado classe Normalizer para formatar o nome através de um regex que
	 * ignora caracter especial e substitua por nada "". Solução encontrada em:
	 * https://pt.stackoverflow.com/questions/42/como-remover-acentos-e-outros-
	 * sinais-gr%C3%A1ficos-de-uma-string-em-java
	 */

	public static String removerAcentos(String nome) {
		return Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

}
