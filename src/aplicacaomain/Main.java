package aplicacaomain;

import java.util.Locale;
import java.util.Scanner;

import banco.Banco;
import conta.Conta;
import menus.MenuOperacoes;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		Banco devInMoney = new Banco();

		System.out.println("####### Bem vindo ao banco " + devInMoney.getNome() + " #######");

		int opcao = 0;

		System.out.print("\nComo podemos te ajudar? Escolha uma das opções abaixo ou digite 0 para sair:\n");
		opcao = MenuOperacoes.apresentaMenuAbertura();

		while (opcao != 0) {

			if (opcao == 1) {
				try {
					MenuOperacoes.menuCadastroConta(devInMoney);
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				opcao = MenuOperacoes.apresentaMenuNovaOperacao(1);
			}

			if (opcao == 2) {
				try {
					Conta conta = MenuOperacoes.menuAcessarConta(devInMoney);
					MenuOperacoes.menuTransacoes(devInMoney, conta);
					opcao = 0;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
					opcao = MenuOperacoes.apresentaMenuNovaOperacao(2);
				}
			}

			if (opcao == 3) {
				try {
					MenuOperacoes.consultarSaldo(devInMoney);
					opcao = 0;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				opcao = MenuOperacoes.apresentaMenuNovaOperacao(3);
			}

			if (opcao == 4) {
				try {
					MenuOperacoes.consultarExtrato(devInMoney);
					opcao = 0;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				opcao = MenuOperacoes.apresentaMenuNovaOperacao(4);
			}

			if (opcao == 5) {
				try {
					MenuOperacoes.adicionarInvestimento(devInMoney);
					opcao = 0;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
					opcao = MenuOperacoes.apresentaMenuNovaOperacao(5);
				}
			}
			
			if (opcao == 6) {
				try {
					MenuOperacoes.simularRendimentoInvestimento(devInMoney);
					opcao = 0;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
					opcao = MenuOperacoes.apresentaMenuNovaOperacao(6);
				}
			}
			
			if (opcao == 7) {
				try {
					MenuOperacoes.simularRendimentoPoupanca(devInMoney);
					opcao = 0;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
					opcao = MenuOperacoes.apresentaMenuNovaOperacao(7);
				}
			}
			
			if (opcao == 8) {
				try {
					MenuOperacoes.listarRelatorios(devInMoney);
					opcao = 0;
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
					opcao = MenuOperacoes.apresentaMenuNovaOperacao(8);
				}
			}

			if (opcao == 0) {
				opcao = MenuOperacoes.apresentaMenuNovaOperacao();
			}

		}

		System.out.println("\nO DevInMoney agradece por sua preferência! \nVolte Sempre :)");
		sc.close();

	}

}
