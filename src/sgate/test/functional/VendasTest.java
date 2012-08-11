package functional;

import java.util.Date;

import models.Cliente;
import models.Plano;
import models.TipoPlano;
import models.Venda;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import play.db.jpa.JPA;
import play.test.Fixtures;
import play.test.FunctionalTest;
import util.DatabaseCleaner;

public class VendasTest extends FunctionalTest {

	@BeforeClass
	public static void setup() {
		
		DatabaseCleaner.cleanUp();
		Fixtures.loadModels("vendas.yml");
		
		if (JPA.isInsideTransaction()) {
			JPA.em().getTransaction().commit();
		}
	}
	
	
	@Test
	public void shouldNotCreateInvalidVenda() {
		
		Venda venda = createVenda();
		venda.plano = null;
		System.out.println(new Gson().toJson(venda));
		assertStatus(500, POST("/vendas", "application/json", new Gson().toJson(venda)));
	}
	
	@Test
	public void shouldNotCreatevalidVenda() {
		
		Venda venda = createVenda();
		assertStatus(200, POST("/vendas", "application/json", new Gson().toJson(venda)));
	}
	
	private Venda createVenda() {
		
		Venda venda = new Venda();
		venda.plano = Plano.all().first();
		venda.cliente = Cliente.all().first();
		venda.data = new Date(); 
		venda.desconto = 10d;
		
		return venda;
	}
}
