package unit;

import models.Plano;
import models.TipoPlano;

import exceptions.ValidationException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;
import util.DatabaseCleaner;

public class PlanoTest extends UnitTest {

	@BeforeClass
	public static void setup() {
		DatabaseCleaner.cleanUp();
		Fixtures.loadModels("planos.yml");
	}
	
	@Test
	public void shouldCreateAValidPlan() {
		
		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano plano = createPlan(internet);
		plano.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotCreateAPlanWithoutName() {

		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano plano = createPlan(internet);
		plano.nome = null;
		plano.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotCreateAPlanWithoutPrice() {

		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano plano = createPlan(internet);
		plano.preco = null;
		
		plano.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotCreateAnInternetPlanWithoutBandwith() {

		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano plano = createPlan(internet);
		plano.banda = null;
		
		plano.save();
	}
	
	@Test
	public void shouldCreateAnTVPlanWithoutBandwith() {
		
		TipoPlano tv = TipoPlano.find("byNome", "TV").first();
		Plano plano = createPlan(tv);
		plano.banda = null;
		plano.save();
	}
	
	@Test
	public void shouldUpdateAValidPlan() {
		
		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano updated = createPlan(internet);
		updated.nome = "Ilimitado 1";
		
		Plano curent = Plano.find("byNome", "Ilimitado 1").first();
		curent.update(updated);
		
		assertEquals(updated.descricao, curent.descricao);
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotUpdateAInvalidPlan() {
		
		TipoPlano internet = TipoPlano.find("byNome", "Internet").first();
		Plano updated = createPlan(internet);
		updated.nome = null;
		
		Plano curent = Plano.find("byNome", "Ilimitado 1").first();
		curent.update(updated);
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
