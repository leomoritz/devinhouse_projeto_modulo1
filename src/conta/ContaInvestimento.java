package conta;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import banco.Agencia;
import enums.TipoConta;
import enums.TipoOperacao;
import excecoes.SaldoInsuficienteException;
import interfaces.SimulacaoRendimentoConta;
import investimento.Investimento;

public class ContaInvestimento extends Conta implements SimulacaoRendimentoConta {

	private final Set<Investimento> investimentosConta;

	public ContaInvestimento(String nome, String cpf, Agencia agencia, Double rendaMensal, Double valorPrimeiroDeposito)
			throws Exception {
		super(nome, cpf, TipoConta.INVESTIMENTO, agencia, rendaMensal, valorPrimeiroDeposito);
		this.investimentosConta = new HashSet<>();
	}

	public Set<Investimento> getInvestimentosConta() {
		return investimentosConta;
	}

	public Investimento getInvestimento(String nomeInvestimento) throws Exception {

		if (nomeInvestimento == null) {
			throw new IllegalArgumentException("É necessário informar o investimento que deseja buscar.");
		}

		Optional<Investimento> investimento = getInvestimentosConta().stream()
				.filter(i -> i.getOpcaoInvestimento().getNomeInvestimento().contains(nomeInvestimento)).findFirst();

		if (investimento.isEmpty()) {
			throw new Exception("Não foi encontrado nenhum investimento com este nome na conta informada.");
		}

		return investimento.get();
	}

	public void adicionarInvestimento(Investimento novoInvestimento) throws Exception {
		getInvestimentosConta().add(novoInvestimento);
		deposito(novoInvestimento.getValorInvestido());
	}

	@Override
	public Boolean saque(Double valor) throws Exception {

		if (valor <= 0 || valor == null) {
			throw new IllegalArgumentException("Valor inválido para esta operação!");
		}

		if ((getSaldo() - valor) >= 0.0) {
			setSaldo(getSaldo() - valor);
			getExtratosConta().add(new ExtratoConta(getAgencia(), this, TipoOperacao.SAQUE, valor));
			return true;
		}

		throw new SaldoInsuficienteException();
	}

	@Override
	public double simulaRendimentoConta(int qtdMeses, double taxaRendimento) {

		if (qtdMeses <= 0.0 || taxaRendimento <= 0.0) {
			throw new IllegalArgumentException("Valor inválido para esta operação. "
					+ "A quantidade de meses e a taxa de rendimento precisam ser maior que zero");
		}

		return (getSaldo() * (taxaRendimento / 100)) * qtdMeses;
	}

}
