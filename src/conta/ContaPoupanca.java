package conta;

import banco.Agencia;
import enums.TipoConta;
import enums.TipoOperacao;
import excecoes.SaldoInsuficienteException;
import interfaces.SimulacaoRendimentoConta;

public class ContaPoupanca extends Conta implements SimulacaoRendimentoConta {

	public ContaPoupanca(String nome, String cpf, Agencia agencia, Double rendaMensal, Double valorPrimeiroDeposito)
			throws Exception {
		super(nome, cpf, TipoConta.POUPANCA, agencia, rendaMensal, valorPrimeiroDeposito);
	}

	@Override
	public Boolean saque(Double valor) throws Exception {

		if (valor <= 0 || valor == null) {
			throw new IllegalArgumentException("Valor inválido para esta operação!");
		}

		if ((getSaldo() - valor) >= 0.0) {
			setSaldo(getSaldo() - valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.SAQUE, valor));
			return true;
		}

		throw new SaldoInsuficienteException();
	}

	@Override
	public double simulaRendimentoConta(int qtdMeses, double taxaRendimento) {

		if (qtdMeses <= 0.0 || taxaRendimento <= 0.0) {
			throw new IllegalArgumentException("Valor inválido para esta operação. "
					+ "A quantidade de meses e a taxa de rendimento precisam ser maior que zero");
		}

		return taxaRendimento * qtdMeses;
	}

}
