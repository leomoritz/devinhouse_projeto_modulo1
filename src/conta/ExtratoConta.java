package conta;

import java.time.LocalDateTime;

import banco.Agencia;
import enums.TipoOperacao;
import util.UtilDateTimeFormatter;

public class ExtratoConta {

	private final Agencia agenciaOrigem;
	private final Conta contaOrigem;
	private final Agencia agenciaDestino;
	private final Conta contaDestino;
	private final TipoOperacao tipoOperacao;
	private final Double valor;
	private final LocalDateTime dataHoraOperacao;

	public ExtratoConta(Agencia agenciaOrigem, Conta contaOrigem, Agencia agenciaDestino, Conta contaDestino,
			TipoOperacao tipoOperacao, Double valor) {
		this.agenciaOrigem = agenciaOrigem;
		this.contaOrigem = contaOrigem;
		this.agenciaDestino = agenciaDestino;
		this.contaDestino = contaDestino;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.dataHoraOperacao = LocalDateTime.now();
	}

	public ExtratoConta(Agencia agenciaOrigem, Conta contaOrigem, TipoOperacao tipoOperacao, Double valor) {
		this.agenciaOrigem = agenciaOrigem;
		this.contaOrigem = contaOrigem;
		this.agenciaDestino = null;
		this.contaDestino = null;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.dataHoraOperacao = LocalDateTime.now();
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

	public LocalDateTime getDataHoraOperacao() {
		return dataHoraOperacao;
	}

	@Override
	public String toString() {
		if (this.tipoOperacao.equals(TipoOperacao.TRANSFERENCIA)) {
			return "\nAgencia Conta Origem: " + agenciaOrigem.toString() + "\nConta Origem: " + contaOrigem.getConta()
					+ " - " + contaOrigem.getNome() + "\nAgencia Conta Destino: " + agenciaDestino.toString()
					+ "\nConta Destino: " + contaDestino.getConta() + " - " + contaDestino.getNome()
					+ "\nTipo Operação: " + tipoOperacao.name() + "\nValor Transferido: " + valor + "\nData/Hora: "
					+ UtilDateTimeFormatter.formataDataParaString(dataHoraOperacao);
		}

		return "\nAgencia Conta: " + agenciaOrigem.toString() + "\nConta: " + contaOrigem.getConta() + " - "
				+ contaOrigem.getNome() + "\nTipo Operação: " + tipoOperacao.name() + "\nValor Operação: " + valor
				+ "\nData/Hora: " + UtilDateTimeFormatter.formataDataParaString(dataHoraOperacao);

	}

}
