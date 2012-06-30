package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;


@Entity
@Table(name = "endereco", schema = "sgate")
public class Endereco extends Model {

	public String logradouro;
	public Integer numero;
	public String bairro;
	public String complemento;
	public String cidade;
	public String uf;
	public String cep;
	
	public void updateValues(Endereco endereco) {
		
		this.logradouro = endereco.logradouro;
		this.numero = endereco.numero;
		this.bairro = endereco.bairro;
		this.complemento = endereco.complemento;
		this.cidade = endereco.cidade;
		this.uf = endereco.uf;
		this.cep = endereco.cep;
	}
}
