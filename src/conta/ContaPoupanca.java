package conta;

import banco.Agencia;
import enums.TipoConta;
import enums.TipoOperacao;
import excecoes.SaldoInsuficienteException;
import interfaces.SimulacaoRendimentoConta;
import investimento.Investimento;

public class ContaPoupanca extends Conta implements SimulacaoRendimentoConta{

	public ContaPoupanca(String nome, String cpf, Agencia agencia, Double rendaMensal, Double saldo) {
		super(nome, cpf, TipoConta.POUPANCA, agencia, rendaMensal, saldo);
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
	public double simulaRendimentoConta(int qtdMeses, Investimento investimento) {
		// TODO Auto-generated method stub
		return 0;
	}

}
