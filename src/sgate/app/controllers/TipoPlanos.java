package controllers;

import java.util.List;

import models.TipoPlano;
import play.mvc.Controller;

public class TipoPlanos extends DefaultController {

  public static void list() {
    
    List<TipoPlano> tipoPlanos = TipoPlano.findAll();
    renderJSON(tipoPlanos);
  }
  
}
