package repositorio;

import banco.Banco;
import enums.TipoInvestimento;
import investimento.OpcaoInvestimento;

public class RepositorioOpcaoInvestimento {

	public static void iniciaRepositorioOpcaoInvestimento(Banco banco) {

		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.ACOES, "Ação A 1", 0.47));
		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.ACOES, "Ação B 2", 0.28));
		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.ACOES, "Ação C 3", 0.14));

		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.FUNDOS, "Fundos A 1", 0.34));
		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.FUNDOS, "Fundos B 2", 0.22));
		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.FUNDOS, "Fundos C 3", 0.36));

		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.RENDA_FIXA, "Renda Fixa A 1", 0.65));
		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.RENDA_FIXA, "Renda Fixa B 2", 0.52));
		banco.getOpcoesInvestimento().add(new OpcaoInvestimento(TipoInvestimento.RENDA_FIXA, "Renda Fixa C 3", 0.42));

		banco.getOpcoesInvestimento()
				.add(new OpcaoInvestimento(TipoInvestimento.TESOURO_DIRETO, "Tesouro Direto A 1", 0.24));
		banco.getOpcoesInvestimento()
				.add(new OpcaoInvestimento(TipoInvestimento.TESOURO_DIRETO, "Tesouro Direto B 2", 0.36));
		banco.getOpcoesInvestimento()
				.add(new OpcaoInvestimento(TipoInvestimento.TESOURO_DIRETO, "Tesouro Direto C 3", 0.48));

	}
	
}
