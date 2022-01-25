package conta;

import banco.Agencia;
import enums.TipoConta;
import enums.TipoOperacao;
import excecoes.DepositoNegativoException;
import excecoes.SaldoInsuficienteException;

public class ContaCorrente extends Conta {

	private Double chequeEspecial;

	public ContaCorrente(String nome, String cpf, Agencia agencia, Double rendaMensal, Double saldo) {
		super(nome, cpf, TipoConta.CORRENTE, agencia, rendaMensal, saldo);
		this.chequeEspecial = -(rendaMensal * 0.10); // 10% da renda mensal
	}

	public Double getChequeEspecial() {
		return chequeEspecial;
	}

	@Override
	public Boolean saque(double valor) throws SaldoInsuficienteException {

		if (getSaldo() >= valor || getSaldo() >= chequeEspecial) {
			setSaldo(valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.SAQUE, valor));
			return true;
		}

		throw new SaldoInsuficienteException();

	}

	@Override
	public Boolean deposito(double valor) throws DepositoNegativoException {

		if (valor > 0.0) {
			setSaldo(valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.DEPOSITO, valor));
			return true;
		}

		throw new DepositoNegativoException();
	}

	@Override
	public Boolean transferir(Conta contaDestino, Double valor) throws Exception {
		if (saque(valor)) {
			contaDestino.deposito(valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, contaDestino.getAgencia(), contaDestino,
					TipoOperacao.TRANSFERENCIA, valor));
			return true;
		}

		throw new Exception();
	}

}
