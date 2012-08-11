package unit;

import java.util.Date;

import models.Cliente;
import models.Plano;
import models.Venda;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.db.jpa.JPA;
import play.test.Fixtures;
import play.test.UnitTest;
import util.DatabaseCleaner;
import exceptions.*;

public class VendaTest extends UnitTest {

	private Plano plano;
	private Cliente cliente;
	private Venda venda;
	private Date dataVenda;
	
	@BeforeClass
	public static void setupDatabase() {
		
		DatabaseCleaner.cleanUp();
		Fixtures.loadModels("vendas.yml");
	}
	
	@Before
	public void setup() {
		
		plano = Plano.all().first();
		cliente = Cliente.all().first();
		venda = createVenda();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutCliente() {
		
		venda.cliente = null;
		venda.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutPlano() {
		
		venda.plano = null;
		venda.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithoutData() {
		
		venda.data = null;
		venda.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithNegativeDiscount() {
		
		venda.desconto = -10d;
		venda.save();
	}
	
	@Test(expected = ValidationException.class)
	public void shouldNotSaveWithADiscountGreaterThanPrice() {
		
		venda.desconto = plano.preco + 10;
		venda.save();
	}
	
	
	
	@Test
	public void shouldSaveVendaWithoutDiscount() {
		
		venda.desconto = null;
		Venda savedVenda = venda.save();
		
		JPA.em().clear();
		savedVenda = Venda.findById(savedVenda.id);
		
		assertNotNull(savedVenda);
		assertEquals(venda.plano, plano);
		assertEquals(venda.cliente, cliente);
		assertEquals(venda.data, dataVenda);
		assertEquals(venda.valor, plano.preco);
		assertEquals(venda.desconto, new Double(0));
		assertEquals(venda.total, plano.preco);
	}
	
	@Test
	public void shouldSaveVendaWithDiscount() {
		
		venda.desconto = 12d;
		Venda savedVenda = venda.save();
		
		JPA.em().clear();
		savedVenda = Venda.findById(savedVenda.id);
		
		assertNotNull(savedVenda);
		assertEquals(venda.plano, plano);
		assertEquals(venda.cliente, cliente);
		assertEquals(venda.data, dataVenda);
		assertEquals(venda.valor, plano.preco);
		assertEquals(venda.desconto, new Double(12d));
		assertEquals(venda.total, new Double(plano.preco - 12d));
	}
	
	private Venda createVenda() {
		
		dataVenda = new Date();
		
		Venda venda = new Venda();
		venda.plano = plano;
		venda.cliente = cliente;
		venda.data = dataVenda; 
		venda.desconto = 0d;
		
		return venda;
	}
}
