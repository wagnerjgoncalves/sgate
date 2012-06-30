package unit;

import java.util.List;

import models.Cliente;
import models.Endereco;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.ValidationException;

import play.db.jpa.JPA;
import play.test.Fixtures;
import play.test.UnitTest;
import util.DatabaseCleaner;

import static util.ClienteTestUtil.*;

public class ClienteTest extends UnitTest {

	@BeforeClass
	public static void setup() {
		
		DatabaseCleaner.cleanUp();
		Fixtures.loadModels("cliente.yml");
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutName() {
		
		Cliente cliente = createCliente();
		cliente.nome = null;		
		cliente.create();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutCPF() {
		
		Cliente cliente = createCliente();
		cliente.cpf = null;		
		cliente.create();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutRG() {
		
		Cliente cliente = createCliente();
		cliente.rg = null;		
		cliente.create();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutFiliacao() {
		
		Cliente cliente = createCliente();
		cliente.filiacao = null;		
		cliente.create();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutTelReferencia() {
		
		Cliente cliente = createCliente();
		cliente.telreferencia = null;		
		cliente.create();
	}
	
	@Test
	public void shouldSave() {
		
		Cliente cliente = createCliente("Alisson");
		cliente.create();
		
		Cliente savedCliente = Cliente.find("byNome", "Alisson").first();
		
		assertNotNull(savedCliente);
		assertNotNull(savedCliente.endereco);
		
		assertEquals("Alisson", savedCliente.nome);
		assertEquals("111.222.333-44", savedCliente.cpf);
		assertEquals("MG-12-456-987", savedCliente.rg);
		assertEquals("Joana maria", savedCliente.filiacao);
		assertEquals("Rua Mathues 13", savedCliente.endereco.logradouro);
		assertEquals(131, savedCliente.endereco.numero.intValue());
		assertEquals("centro", savedCliente.endereco.bairro);
		assertEquals("MG", savedCliente.endereco.uf);
		assertEquals("37000-000", savedCliente.endereco.cep);
	}
}