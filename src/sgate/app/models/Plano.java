package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;
import util.ValidationUtil;
import exceptions.ValidationException;

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
	
	public void update(Plano plano) {
		
		this.nome = plano.nome;
		this.descricao = plano.descricao;
		this.preco = plano.preco;
		this.banda = plano.banda;
		this.tipo = plano.tipo;
		
		this.save();
	}

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
