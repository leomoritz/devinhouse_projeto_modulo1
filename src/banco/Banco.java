package banco;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import conta.Conta;
import conta.ContaInvestimento;
import conta.Transacao;
import enums.TipoConta;
import excecoes.CadastroIlegalException;
import excecoes.CadastroInexistenteException;
import excecoes.CadastroJaExisteException;
import investimento.Investimento;
import investimento.OpcaoInvestimento;
import repositorio.RepositorioAgencia;
import repositorio.RepositorioOpcaoInvestimento;
import util.UtilValidadorCpf;
import util.UtilValidadorNome;

public class Banco {

	private final String nome;
	private final String cnpj;
	private final Set<Agencia> agencias;
	private final Set<Conta> contas;
	private final Set<OpcaoInvestimento> opcoesInvestimento;
	private final Set<Transacao> historicoTransacoes;

	public Banco() {
		this.nome = "DEVinMoney";
		this.cnpj = "97.679.103/0001-61";
		this.agencias = new HashSet<>();
		this.contas = new HashSet<>();
		this.historicoTransacoes = new HashSet<>();
		this.opcoesInvestimento = new HashSet<>();
		iniciaRepositorios();
	}

	/*
	 * Método específico para iniciar instâncias de cadastro pré-existentes, visto
	 * que a interação é com o usuário cliente que utilizará funcionalidades
	 * relacionadas a sua conta.
	 */

	public void iniciaRepositorios() {
		RepositorioOpcaoInvestimento.iniciaRepositorioOpcaoInvestimento(this);
		RepositorioAgencia.iniciaRepositorioOpcaoInvestimento(this);
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Set<Agencia> getAgencias() {
		return agencias;
	}

	public Set<Transacao> getHistoricoTransacoes() {
		return historicoTransacoes;
	}

	public Set<OpcaoInvestimento> getOpcoesInvestimento() {
		return opcoesInvestimento;
	}

	/*
	 * Métodos para listagem de relatórios
	 */

	public Set<Conta> getContas() {
		return contas;
	}

	public Set<Conta> getContasNegativas() {
		return getContas().stream().filter(conta -> conta.getSaldo() < 0).collect(Collectors.toSet());
	}

	public Map<Conta, Double> getTotalInvestimentoConta() {

		Set<Conta> contasInvestimento = getContas().stream()
				.filter(conta -> conta.getTipoConta().equals(TipoConta.INVESTIMENTO)).collect(Collectors.toSet());

		Map<Conta, Double> totalInvestimentosConta = new HashMap<>();

		for (Conta i : contasInvestimento) {
			totalInvestimentosConta.put(i, i.getSaldo());
		}

		return totalInvestimentosConta;

	}

	public Set<Transacao> getTransacoesConta(Conta conta) {
		return getHistoricoTransacoes().stream().filter(contaAux -> contaAux.equals(conta)).collect(Collectors.toSet());
	}

	/*
	 * Métodos de cadastro do Banco
	 */

	public Boolean cadastrarConta(Conta novaConta) throws Exception {
		if (novaConta == null) {
			throw new CadastroIlegalException();
		}

		if (getContaJaExistente(novaConta).isPresent()) {
			throw new CadastroJaExisteException();
		}

		validaNomeCpfCadastroConta(novaConta);

		contas.add(novaConta);
		return true;
	}

	public Boolean validaNomeCpfCadastroConta(Conta novaConta) throws Exception {

		if (!UtilValidadorNome.validadorNome(novaConta.getNome())) {
			throw new CadastroIlegalException(
					"O nome informado é inválido. O nome precisa conter apenas letras e pelo menos um "
							+ "sobrenome. Caracteres especiais são ignorados.");
		}

		if (!UtilValidadorCpf.validadorCpf(novaConta.getCpf())) {
			throw new CadastroIlegalException(
					"CPF inválido. Favor informe CPF com 11 dígitos no formato 99999999999 ou 999.999.999-99");
		}

		return true;

	}

	public Boolean cadastrarInvestimentoConta(ContaInvestimento conta, Investimento investimento) throws Exception {

		if (conta.getTipoConta() != TipoConta.INVESTIMENTO) {
			throw new CadastroIlegalException("Só é possível cadastrar investimentos em contas do tipo Investimento.");
		}

		if (investimento.getOpcaoInvestimento() != null && investimento.getValorInvestido() != 0.0) {
			conta.adicionarInvestimento(investimento);
			return true;
		}

		throw new CadastroIlegalException();

	}

	/*
	 * Métodos de busca/pesquisa de cadastro existentes no Banco com base no código
	 * do cadastro
	 */

	public Conta getContaPeloCodigo(Integer codigoConta) throws CadastroInexistenteException {

		Optional<Conta> conta = getContas().stream().filter(contaAux -> contaAux.getConta().equals(codigoConta))
				.findFirst();

		if (conta.isPresent()) {
			return conta.get();
		}

		throw new CadastroInexistenteException();

	}

	public Agencia getAgenciaPeloCodigo(Integer codigo) throws CadastroInexistenteException {

		Optional<Agencia> agencia = getAgencias().stream().filter(agenciaAux -> agenciaAux.getCodigo().equals(codigo))
				.findFirst();

		if (agencia.isPresent()) {
			return agencia.get();
		}

		throw new CadastroInexistenteException();

	}

	public OpcaoInvestimento getOpcaoInvestimentoPeloCodigo(Integer codigo) throws CadastroInexistenteException {

		Optional<OpcaoInvestimento> opcao = getOpcoesInvestimento().stream()
				.filter(opcaoAux -> opcaoAux.getCodigoInvestimento().equals(codigo)).findFirst();

		if (opcao.isPresent()) {
			return opcao.get();
		}

		throw new CadastroInexistenteException();

	}

	/*
	 * Busca a nova conta informada na lista de contas do banco para verificar se já
	 * existe uma conta com o mesmo cpf e tipo cadastrado.
	 */

	private Optional<Conta> getContaJaExistente(Conta novaConta) {
		return getContas().stream().filter(contaAux -> contaAux.getCpf().equals(novaConta.getCpf())
				&& contaAux.getTipoConta().equals(novaConta.getTipoConta())).findFirst();
	}

	/**
	 * Método que verifica se a conta informada existe e em seguida altera a
	 * propriedade do usuário conforme a opção e valor escolhidos pelo mesmo.
	 * 
	 * @param conta que será alvo da alteração
	 * @param opcao da propriedade a ser alterada
	 * @param valor da propriedade que será alterada
	 * @return true se a propriedade da conta foi alterada com sucesso.
	 * @throws CadastroInexistenteException
	 * @throws IllegalArgumentException
	 */

	public Boolean alterarDadosCadastraisConta(Conta conta, Integer opcao, String valor) throws Exception {

		if (!getContas().contains(conta)) {
			throw new CadastroInexistenteException();
		}

		switch (opcao) {

		case 1:
			conta.setNome(valor);
			return true;

		case 2:
			conta.setRendaMensal(Double.valueOf(valor));
			return true;

		default:
			throw new IllegalArgumentException("A opção informada é inválida!");
		}

	}
}
