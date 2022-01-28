package repositorio;

import banco.Agencia;
import banco.Banco;

public class RepositorioAgencia {

	public static void iniciaRepositorioOpcaoInvestimento(Banco banco) {

		banco.getAgencias().add(new Agencia(001, "Florianópolis"));
		banco.getAgencias().add(new Agencia(002, "São José"));

	}

}
