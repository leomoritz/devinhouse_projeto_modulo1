package banco;

import java.util.HashSet;
import java.util.Set;

import conta.Conta;
import conta.Transacao;

public class Banco {

	private final String nome;
	private final String cnpj;
	private final Set<Agencia> agencias;
	private final Set<Conta> contas;
	private final Set<Transacao> historicoTransacoes;

	public Banco(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.agencias = new HashSet<>();
		this.contas = new HashSet<>();
		this.historicoTransacoes = new HashSet<>();
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

	/* Cadastros */

	public Boolean cadastrarConta() {
		return null;
	}

	public Boolean cadastrarAgencia() {
		return null;
	}

	public Boolean cadastrarInvestimento() {
		return null;
	}

	public Boolean alterarDadosCadastraisConta() {
		return null;
	}

}
