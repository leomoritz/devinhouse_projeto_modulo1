package conta;

import java.time.LocalDate;

import banco.Agencia;
import enums.TipoOperacao;

public class ExtratoConta {

	private final Agencia agenciaOrigem;
	private final Conta contaOrigem;
	private final Agencia agenciaDestino;
	private final Conta contaDestino;
	private final TipoOperacao tipoOperacao;
	private final Double valor;
	private final LocalDate dataHoraOperacao;

	public ExtratoConta(Agencia agenciaOrigem, Conta contaOrigem, Agencia agenciaDestino, Conta contaDestino,
			TipoOperacao tipoOperacao, Double valor) {
		this.agenciaOrigem = agenciaOrigem;
		this.contaOrigem = contaOrigem;
		this.agenciaDestino = agenciaDestino;
		this.contaDestino = contaDestino;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.dataHoraOperacao = LocalDate.now();
	}

	public ExtratoConta(Agencia agenciaOrigem, Conta contaOrigem, TipoOperacao tipoOperacao, Double valor) {
		this.agenciaOrigem = agenciaOrigem;
		this.contaOrigem = contaOrigem;
		this.agenciaDestino = null;
		this.contaDestino = null;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.dataHoraOperacao = LocalDate.now();
	}

	public Agencia getAgenciaOrigem() {
		return agenciaOrigem;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public Agencia getAgenciaDestino() {
		return agenciaDestino;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public Double getValor() {
		return valor;
	}

	public LocalDate getDataHoraOperacao() {
		return dataHoraOperacao;
	}

}
