package conta;

import banco.Agencia;
import enums.TipoConta;
import util.UtilGeradorCodigoSequencial;

public abstract class Conta {

	private final String nome;
	private final String cpf;
	private final TipoConta tipoConta;
	private final Integer conta;
	private Agencia agencia;
	private Double rendaMensal;
	private Double saldo;

	public Conta(String nome, String cpf, TipoConta tipoConta, Agencia agencia, Double rendaMensal, Double saldo) {
		this.nome = nome;
		this.cpf = cpf;
		this.tipoConta = tipoConta;
		this.conta = UtilGeradorCodigoSequencial.gerarCodigoSequencial();
		this.agencia = agencia;
		this.rendaMensal = rendaMensal;
		this.saldo = saldo;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public Integer getConta() {
		return conta;
	}

	public Double getSaldo() {
		return saldo;
	}

	/*public abstract Boolean saque(double valor);

	public abstract Boolean deposito(double valor);

	public abstract Double saldo();

	public abstract List<Double> extrato();

	public abstract Boolean transferir(Conta destino, Double valor);*/

	@Override
	public String toString() {
		return "Conta [nome=" + nome + ", cpf=" + cpf + ", tipoConta=" + tipoConta + ", conta=" + conta + ", agencia="
				+ agencia + ", rendaMensal=" + rendaMensal + ", saldo=" + saldo + "]";
	}

}
