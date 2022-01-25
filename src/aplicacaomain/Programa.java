package aplicacaomain;

import banco.Agencia;
import banco.Banco;
import conta.Conta;
import conta.ContaCorrente;
import conta.ContaInvestimento;
import conta.ContaPoupanca;

public class Programa {

	public static void main(String[] args) {
		
		Banco banco = new Banco("DEVinMoney", "97.679.103/0001-61");

		Agencia agenciaFlorianopolis = new Agencia(001, "Florian�polis");
		
		banco.getAgencias().add(agenciaFlorianopolis);

		Conta contaCorrente = new ContaCorrente("Le�nidas Guilherme Moritz Pereira Rosa", "09874553979",
				agenciaFlorianopolis, 3500.00, 1500.00);
		
		Conta contaCorrente2 = new ContaCorrente("Bruna de Oliveira Rosa", "09988020937",
				agenciaFlorianopolis, 2500.00, 1500.00);
		
		Conta contaPoupanca = new ContaPoupanca("Le�nidas Guilherme Moritz Pereira Rosa", "09874553979",
				agenciaFlorianopolis, 3500.00, 1500.00);
		
		Conta contaPoupanca2 = new ContaPoupanca("Bruna de Oliveira Rosa", "09988020937",
				agenciaFlorianopolis, 2500.00, 1500.00);
		
		Conta contaInvestimento = new ContaInvestimento("Le�nidas Guilherme Moritz Pereira Rosa", "09874553979",
				agenciaFlorianopolis, 3500.00, 1500.00);
		
		Conta contaInvestimento2 = new ContaInvestimento("Bruna de Oliveira Rosa", "09988020937",
				agenciaFlorianopolis, 2500.00, 1500.00);
		
		banco.getContas().add(contaCorrente);
		banco.getContas().add(contaCorrente2);
		banco.getContas().add(contaPoupanca);
		banco.getContas().add(contaPoupanca2);
		banco.getContas().add(contaInvestimento);
		banco.getContas().add(contaInvestimento2);
		
		for (Conta conta : banco.getContas()) {
			System.out.println(conta.toString());
		}
		

	}

}