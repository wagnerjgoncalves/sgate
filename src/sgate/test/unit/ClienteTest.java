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
		assertClientValues(savedCliente, "Alisson");
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotUpdateAnInvalidCliente() {
		
		Cliente maria = Cliente.find("byNome", "Maria").first();
		Cliente updatedClienteData = createCliente();
		updatedClienteData.nome = null;
		
		maria.update(updatedClienteData);
	}
	
	@Test
	public void shouldUpdate() {
		
		Cliente maria = Cliente.find("byNome", "Maria").first();
		Cliente updatedClienteData = createCliente();
		updatedClienteData.nome = "Maria updated";
		
		maria.update(updatedClienteData);
		
		maria = Cliente.findById(maria.id);
		assertClientValues(maria, "Maria updated");
	}
}