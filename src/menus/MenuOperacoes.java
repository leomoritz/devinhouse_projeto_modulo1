package menus;

import java.util.Scanner;

import banco.Agencia;
import banco.Banco;
import conta.Conta;
import conta.ContaCorrente;
import conta.ContaInvestimento;
import conta.ContaPoupanca;
import conta.ExtratoConta;
import enums.TipoConta;

public class MenuOperacoes {

	private final static Scanner sc = new Scanner(System.in);

	public static int apresentaMenuAbertura() {
		System.out.print("\n1 - Cadastrar uma conta" + "\n2 - Realizar Transa��es" + "\n3 - Consultar Saldo"
				+ "\n4 - Consultar Extrato" + "\n5 - Listar Relat�rios" + "\n0 - Sair"
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
		
		/*System.out.println("Informe o per�odo desejado abaixo: ");
		
		System.out.print("Data In�cio (dd/mm/yyyy): ");
		LocalDate dataInicio = UtilDateTimeFormatter.formataDataParaLocalDate(sc.next());
		
		System.out.print("Data Fim (dd/mm/yyyy): ");
		LocalDate dataFim = UtilDateTimeFormatter.formataDataParaLocalDate(sc.next());*/
		
		System.out.println("Extrato da Conta: \n");
		
		for (ExtratoConta i : conta.getExtratosConta()) {
			System.out.println(i.toString());
		}

	}

}
