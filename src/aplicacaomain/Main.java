package aplicacaomain;

import java.util.Locale;
import java.util.Scanner;

import banco.Banco;
import menus.MenuOperacoes;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Banco devInMoney = new Banco();

		devInMoney.iniciaRepositorios();

		System.out.println("####### Bem vindo ao banco " + devInMoney.getNome() + " #######");

		int opcao = 0;

		System.out.print("\nComo podemos te ajudar? Escolha uma das opções abaixo ou digite 0 para sair:\n");
		opcao = MenuOperacoes.apresentaMenuAbertura();

		while (opcao != 0) {

			if (opcao == 1) {
				try {
					MenuOperacoes.menuCadastroConta(devInMoney);
					opcao = MenuOperacoes.apresentaMenuNovaOperacao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (opcao == 2) {
				try {
					MenuOperacoes.menuCadastroConta(devInMoney);
					opcao = MenuOperacoes.apresentaMenuNovaOperacao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (opcao == 3) {
				try {
					MenuOperacoes.menuCadastroConta(devInMoney);
					opcao = MenuOperacoes.apresentaMenuNovaOperacao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		sc.close();

	}

}
