package repositorio;

import banco.Agencia;
import banco.Banco;

public class RepositorioAgencia {

	public static void iniciaRepositorioOpcaoInvestimento(Banco banco) {

		banco.getAgencias().add(new Agencia(001, "Florian�polis"));
		banco.getAgencias().add(new Agencia(002, "S�o Jos�"));

	}

}
