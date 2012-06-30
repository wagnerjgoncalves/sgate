package util;

import junit.framework.Assert;
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
	
	public static void assertClientValues(Cliente cliente, String nome) {
		
		Assert.assertEquals(nome, cliente.nome);
		Assert.assertEquals("111.222.333-44", cliente.cpf);
		Assert.assertEquals("MG-12-456-987", cliente.rg);
		Assert.assertEquals("Joana maria", cliente.filiacao);
		Assert.assertEquals("Rua Mathues 13", cliente.endereco.logradouro);
		Assert.assertEquals(131, cliente.endereco.numero.intValue());
		Assert.assertEquals("centro", cliente.endereco.bairro);
		Assert.assertEquals("MG", cliente.endereco.uf);
		Assert.assertEquals("37000-000", cliente.endereco.cep);
	}
}
