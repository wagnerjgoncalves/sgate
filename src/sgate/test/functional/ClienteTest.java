package functional;

import java.util.List;

import models.Cliente;
import models.Endereco;

import org.junit.BeforeClass;
import org.junit.Test;

import play.db.jpa.JPA;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;
import util.DatabaseCleaner;

import com.google.gson.Gson;

import static util.ClienteTestUtil.*;

public class ClienteTest extends FunctionalTest {

	@BeforeClass
	public static void setup() {
		
		DatabaseCleaner.cleanUp();
		Fixtures.loadModels("cliente.yml");
		
		if (JPA.isInsideTransaction()) {
			JPA.em().getTransaction().commit();
		}
	}
	
	@Test
	public void shouldNotSaveInvalidCliente() {
		
		Cliente cliente = createCliente();
		cliente.nome = null;
		String json = new Gson().toJson(cliente);
		System.out.println(json);
		Response response = POST("/clientes", "application/json", json);
		assertStatus(500, response);
	}
	
	@Test
	public void shouldNotSave() {
		
		Cliente cliente = createCliente();
		cliente.nome = "Fernando";
		
		Response response = POST("/clientes", "application/json", new Gson().toJson(cliente));
		assertIsOk(response);
	}

	@Test
	public void shouldList() {
		
		Long clientesCount = Cliente.count();
		Response response = GET("/clientes");
		assertIsOk(response);
		
		Cliente [] clientes = new Gson().fromJson(getContent(response), (new Cliente [0]).getClass());
		
		assertEquals(clientesCount.intValue(), clientes.length);
	}
	
	@Test
	public void shouldNotUpdateWithInvalidId() {
		
		Cliente maria = Cliente.find("byNome", "Maria").first();
		
		Response response = POST("/clientes/0", "application/json", new Gson().toJson(maria));
		assertStatus(500, response);
	}
	
	@Test
	public void shouldNotUpdateInvalidCliente() {
		
		Cliente maria = Cliente.find("byNome", "Maria").first();
		maria.nome = null;
		
		Response response = POST("/clientes/" + maria.id, "application/json", new Gson().toJson(maria));
		assertStatus(500, response);
	}
	
	@Test
	public void shouldUpdate() {
		
		Cliente joao = Cliente.find("byNome", "Joao").first();
		joao.nome = "Joao xvi";
		
		Response response = POST("/clientes/" + joao.id, "application/json", new Gson().toJson(joao));
		assertIsOk(response);
	}
}
