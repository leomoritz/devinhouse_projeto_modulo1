package menus;

import java.util.Scanner;

import banco.Agencia;
import banco.Banco;
import conta.ContaCorrente;
import conta.ContaPoupanca;

public class MenuOperacoes {

	private final static Scanner sc = new Scanner(System.in);

	public static int apresentaMenuAbertura() {
		System.out.print("\n1 - Cadastrar uma conta" + "\n2 - Acessar sua conta" + "\n3 - Realizar Transa��es"
				+ "\n0 - Sair" + "\n\nDigite Op��o Desejada -> ");
		return sc.nextInt();
	}

	public static int apresentaMenuNovaOperacao() {
		System.out.print("\nDeseja realizar mais alguma opera��o? (S / N): ");
		char opcao = Character.toUpperCase(sc.next().charAt(0));

		switch (opcao) {
		case 'S':
			return apresentaMenuAbertura();
		case 'N':
			return 0;
		default:
			throw new IllegalArgumentException("Op��o inv�lida!");
		}
	}

	public static void menuCadastroConta(Banco banco) throws Exception {

		String nome, sobrenome, cpf;
		Double rendaMensal, valorPrimeiroDeposito;
		Agencia agencia;

		System.out.println("\n##############################################");
		System.out.println("Formul�rio de Cadastro de Conta: \n");

		System.out.print("Nome: ");
		nome = sc.nextLine();
		sc.nextLine();

		System.out.print("Sobrenome: ");
		sobrenome = " " + sc.nextLine();

		System.out.print("CPF: ");
		cpf = sc.nextLine();

		System.out.print("Digite o tipo de conta desejado - 1 - Corrente / 2 - Poupan�a / 3 - Investimento: ");
		char tipoConta = sc.nextLine().charAt(0);

		System.out.print("Digite abaixo o c�digo da ag�ncia - 1 - Florian�polis ou 2 - S�o Jos�: ");
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
			throw new IllegalArgumentException("Op��o inv�lida! As op��es v�lidas s�o: 'S' ou 'N'.");
		}

		switch (tipoConta) {
		case '1':
			banco.cadastrarConta(new ContaCorrente(nome + sobrenome, cpf, agencia, rendaMensal, valorPrimeiroDeposito));
			break;
		case '2':
			banco.cadastrarConta(new ContaPoupanca(nome + sobrenome, cpf, agencia, rendaMensal, valorPrimeiroDeposito));
			break;
		case '3':
			banco.cadastrarConta(new ContaPoupanca(nome + sobrenome, cpf, agencia, rendaMensal, valorPrimeiroDeposito));
			break;
		default:
			throw new IllegalArgumentException("Op��o inv�lida! Tipo de conta informado n�o � v�lido.");
		}

		System.out.println("Conta cadastrada com sucesso!");

	}

}
