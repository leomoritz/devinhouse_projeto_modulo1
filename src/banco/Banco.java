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

	public Set<Conta> getContas() {
		return contas;
	}

	public Set<Transacao> getHistoricoTransacoes() {
		return historicoTransacoes;
	}

	public Set<OpcaoInvestimento> getOpcoesInvestimento() {
		return opcoesInvestimento;
	}

	/*
	 * Métodos de cadastro do Banco
	 */

	public Boolean cadastrarConta(Conta novaConta) throws Exception {

		if (novaConta == null) {
			throw new CadastroIlegalException();
		}

		if (!UtilValidadorCpf.validadorCpf(novaConta.getCpf())) {
			throw new CadastroIlegalException(
					"CPF inválido. Favor informe CPF no formato 99999999999 ou 999.999.999-99");
		}

		if (getContaJaExistente(novaConta).isPresent()) {
			throw new CadastroJaExisteException();
		}

		contas.add(novaConta);
		return true;

	}

	public Boolean cadastrarInvestimentoConta(ContaInvestimento conta, Investimento investimento) throws Exception {

		if (conta.getTipoConta() != TipoConta.INVESTIMENTO) {
			throw new CadastroIlegalException("Só é possível cadastrar investimentos em contas do tipo Investimento.");
		}

		if (investimento.getOpcaoInvestimento() != null && investimento.getValorInvestido() != 0.0) {
			conta.adicionarInvestimento(investimento);
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

	/*
	 * Busca a nova conta informada na lista de contas do banco para verificar se já
	 * existe uma conta com o mesmo cpf e tipo cadastrado.
	 */

	private Optional<Conta> getContaJaExistente(Conta novaConta) {
		return getContas().stream()
				.filter(contaAux -> contaAux.getCpf()
						.equals(novaConta.getCpf() + contaAux.getTipoConta().equals(novaConta.getTipoConta())))
				.findFirst();
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

	/*
	 * Métodos para listagem de relatórios
	 */

	public String listarRelatorioContas() {
		return "Relatório de Contas cadastradas no Banco:" + getContas().stream().toString();
	}

	public String listarRelatorioContasNegativas() {
		return "Relatório de Contas Negativas:" + getContas().stream().filter(conta -> conta.getSaldo() < 0).toString();
	}

	public String listaTotalInvestimentosContas() {
		
		Set<Conta> contasInvestimento =  getContas()
										.stream()
										.filter(conta -> conta.getTipoConta().equals(TipoConta.INVESTIMENTO))
										.collect(Collectors.toSet());
		
		Map<Conta, Double> totalInvestimentosConta = new HashMap<>();
		
		for (Conta i : contasInvestimento) {
			totalInvestimentosConta.put(i, i.getSaldo());
		}
		
		return "Relatório Total Investimentos da Conta:" + totalInvestimentosConta.entrySet().stream().toString();

	}
	
	public String listarRelatorioTransacoesConta(Conta conta) {
		return "Relatório de Transações da Conta:" + getHistoricoTransacoes().toString();
	}
	
	

}
