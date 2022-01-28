package conta;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

	public Conta(String nome, String cpf, TipoConta tipoConta, Agencia agencia, Double rendaMensal, Double saldo) {
		this.nome = nome;
		this.cpf = cpf;
		this.tipoConta = tipoConta;
		this.conta = UtilGeradorCodigoSequencial.gerarCodigoSequencial();
		this.agencia = agencia;
		this.rendaMensal = rendaMensal;
		this.saldo = saldo;
		this.extratosConta = new HashSet<>();
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

	public abstract Boolean saque(Double valor) throws Exception;

	public Boolean deposito(Double valor) throws Exception {

		if (valor > 0.0) {
			setSaldo(getSaldo() + valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.DEPOSITO, valor));
			return true;
		}

		throw new DepositoNegativoException();

	}

	/*
	 * O método transferir reaproveita os métodos sacar e depósito, bem como suas
	 * respectivas exceções
	 */

	public Boolean transferir(Conta destino, Double valor) throws Exception {

		DayOfWeek diaAtualDaSemana = LocalDate.now().getDayOfWeek();

		if (diaAtualDaSemana == DayOfWeek.SATURDAY || diaAtualDaSemana == DayOfWeek.SUNDAY) {
			throw new TransacaoIlegalException(
					"Transação negada. Não é possível realizar transferências no Sábado ou Domingo.");
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
			return true;
		}

		throw new TransacaoIlegalException();
	}

	public Set<ExtratoConta> extrato(LocalDate dataInicio, LocalDate dataFinal) {
		return getExtratosConta().stream().filter(extrato -> extrato.getDataHoraOperacao().isAfter(dataInicio)
				&& extrato.getDataHoraOperacao().isBefore(dataFinal)).collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return "\nConta: " + conta + "\nNome: " + nome + "\nCPF: " + cpf + "\nTipo Conta: " + tipoConta + "\nAgencia: "
				+ agencia + "\nRenda Mensal: " + rendaMensal + "\nSaldo Conta: " + saldo;
	}

}
