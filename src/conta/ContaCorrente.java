package conta;

import banco.Agencia;
import enums.TipoConta;

public class ContaCorrente extends Conta {

	private Double chequeEspecial;

	public ContaCorrente(String nome, String cpf, Agencia agencia, Double rendaMensal, Double saldo) {
		super(nome, cpf, TipoConta.CORRENTE, agencia, rendaMensal, saldo);
		this.chequeEspecial = rendaMensal * 0.10; // 10% da renda mensal
	}

	public Double getChequeEspecial() {
		return chequeEspecial;
	}

	public void aumentarChequeEspecial() {
		
		double valorRendaMensal = getRendaMensal() * 0.10;
		
		if(valorRendaMensal < chequeEspecial) {
			setRendaMensal(valorRendaMensal); 
		}
		
	}
	
}
