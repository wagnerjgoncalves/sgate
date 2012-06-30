package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import exceptions.ValidationException;

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
		validate();
		return super.save();
	}
	
	private  void validate() {
		validateBandwith();
		ValidationUtil.validate(this);
	}
	
	private void validateBandwith() {
		
		if (this.tipo != null && this.tipo.equals(TipoPlano.find("byNome", "Internet").first()) && 
			(this.banda == null || this.banda.trim().equals(""))) {
			
			throw new ValidationException("banda: this field is required for this type of plan.");
		}
	}
}
