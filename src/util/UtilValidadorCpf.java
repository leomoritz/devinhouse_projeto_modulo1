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
	 * M�todo privado utilizado apenas por esta classe utilit�ria.
	 * 
	 * Valida CPF digitado no formato: 999.999.999-99
	 * 
	 * @apiNote O CPF pode conter 11 caracteres alfanum�ricos, 3 pontos e 1 tra�o.
	 * 
	 * S�o 11 caracteres alfanum�ricos divididos em 4
	 * grupos. Os 3 primeiros grupos devem ser divididas por 1 ponto a cada 3
	 * caracteres alfanum�ricos. O 4� grupo s� ir� come�ar depois de encontrar 1
	 * tra�o. Ap�s o tra�o ser encontrado � permitido apenas 2 caracteres
	 * alfanum�ricos.
	 */

	private static Pattern getValidadorComCaracterEspecial() {

		return Pattern.compile("[0-9]{3}\\." + "[0-9]{3}\\." + "[0-9]{3}-" + "[0-9]{2}");

	}

	/**
	 * M�todo privado utilizado apenas por esta classe utilit�ria.
	 * 
	 * Valida CPF digitado no formato 99999999999.
	 * 
	 * @apiNote S�o 11 caracteres alfanum�ricos sem caracteres especiais (pontos ou tra�o);
	 */

	private static Pattern getValidadorSemCaracterEspecial() {

		return Pattern.compile("[0-9]{11}");

	}

}
