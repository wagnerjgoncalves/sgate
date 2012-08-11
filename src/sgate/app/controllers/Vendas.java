package controllers;

import models.Cliente;
import models.Venda;

import com.google.gson.Gson;

import play.mvc.Controller;

public class Vendas extends Controller {

	public static void create() {
		
		Venda venda = new Gson().fromJson(params.get("body"), Venda.class);

		try {
			
			venda.save();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response.status = 500;
		}
	}
}
