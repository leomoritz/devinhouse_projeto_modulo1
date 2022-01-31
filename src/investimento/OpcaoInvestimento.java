package investimento;

import enums.TipoInvestimento;

public class OpcaoInvestimento {

	private final Integer codigoInvestimento;
	private final TipoInvestimento tipoInvestimento;
	private String nomeInvestimento;
	private Double taxaRendimento;

	public OpcaoInvestimento(Integer codigoInvestimento, TipoInvestimento tipoInvestimento, String nomeInvestimento,
			Double taxaRendimento) {
		this.codigoInvestimento = codigoInvestimento;
		this.tipoInvestimento = tipoInvestimento;
		this.nomeInvestimento = nomeInvestimento;
		this.taxaRendimento = taxaRendimento;
	}

	public Integer getCodigoInvestimento() {
		return codigoInvestimento;
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

	@Override
	public String toString() {
		return "Código: " + codigoInvestimento + " | " + "Nome: " + nomeInvestimento + " | Tipo Investimento: "
				+ tipoInvestimento + " | Taxa Rendimento: " + taxaRendimento + " | Taxa Rendimento Anual: "
				+ getRendimentoAnual();
	}

}
