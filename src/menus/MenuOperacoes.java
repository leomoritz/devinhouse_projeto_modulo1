package menus;

import java.util.Scanner;

import banco.Agencia;
import banco.Banco;
import conta.ContaCorrente;
import conta.ContaPoupanca;

public class MenuOperacoes {

	private final static Scanner sc = new Scanner(System.in);

	public static int apresentaMenuAbertura() {
		System.out.print("\n1 - Cadastrar uma conta" + "\n2 - Acessar sua conta" + "\n3 - Realizar Transações"
				+ "\n0 - Sair" + "\n\nDigite Opção Desejada -> ");
		return sc.nextInt();
	}

	public static int apresentaMenuNovaOperacao() {
		System.out.print("\nDeseja realizar mais alguma operação? (S / N): ");
		char opcao = Character.toUpperCase(sc.next().charAt(0));

		switch (opcao) {
		case 'S':
			return apresentaMenuAbertura();
		case 'N':
			return 0;
		default:
			throw new IllegalArgumentException("Opção inválida!");
		}
	}

	public static void menuCadastroConta(Banco banco) throws Exception {

		String nome, sobrenome, cpf;
		Double rendaMensal, valorPrimeiroDeposito;
		Agencia agencia;

		System.out.println("\n##############################################");
		System.out.println("Formulário de Cadastro de Conta: \n");

		System.out.print("Nome: ");
		nome = sc.nextLine();
		sc.nextLine();

		System.out.print("Sobrenome: ");
		sobrenome = " " + sc.nextLine();

		System.out.print("CPF: ");
		cpf = sc.nextLine();

		System.out.print("Digite o tipo de conta desejado - 1 - Corrente / 2 - Poupança / 3 - Investimento: ");
		char tipoConta = sc.nextLine().charAt(0);

		System.out.print("Digite abaixo o código da agência - 1 - Florianópolis ou 2 - São José: ");
		agencia = banco.getAgenciaPeloCodigo(sc.nextInt());

		System.out.print("Renda Mensal: ");
		rendaMensal = sc.nextDouble();

		System.out.print("Deseja realizar um depósito inicial? (S / N): ");
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
			throw new IllegalArgumentException("Opção inválida! As opções válidas são: 'S' ou 'N'.");
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
			throw new IllegalArgumentException("Opção inválida! Tipo de conta informado não é válido.");
		}

		System.out.println("Conta cadastrada com sucesso!");

	}

}
