package conta;

import banco.Agencia;
import enums.TipoConta;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(String nome, String cpf, Agencia agencia, Double rendaMensal, Double saldo) {
		super(nome, cpf, TipoConta.POUPANCA, agencia, rendaMensal, saldo);
	}

}
