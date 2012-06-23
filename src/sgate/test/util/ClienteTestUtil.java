package util;

import models.Cliente;
import models.Endereco;

public class ClienteTestUtil {

	public static Cliente createCliente(String nome) {
		
		Cliente cliente = createCliente();
		cliente.nome = nome;
		
		return cliente;
	}
	
	public static Cliente createCliente() {
		
		Cliente cliente = new Cliente();
		cliente.nome = "Joao";
		cliente.cpf = "111.222.333-44";
		cliente.rg = "MG-12-456-987";
		cliente.filiacao = "Joana maria";
		cliente.telreferencia = "6766-9865";
		
		Endereco endereco = new Endereco();
		endereco.logradouro = "Rua Mathues 13";
		endereco.numero = 131;
		endereco.bairro = "centro";
		endereco.cidade = "Belo Horizonte";
		endereco.uf = "MG";
		endereco.cep = "37000-000";
		
		cliente.endereco = endereco;

		return cliente;
	}

}
