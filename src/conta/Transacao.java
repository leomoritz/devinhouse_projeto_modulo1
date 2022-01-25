package conta;

import java.time.LocalDate;

public class Transacao {
	
	private final Conta contaOrigem;
	private final Conta contaDestino;
	private final Double valorTransferido;
	private final LocalDate dataTransferencia;
	
	
	public Transacao(Conta contaOrigem, Conta contaDestino, Double valorTransferido) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valorTransferido = valorTransferido;
		this.dataTransferencia = LocalDate.now();
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


	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

}
