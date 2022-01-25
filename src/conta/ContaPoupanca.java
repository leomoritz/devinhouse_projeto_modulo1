package conta;

import banco.Agencia;
import enums.TipoConta;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(String nome, String cpf, Agencia agencia, Double rendaMensal, Double saldo) {
		super(nome, cpf, TipoConta.POUPANCA, agencia, rendaMensal, saldo);
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
