package controllers;

import java.util.List;

import org.junit.BeforeClass;

import com.google.gson.Gson;

import models.Cliente;
import play.mvc.Controller;
import play.test.Fixtures;
import util.DatabaseCleaner;

public class Clientes extends Controller {

	
	public static void create() {
		
		Cliente cliente = new Gson().fromJson(params.get("body"), Cliente.class);

		try {
			
			cliente.save();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response.status = 500;
		}
	}
	
	public static void update(Long id) {
	
		try {
			Cliente cliente = new Gson().fromJson(params.get("body"), Cliente.class);
			Cliente storedCliente = Cliente.findById(id);
			
			storedCliente.update(cliente);
		} catch(Exception e) {
			
			e.printStackTrace();
			response.status = 500;
		}
	}
	
	public static void list() {
		
		List<Cliente> clientes = Cliente.all().fetch();
		renderJSON(clientes);
	}
	
	public static void show(Long id) {
		
		Cliente cliente  = Cliente.findById(id);
		
		if (cliente == null) {
			
			response.status = 500;
			
		} else {
			
			renderJSON(cliente);
		}
	}
}
