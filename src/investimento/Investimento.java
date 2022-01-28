package investimento;

import java.time.LocalDate;

public class Investimento {

	private final OpcaoInvestimento opcaoInvestimento;
	private final LocalDate dataInvestimento;
	private Double valorInvestido;

	public Investimento(OpcaoInvestimento opcaoInvestimento, Double valorInvestido) {
		this.opcaoInvestimento = opcaoInvestimento;
		this.dataInvestimento = LocalDate.now();
		this.valorInvestido = valorInvestido;
	}

	public Double getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(Double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public OpcaoInvestimento getOpcaoInvestimento() {
		return opcaoInvestimento;
	}

	public LocalDate getDataInvestimento() {
		return dataInvestimento;
	}

	@Override
	public String toString() {
		return "Investimento: "
				+ "\nNome: " + opcaoInvestimento.getNomeInvestimento()
				+ "\nTaxa Rendimento: " + opcaoInvestimento.getTaxaRendimento()
				+ "\nTaxa Rendimento Anual: " + opcaoInvestimento.getRendimentoAnual()
				+ "\nData Investimento: " + dataInvestimento
				+ "\nValor Investido: " + valorInvestido;
	}
	
	

}
