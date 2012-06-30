package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import exceptions.ValidationException;

import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.Model;
import util.ValidationUtil;


@Entity
@Table(name = "cliente", schema = "sgate")
public class Cliente extends Model {

	@Required
	public String nome;
	
	@Required
	public String cpf;
	
	@Required
	public String filiacao;
	
	@Required
	public String rg;
	
	@Required
	public String telreferencia;
	
	public String telcelular;
	public String telfixo;
	public String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_endereco", referencedColumnName="id")
	public Endereco endereco;

	@Override
	public boolean create() {
		
		ValidationUtil.validate(this);
		return super.create();
	}	
}
