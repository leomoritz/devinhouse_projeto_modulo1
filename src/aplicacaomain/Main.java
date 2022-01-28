package aplicacaomain;

import java.util.Locale;
import java.util.Scanner;

import banco.Banco;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Banco devInMoney = new Banco();
		
		System.out.println("####### Bem vindo ao banco " + devInMoney.getNome() + " #######");
		
		int opcao = 0;
		
		do {
			System.out.println("O que você deseja fazer? Escolha uma das opções abaixo ou digite 0 para sair");
			
			System.out.println("\n1 - Cadastrar uma conta" +
								"\n2 - Acessar sua conta" +
								"\n3 - Realizar Transações");
			
			opcao = sc.nextInt();
			
		}while(opcao != 0);
		
		
		
		sc.close();
		
	}

}
