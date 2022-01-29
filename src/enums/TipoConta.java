package enums;

public enum TipoConta {

	CORRENTE("Corrente"), POUPANCA("Poupan�a"), INVESTIMENTO("Investimento");

	private String tipoConta;

	TipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

}
