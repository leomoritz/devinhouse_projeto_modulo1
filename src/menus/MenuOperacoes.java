package menus;

import java.util.Map.Entry;
import java.util.Scanner;

import banco.Agencia;
import banco.Banco;
import conta.Conta;
import conta.ContaCorrente;
import conta.ContaInvestimento;
import conta.ContaPoupanca;
import conta.ExtratoConta;
import conta.Transacao;
import enums.TipoConta;
import investimento.Investimento;
import investimento.OpcaoInvestimento;

public class MenuOperacoes {

	private final static Scanner sc = new Scanner(System.in);

	public static int apresentaMenuAbertura() {
		System.out.print("\n1 - Cadastrar uma conta" + "\n2 - Realizar Transa��es" + "\n3 - Consultar Saldo"
				+ "\n4 - Consultar Extrato" + "\n5 - Adicionar Investimentos" + "\n6 - Simular Rendimento Investimento"
				+ "\n7 - Simular Rendimento Poupan�a" + "\n8 - Listar Relat�rios" + "\nListar Relat�rios"
				+ "\n\nDigite Op��o Desejada -> ");
		return sc.nextInt();
	}

	public static int apresentaMenuTransacoes() {
		System.out.print("Informe o tipo de transa��o desejada");

		System.out.print("\n1 - Saque" + "\n2 - Dep�sito" + "\n3 - Transefer�ncias" + "\n\nDigite Op��o Desejada -> ");
		return sc.nextInt();
	}

	public static int apresentaMenuNovaOperacao() {
		System.out.print("\nDeseja realizar mais alguma opera��o em nosso banco? (S / N): ");
		char opcao = Character.toUpperCase(sc.next().charAt(0));

		switch (opcao) {
		case 'S':
			return apresentaMenuAbertura();
		case 'N':
			return 0;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! A op��o informada n�o existe.");
		}
	}

	public static int apresentaMenuNovaOperacao(int opcaoOperecao) {
		System.out.print("\nDeseja repetir a opera��o? (S / N): ");
		char opcao = Character.toUpperCase(sc.next().charAt(0));

		switch (opcao) {
		case 'S':
			return opcaoOperecao;
		case 'N':
			return 0;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! A op��o informada n�o existe.");
		}

	}

	public static void apresentaMenuNovaTransacao(Banco banco, Conta conta) throws Exception {
		System.out.print("\nDeseja repetir uma transa��o (Saque / Dep�sito / Transfer�ncia)? (S / N): ");
		char opcao = Character.toUpperCase(sc.next().charAt(0));

		switch (opcao) {
		case 'S':
			menuTransacoes(banco, conta);
		case 'N':
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! A op��o informada n�o existe.");
		}
	}

	public static void menuCadastroConta(Banco banco) throws Exception {

		String nome, cpf;
		Double rendaMensal, valorPrimeiroDeposito;
		Agencia agencia;

		System.out.println("\n##############################################");
		System.out.println("Formul�rio de Cadastro de Conta: \n");
		sc.nextLine();

		System.out.print("Nome Completo: ");
		nome = sc.nextLine();

		System.out.print("CPF: ");
		cpf = sc.nextLine();

		System.out.print("Digite o tipo de conta desejado - 1 - Corrente / 2 - Poupan�a / 3 - Investimento: ");
		char tipoConta = sc.nextLine().charAt(0);

		System.out.print("Digite abaixo o c�digo da ag�ncia: 1 - Florian�polis / 2 - S�o Jos�: ");
		agencia = banco.getAgenciaPeloCodigo(sc.nextInt());

		System.out.print("Renda Mensal: ");
		rendaMensal = sc.nextDouble();

		System.out.print("Deseja realizar um dep�sito inicial? (S / N): ");
		char opcao = Character.toUpperCase(sc.next().charAt(0));

		switch (opcao) {
		case 'S':
			System.out.print("Informe o valor que deseja depositar em sua conta: ");
			valorPrimeiroDeposito = sc.nextDouble();
			break;
		case 'N':
			valorPrimeiroDeposito = 0.0;
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! A op��o informada n�o existe.\n\n");
		}

		switch (tipoConta) {
		case '1':
			banco.cadastrarConta(new ContaCorrente(nome, cpf, agencia, rendaMensal, valorPrimeiroDeposito));
			break;
		case '2':
			banco.cadastrarConta(new ContaPoupanca(nome, cpf, agencia, rendaMensal, valorPrimeiroDeposito));
			break;
		case '3':
			banco.cadastrarConta(new ContaInvestimento(nome, cpf, agencia, rendaMensal, valorPrimeiroDeposito));
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! A op��o informada n�o existe.");
		}

		System.out.println("Conta cadastrada com sucesso!");

	}

	public static Conta menuAcessarConta(Banco banco) throws Exception {

		System.out.print("Informe o c�digo da conta com a qual voc� deseja realizar a opera��o informada: ");
		int codigoConta = sc.nextInt();

		return banco.getContaPeloCodigo(codigoConta);
	}

	public static void menuTransacoes(Banco banco, Conta conta) throws Exception {

		int opcao = apresentaMenuTransacoes();
		double valorTransacao;

		switch (opcao) {
		case 1:
			System.out.print("Informe o valor que deseja sacar da sua conta: ");
			valorTransacao = sc.nextDouble();
			conta.saque(valorTransacao);
			break;
		case 2:
			System.out.print("Informe o valor que deseja depositar em sua conta: ");
			valorTransacao = sc.nextDouble();
			conta.deposito(valorTransacao);
			break;
		case 3:
			System.out.print("Informe o c�digo da conta para qual voc� deseja realizar a transfer�ncia: ");
			int codigoOutraConta = sc.nextInt();
			Conta outraConta = banco.getContaPeloCodigo(codigoOutraConta);
			System.out.print("Informe o valor que voc� deseja transferir para a conta " + outraConta.getConta() + " - "
					+ outraConta.getNome() + ": ");
			valorTransacao = sc.nextDouble();
			conta.transferir(outraConta, valorTransacao);
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! A op��o informada n�o existe.");
		}

		System.out.println("Transa��o realizada com sucesso!");

		apresentaMenuNovaTransacao(banco, conta);
	}

	public static void consultarSaldo(Banco banco) throws Exception {

		Conta conta = menuAcessarConta(banco);

		System.out.println("O saldo da sua conta �: R$" + conta.getSaldo());

		if (conta.getTipoConta().equals(TipoConta.CORRENTE)) {
			ContaCorrente contaCorrente = (ContaCorrente) conta;
			System.out.println("Saldo Cheque Especial: R$" + contaCorrente.getChequeEspecial());
		}
	}

	public static void consultarExtrato(Banco banco) throws Exception {

		Conta conta = menuAcessarConta(banco);

		/*
		 * System.out.println("Informe o per�odo desejado abaixo: ");
		 * 
		 * System.out.print("Data In�cio (dd/mm/yyyy): "); LocalDate dataInicio =
		 * UtilDateTimeFormatter.formataDataParaLocalDate(sc.next());
		 * 
		 * System.out.print("Data Fim (dd/mm/yyyy): "); LocalDate dataFim =
		 * UtilDateTimeFormatter.formataDataParaLocalDate(sc.next());
		 */

		System.out.println("\nExtrato da Conta: ");

		for (ExtratoConta i : conta.getExtratosConta()) {
			System.out.println(i.toString());
		}

	}

	public static void adicionarInvestimento(Banco banco) throws Exception {

		ContaInvestimento conta = (ContaInvestimento) menuAcessarConta(banco);

		if (!conta.getTipoConta().equals(TipoConta.INVESTIMENTO)) {
			throw new Exception("N�o � poss�vel continuar com a opera��o. "
					+ "� necess�rio informar uma conta investimento para esta opera��o.");
		}

		System.out.println("Escolha uma das nossas op��es de investimentos dispon�veis abaixo: \n");

		for (OpcaoInvestimento i : banco.getOpcoesInvestimento()) {
			System.out.println(i.toString());
		}

		System.out.print("\nDigite o c�digo do investimento desejado -> ");
		OpcaoInvestimento opcaoInvestimento = banco.getOpcaoInvestimentoPeloCodigo(sc.nextInt());

		System.out.print("\nInforme o valor que deseja investir: R$");
		Double valorInvestimento = sc.nextDouble();

		banco.cadastrarInvestimentoConta(conta, new Investimento(opcaoInvestimento, valorInvestimento));

		System.out.println("Investimento adicionado com sucesso em sua conta!");

	}

	public static void simularRendimentoInvestimento(Banco banco) throws Exception {

		ContaInvestimento conta = (ContaInvestimento) menuAcessarConta(banco);

		if (!conta.getTipoConta().equals(TipoConta.INVESTIMENTO)) {
			throw new Exception("N�o � poss�vel continuar com a opera��o. "
					+ "� necess�rio informar uma conta investimento para esta opera��o.");
		}

		System.out.println("Escolha um de seus investimentos abaixo para realizar simula��o: \n");

		for (Investimento i : conta.getInvestimentosConta()) {
			System.out.println(i.toString());
		}

		System.out.print("\nDigite o nome do investimento: ");
		Investimento investimento = conta.getInvestimento(sc.nextLine());

		System.out.println("Informe a quantidade de meses: ");
		int qtdMeses = sc.nextInt();

		double taxaRendimento = investimento.getOpcaoInvestimento().getTaxaRendimento();

		double valorRendimento = conta.simulaRendimentoConta(qtdMeses, taxaRendimento);

		System.out.println("O rendimento do saldo atual da conta R$" + conta.getSaldo()
				+ " com a taxa do investimento escolhido de " + taxaRendimento
				+ " na quantidade de meses informada �: R$" + valorRendimento
				+ " \n Total saldo daqui 6 meses considerando o saldo atual: R$"
				+ (conta.getSaldo() + valorRendimento));

	}

	public static void simularRendimentoPoupanca(Banco banco) throws Exception {

		ContaPoupanca conta = (ContaPoupanca) menuAcessarConta(banco);

		if (!conta.getTipoConta().equals(TipoConta.POUPANCA)) {
			throw new Exception("N�o � poss�vel continuar com a opera��o. "
					+ "� necess�rio informar uma conta poupan�a para esta opera��o.");
		}

		System.out.println("Informe a taxa de rendimento da poupan�a: ");
		double taxaRendimento = sc.nextDouble();

		System.out.println("Informe a quantidade de meses para simular o rendimento do seu saldo na poupan�a: ");
		int qtdMeses = sc.nextInt();

		double valorRendimento = conta.simulaRendimentoConta(qtdMeses, taxaRendimento);

		System.out.println("O rendimento do saldo atual da conta R$" + conta.getSaldo()
				+ " com a taxa do investimento escolhido de " + taxaRendimento
				+ " na quantidade de meses informada �: R$" + valorRendimento
				+ " \n Total saldo daqui 6 meses considerando o saldo atual: R$"
				+ (conta.getSaldo() + valorRendimento));

	}

	public static void listarRelatorios(Banco banco) throws Exception {

		System.out.println("Informe qual relat�rio deseja listar: ");

		System.out.println("\n1 - Relat�rio Todas as Contas" + "\n2 - Relat�rio Contas Negativadas"
				+ "\n3 - Relat�rio Total Investido" + "\n4 - Relat�rio Todas Transa��es Por Conta");
		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			listaRelatorioTodasContas(banco);
			break;
		case 2:
			listaRelatorioContasNegativadas(banco);
			break;
		case 3:
			listaRelatorioTotalInvestido(banco);
			break;
		case 4:
			listaRelatorioTodasTransacoesConta(banco);
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! A op��o informada n�o existe.");
		}
	}

	public static void listaRelatorioTodasContas(Banco banco) {

		for (Conta conta : banco.getContas()) {
			System.out.println(conta.toString() + "\n");
		}

	}

	public static void listaRelatorioContasNegativadas(Banco banco) {

		for (Conta conta : banco.getContasNegativas()) {
			System.out.println(conta.toString() + "\n");
		}

	}

	public static void listaRelatorioTotalInvestido(Banco banco) {

		for (Entry<Conta, Double> entry : banco.getTotalInvestimentoConta().entrySet()) {
			System.out.println("Conta: " + entry.getKey() + " | Total Investido R$" + entry.getValue() + "\n");
		}

	}

	public static void listaRelatorioTodasTransacoesConta(Banco banco) throws Exception {
		
		Conta conta = menuAcessarConta(banco);
		
		for (Transacao transacao : banco.getTransacoesConta(conta)) {
			System.out.println(transacao.toString() + "\n");
		}

	}

}
