package functional;

import models.Plano;
import models.TipoPlano;

import org.junit.BeforeClass;
import org.junit.Test;

import play.db.jpa.JPA;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;
import util.DatabaseCleaner;

import com.google.gson.Gson;

public class PlanosTest extends FunctionalTest {
	
	@BeforeClass
	public static void setup() {
		
		DatabaseCleaner.cleanUp();
		Fixtures.loadModels("planos.yml");
		
		if (JPA.isInsideTransaction()) {
			JPA.em().getTransaction().commit();
		}
	}
	
	@Test
	public void shouldNotCreateInvalidPlan() {
		
		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano plano = createPlan(internet);
		plano.nome = null;
		
		assertStatus(500, POST("/planos", "application/json", new Gson().toJson(plano)));
	}
	
	@Test
	public void shouldCreateAValidInternetPlan() {
		
		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano plano = createPlan(internet);
		
		assertStatus(200, POST("/planos", "application/json", new Gson().toJson(plano)));
	}
	
	@Test
	public void shouldNotEditPlanWithInvalidId() {

		assertStatus(500, GET("/planos/999"));
	}
	
	@Test
	public void shouldEditAValidPlan() {
		
		Plano plano = Plano.find("byNome", "Ilimitado 1").first();
		Response response = GET("/planos/" + plano.id);
		
		assertStatus(200, response);
		assertEquals(getContent(response), new Gson().toJson(plano));
	}
	
	@Test
	public void shouldNotUpdatePlanWithInvalidId() {

		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano plano = createPlan(internet);
		
		assertStatus(500, POST("/planos/999", "application/json", new Gson().toJson(plano)));
	}
	
	@Test
	public void shouldUpdateAValidPlan() {
		
		TipoPlano internet = TipoPlano.find("byNome", "TV").first();
		Plano plano = createPlan(internet);
		plano.descricao = "Teste 1";
	
		Plano current = Plano.find("byNome", "Ilimitado 1").first();
		assertStatus(200, POST("/planos/" + current.id, "application/json", new Gson().toJson(plano)));
	}

	private Plano createPlan(TipoPlano tipo) {
		
		Plano plano = new Plano();
		plano.nome = "Internet a Vontade";
		plano.descricao = "Internet de alta velocidade";
		plano.preco = 50.0;
		plano.tipo = tipo;
		plano.banda = "2MB";
		
		return plano;
	}
	
}
