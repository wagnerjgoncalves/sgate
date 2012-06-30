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
		
		Plano plano = new Plano();
		plano.nome = "Internet a Vontade";
		plano.descricao = "Internet de alta velocidade";
		plano.preco = 50.0;
		plano.tipo = TipoPlano.find("byNome", "Internet").first();
		plano.banda = "2MB";
		
		plano.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotCreateAPlanWithoutName() {

		Plano plano = new Plano();
		plano.descricao = "Internet de alta velocidade";
		plano.preco = 50.0;
		plano.tipo = TipoPlano.find("byNome", "Internet").first();
		plano.banda = "2MB";
		
		plano.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotCreateAPlanWithoutPrice() {

		Plano plano = new Plano();
		plano.nome = "Internet a Vontade";
		plano.descricao = "Internet de alta velocidade";
		plano.tipo = TipoPlano.find("byNome", "Internet").first();
		plano.banda = "2MB";
		
		plano.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotCreateAnInternetPlanWithoutBandwith() {

		Plano plano = new Plano();
		plano.nome = "Internet a Vontade";
		plano.descricao = "Internet de alta velocidade";
		plano.preco = 50.0;
		plano.tipo = TipoPlano.find("byNome", "Internet").first();
		
		plano.save();
	}
	
}
