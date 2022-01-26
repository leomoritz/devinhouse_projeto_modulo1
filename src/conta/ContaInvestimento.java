package conta;

import java.util.HashSet;
import java.util.Set;

import banco.Agencia;
import enums.TipoConta;
import enums.TipoOperacao;
import excecoes.SaldoInsuficienteException;
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
	public Boolean saque(Double valor) throws Exception {

		if (valor <= 0 || valor == null) {
			throw new IllegalArgumentException("Valor inv�lido para esta opera��o!");
		}

		if ((getSaldo() - valor) >= 0.0) {
			setSaldo(getSaldo() - valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.SAQUE, valor));
			return true;
		}

		throw new SaldoInsuficienteException();
	}
	
	@Override
	public double simulaRendimentoConta(int qtdMeses, Investimento investimento) {
		return investimento.getTaxaRendimento() * qtdMeses;
	}

}
