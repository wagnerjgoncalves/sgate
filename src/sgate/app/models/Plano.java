package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;
import util.ValidationUtil;

@Entity
@Table(name = "planos", schema = "sgate")
public class Plano extends Model {

	@Required
	public String nome;

	public String descricao;
	
	@Required
	public Double preco;
	
	public String banda;

	@ManyToOne
	@JoinColumn(name = "id_tipo_plano", referencedColumnName = "id")
	public TipoPlano tipo;

	@Override
	public Plano save() {

		ValidationUtil.validate(this);
		return super.save();
	}
}
