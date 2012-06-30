package controllers;

import com.google.gson.Gson;

import models.Cliente;
import play.mvc.Controller;

public class Clientes extends Controller {

	public static void create() {
		
		Cliente cliente = new Gson().fromJson(params.get("body"), Cliente.class);

		try {
			
			cliente.create();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response.status = 500;
			
		}
	}
}
