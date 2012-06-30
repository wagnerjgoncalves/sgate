package controllers;

import models.Plano;
import play.mvc.Controller;

import com.google.gson.Gson;

public class Planos extends DefaultController {

	public static void create() {
		
		Plano plano = new Gson().fromJson(params.get("body"), Plano.class);

		try {
			
			plano.save();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response.status = 500;
		}
	}
	
	public static void edit(Long id) {
		
		Plano current = Plano.findById(id);
		returnIfNotFound(current);
		
		renderJSON(current);
	}
	
	public static void update(Long id) {
		
		Plano current = Plano.findById(id);
		returnIfNotFound(current);
		
		Plano updated = new Gson().fromJson(params.get("body"), Plano.class);
		returnIfNotValid(updated);
		
		try {
			
			current.update(updated);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			response.status = 500;
		}
	}
}
