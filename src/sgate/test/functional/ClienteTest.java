package functional;

import java.util.List;

import models.Cliente;
import models.Endereco;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

import com.google.gson.Gson;

import static util.ClienteTestUtil.*;

public class ClienteTest extends FunctionalTest {

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
}
