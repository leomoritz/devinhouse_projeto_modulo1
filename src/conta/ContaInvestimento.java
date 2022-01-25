package conta;

import java.util.HashSet;
import java.util.Set;

import banco.Agencia;
import enums.TipoConta;
import interfaces.SimulacaoRendimentoConta;
import investimento.Investimento;

public class ContaInvestimento extends Conta implements SimulacaoRendimentoConta {
	
	private final Set<Investimento> investimentosConta;

	public ContaInvestimento(String nome, String cpf, Agencia agencia, Double rendaMensal, Double saldo) {
		super(nome, cpf, TipoConta.INVESTIMENTO, agencia, rendaMensal, saldo);
		this.investimentosConta = new HashSet<Investimento>();
	}

	public Set<Investimento> getInvestimentosConta() {
		return investimentosConta;
	}
	
	public void adicionarInvestimento(Investimento novoInvestimento) {
		getInvestimentosConta().add(novoInvestimento);
	}
	
	public Double mostrarRendimentoAnualInvestimento(Investimento investimento) {
		return investimento.getRendimentoAnual();
	}

	@Override
	public double simulaRendimentoConta(int qtdMeses, Investimento investimento) {
		return investimento.getTaxaRendimento() * qtdMeses;
	}

	@Override
	public Boolean saque(double valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deposito(double valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean transferir(Conta destino, Double valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
