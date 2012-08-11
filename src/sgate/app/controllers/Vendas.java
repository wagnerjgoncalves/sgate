package controllers;

import models.Cliente;
import models.Plano;
import models.Venda;

import com.google.gson.Gson;

import play.mvc.Controller;

public class Vendas extends Controller {

	public static void create() {
		
		Venda venda = new Gson().fromJson(params.get("body"), Venda.class);

		try {
			
			venda.plano = Plano.findById(venda.plano.id);
			venda.cliente = Cliente.findById(venda.plano.id);
			venda.save();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response.status = 500;
		}
	}
}
