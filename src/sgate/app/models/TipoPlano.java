package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "tipos_planos", schema = "sgate")
public class TipoPlano extends Model {
	
	public String nome;

}
