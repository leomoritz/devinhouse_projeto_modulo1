package conta;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import banco.Agencia;
import enums.TipoConta;
import enums.TipoOperacao;
import excecoes.DepositoNegativoException;
import excecoes.TransacaoIlegalException;
import util.UtilGeradorCodigoSequencial;

public abstract class Conta {

	private String nome;
	private final String cpf;
	private final TipoConta tipoConta;
	private final Integer conta;
	private Agencia agencia;
	private Double rendaMensal;
	private Double saldo;
	private final Set<ExtratoConta> extratosConta;
	private final Set<Transacao> transacoesConta;

	public Conta(String nome, String cpf, TipoConta tipoConta, Agencia agencia, Double rendaMensal,
			Double valorPrimeiroDeposito) throws Exception {
		this.nome = nome;
		this.cpf = cpf;
		this.tipoConta = tipoConta;
		this.conta = UtilGeradorCodigoSequencial.gerarCodigoSequencial();
		this.agencia = agencia;
		this.rendaMensal = rendaMensal;
		this.extratosConta = new HashSet<>();
		this.transacoesConta = new HashSet<>();
		this.saldo = 0.0;
		this.depositoAberturaConta(valorPrimeiroDeposito);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public Integer getConta() {
		return conta;
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getCpf() {
		return cpf;
	}

	public Set<ExtratoConta> getExtratosConta() {
		return extratosConta;
	}

	public Set<Transacao> getTransacoesConta() {
		return transacoesConta;
	}

	public abstract Boolean saque(Double valor) throws Exception;

	public Boolean deposito(Double valor) throws Exception {

		if (valor > 0.0) {
			setSaldo(saldo + valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.DEPOSITO, valor));
			return true;
		}

		/*
		 * Lógica criada para a idéia de um primeiro depósito por exemplo, onde o
		 * usuário não pretende realizar um depósito inicial
		 */

		throw new DepositoNegativoException();

	}

	private Boolean depositoAberturaConta(Double valor) throws Exception {

		if (valor == 0) {
			return false;
		}

		deposito(valor);
		return true;
	}

	/*
	 * O método transferir reaproveita os métodos sacar e depósito, bem como suas
	 * respectivas exceções
	 */

	public Boolean transferir(Conta destino, Double valor) throws Exception {

		DayOfWeek diaAtualDaSemana = LocalDate.now().getDayOfWeek();

		if (diaAtualDaSemana == DayOfWeek.SATURDAY || diaAtualDaSemana == DayOfWeek.SUNDAY) {
			throw new TransacaoIlegalException(
					"Transação negada. Não é possível realizar transferências nos Sábados ou Domingos.");
		}

		if (destino == this) {
			throw new TransacaoIlegalException(
					"Transação negada. Não é possível realizar transferências para si próprio. Utilize a operação de "
							+ "depósito.");
		}

		if (saque(valor)) {
			destino.deposito(valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, destino.getAgencia(), destino,
					TipoOperacao.TRANSFERENCIA, valor));
			getTransacoesConta().add(new Transacao(this, destino, valor));
			return true;
		}

		throw new TransacaoIlegalException();
	}

	@Override
	public String toString() {
		return "\nConta: " + conta + "\nNome: " + nome + "\nCPF: " + cpf + "\nTipo Conta: " + tipoConta + "\nAgencia: "
				+ agencia + "\nRenda Mensal: " + rendaMensal + "\nSaldo Conta: " + saldo;
	}

}
