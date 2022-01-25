package investimento;

import enums.TipoInvestimento;

public class Investimento {

	private final TipoInvestimento tipoInvestimento;
	private String nomeInvestimento;
	private Double taxaRendimento;
	
	public Investimento(TipoInvestimento tipoInvestimento, String nomeInvestimento, Double taxaRendimento) {
		this.tipoInvestimento = tipoInvestimento;
		this.nomeInvestimento = nomeInvestimento;
		this.taxaRendimento = taxaRendimento;
	}

	public String getNomeInvestimento() {
		return nomeInvestimento;
	}

	public void setNomeInvestimento(String nomeInvestimento) {
		this.nomeInvestimento = nomeInvestimento;
	}

	public Double getTaxaRendimento() {
		return taxaRendimento;
	}

	public void setTaxaRendimento(Double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}

	public TipoInvestimento getTipoInvestimento() {
		return tipoInvestimento;
	}
	
	public Double getRendimentoAnual() {
		return taxaRendimento * 12;
	}
	
}
