package util;

import java.util.regex.Pattern;

public class UtilValidadorCpf {

	public static Boolean validadorCpf(String cpf) {

		if (getValidadorComCaracterEspecial().matcher(cpf).matches()) {
			return true;
		}

		if (getValidadorSemCaracterEspecial().matcher(cpf).matches()) {
			return true;
		}

		return false;

	}

	/**
	 * Método privado utilizado apenas por esta classe utilitária.
	 * 
	 * Valida CPF digitado no formato: 999.999.999-99
	 * 
	 * @apiNote O CPF pode conter 11 caracteres alfanuméricos, 3 pontos e 1 traço.
	 * 
	 * São 11 caracteres alfanuméricos divididos em 4
	 * grupos. Os 3 primeiros grupos devem ser divididas por 1 ponto a cada 3
	 * caracteres alfanuméricos. O 4º grupo só irá começar depois de encontrar 1
	 * traço. Após o traço ser encontrado é permitido apenas 2 caracteres
	 * alfanuméricos.
	 */

	private static Pattern getValidadorComCaracterEspecial() {

		return Pattern.compile("[0-9]{3}\\." + "[0-9]{3}\\." + "[0-9]{3}-" + "[0-9]{2}");

	}

	/**
	 * Método privado utilizado apenas por esta classe utilitária.
	 * 
	 * Valida CPF digitado no formato 99999999999.
	 * 
	 * @apiNote São 11 caracteres alfanuméricos sem caracteres especiais (pontos ou traço);
	 */

	private static Pattern getValidadorSemCaracterEspecial() {

		return Pattern.compile("[0-9]{11}");

	}

}
