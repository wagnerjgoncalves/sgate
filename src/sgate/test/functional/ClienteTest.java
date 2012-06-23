package functional;

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

}
