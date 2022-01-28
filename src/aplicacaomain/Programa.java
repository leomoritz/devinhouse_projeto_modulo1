package aplicacaomain;

import banco.Agencia;
import banco.Banco;
import conta.Conta;
import conta.ContaCorrente;
import conta.ContaInvestimento;
import conta.ContaPoupanca;

public class Programa {

	public static void main(String[] args) {

		Banco banco = new Banco();

		Agencia agenciaFlorianopolis = new Agencia(001, "Florianópolis");

		banco.getAgencias().add(agenciaFlorianopolis);

		ContaCorrente contaCorrente = new ContaCorrente("Leônidas Guilherme Moritz Pereira Rosa", "09874553979",
				agenciaFlorianopolis, 3500.00, 1500.00);

		Conta contaCorrente2 = new ContaCorrente("Bruna de Oliveira Rosa", "09988020937", agenciaFlorianopolis, 2500.00,
				1500.00);

		Conta contaPoupanca = new ContaPoupanca("Leônidas Guilherme Moritz Pereira Rosa", "09874553979",
				agenciaFlorianopolis, 3500.00, 1500.00);

		Conta contaPoupanca2 = new ContaPoupanca("Bruna de Oliveira Rosa", "09988020937", agenciaFlorianopolis, 2500.00,
				1500.00);

		ContaInvestimento contaInvestimento = new ContaInvestimento("Leônidas Guilherme Moritz Pereira Rosa", "09874553979",
				agenciaFlorianopolis, 3500.00, 0.00);

		Conta contaInvestimento2 = new ContaInvestimento("Bruna de Oliveira Rosa", "09988020937", agenciaFlorianopolis,
				2500.00, 1500.00);

		banco.getContas().add(contaCorrente);
		banco.getContas().add(contaCorrente2);
		banco.getContas().add(contaPoupanca);
		banco.getContas().add(contaPoupanca2);
		banco.getContas().add(contaInvestimento);
		banco.getContas().add(contaInvestimento2);

		/*
		 * for (Conta conta : banco.getContas()) { System.out.println(conta.toString());
		 * }
		

		System.out.println("Saldo origem antes: " + contaCorrente.getSaldo());
		System.out.println("Cheque Especial origem antes: " + contaCorrente.getChequeEspecial());

		System.out.println("Saldo destino antes: " + contaCorrente2.getSaldo());

		try {
			System.out.println("Transferindo.... " + contaCorrente.transferir(contaCorrente2, 2000.0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Saldo conta origem: " + contaCorrente.getSaldo());
		System.out.println("Saldo conta destino: " + contaCorrente2.getSaldo());
		
		OpcaoInvestimento opcaoInvestimento = new OpcaoInvestimento(TipoInvestimento.FUNDOS,"OpcaoInvestimento X", 0.47);
		Investimento novoInvestimento = new Investimento(opcaoInvestimento, LocalDate.now().minusMonths(11), 1000.00);
		contaInvestimento.adicionarInvestimento(novoInvestimento);
		
		/*try {
			contaInvestimento.resgatarValorInvestimento(opcaoInvestimento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
			System.out.println("Investimento: " + contaInvestimento.getInvestimento("X"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
