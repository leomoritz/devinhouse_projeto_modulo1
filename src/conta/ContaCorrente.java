package conta;

import banco.Agencia;
import enums.TipoConta;
import enums.TipoOperacao;
import excecoes.SaldoInsuficienteException;

public class ContaCorrente extends Conta {

	private Double chequeEspecial;

	public ContaCorrente(String nome, String cpf, Agencia agencia, Double rendaMensal, Double saldo) {
		super(nome, cpf, TipoConta.CORRENTE, agencia, rendaMensal, saldo);
		calculaChequeEspecialConta(rendaMensal * 0.10);
	}

	/*
	 * M�todo criado exclusivamente para calcular o cheque especial da conta que
	 * equivale a 10% da renda mensal
	 */
	private void calculaChequeEspecialConta(Double valor) {
		this.chequeEspecial = valor;
	}

	public Double getChequeEspecial() {
		return chequeEspecial;
	}

	/**
	 * O m�todo desconta o valor do saldo da conta levando em considera��o o cheque
	 * especial.
	 * 
	 * Como o cheque especial � um dinheiro emprestado pelo banco, ele foi
	 * convertido para negativo para que a conta fique negativa at� atingir o valor
	 * do cheque especial, impedindo a conta de sacar valores negativos infinitos.
	 * 
	 * @return true somente se o saldo e o valor forem positivos e a opera��o n�o
	 *         ultrapasse o valor do cheque especial.
	 */
	
	@Override
	public Boolean saque(Double valor) throws Exception {

		if (valor <= 0 || valor == null) {
			throw new IllegalArgumentException("Valor inv�lido para esta opera��o!");
		}

		if ((getSaldo() - valor) >= -(getChequeEspecial())) {
			setSaldo(getSaldo() - valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.SAQUE, valor));
			return true;
		}

		throw new SaldoInsuficienteException();
	}

}
