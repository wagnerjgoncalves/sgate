package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import exceptions.ValidationException;

import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.db.jpa.Model;
import util.ValidationUtil;

@Entity
@Table(name = "vendas", schema = "sgate")
public class Venda extends Model {

	@Required
	public Date data;
	public Double valor;
	
	@Min(0)
	public Double desconto;
	public Double total;
	
	@Required
	@ManyToOne
	@JoinColumn(name = "id_plano", referencedColumnName = "id")
	public Plano plano;

	@Required
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	public Cliente cliente;
	
	
	@Override
	public Venda save() {
		
		this.validate();
		
		if (this.desconto == null)
			this.desconto = 0d;
			
		this.valor = this.plano.preco;
		this.total = this.valor - this.desconto;
		
		return super.save();
	}


	private void validate() {

		ValidationUtil.validate(this);
		
		if (this.desconto != null && this.desconto >= this.plano.preco)
			throw new ValidationException("O valor do desconto não pode ser superior ao preço do plano.");
	}
}
