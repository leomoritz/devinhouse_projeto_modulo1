package conta;

import java.time.LocalDateTime;

import util.UtilDateTimeFormatter;

public class Transacao {
	
	private final Conta contaOrigem;
	private final Conta contaDestino;
	private final Double valorTransferido;
	private final LocalDateTime dataTransferencia;
	
	
	public Transacao(Conta contaOrigem, Conta contaDestino, Double valorTransferido) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valorTransferido = valorTransferido;
		this.dataTransferencia = LocalDateTime.now();
	}


	public Conta getContaOrigem() {
		return contaOrigem;
	}


	public Conta getContaDestino() {
		return contaDestino;
	}


	public Double getValorTransferido() {
		return valorTransferido;
	}


	public LocalDateTime getDataTransferencia() {
		return dataTransferencia;
	}


	@Override
	public String toString() {
		return "Conta Origem: " + contaOrigem.getConta() + " - " + contaOrigem.getNome()
				+ "\nConta Destino: " + contaDestino.getConta() + " - " + contaDestino.getNome()
				+ "\nValor Transferido: " + valorTransferido 
				+ "\nData Transferência: " + UtilDateTimeFormatter.formataDataParaString(dataTransferencia);
	}
	
	

}
