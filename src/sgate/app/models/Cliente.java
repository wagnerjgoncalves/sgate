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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
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
		
		this.id = null;
		
		ValidationUtil.validate(this);
		return super.create();
	}
	
	public void update(Cliente updatedCliente) {
		
		this.nome = updatedCliente.nome;
		this.cpf = updatedCliente.cpf;
		this.filiacao = updatedCliente.filiacao;
		this.rg = updatedCliente.rg;
		this.telcelular = updatedCliente.telcelular;
		this.telfixo = updatedCliente.telfixo;
		this.telreferencia = updatedCliente.telreferencia;
		this.email = updatedCliente.email;
		updateEndereco(updatedCliente.endereco);
		
		if (updatedCliente.endereco != null) {
			
			if (this.endereco == null)
				this.endereco = new Endereco();
		} else {
			
			this.endereco = null;
		}
		
		ValidationUtil.validate(this);
		super.save();
	}
	
	private void updateEndereco(Endereco endereco) {
		
		if (endereco == null) {
			
			this.endereco = null;
			
		} else {
			
			if (this.endereco == null)
				this.endereco = new Endereco();
			
			this.endereco.updateValues(endereco);
		}
	}
}
