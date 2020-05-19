package br.org.senairs.apsweb.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ATENDIMENTO")
public class Atendimento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String animal_nome;
	private String animal_raca;
	private String pessoa_nome;
	private String tipoAtendimento;
	private String dataEntrega;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAnimal_nome() {
		return animal_nome;
	}
	public void setAnimal_nome(String animal_nome) {
		this.animal_nome = animal_nome;
	}
	public String getAnimal_raca() {
		return animal_raca;
	}
	public void setAnimal_raca(String animal_raca) {
		this.animal_raca = animal_raca;
	}
	public String getPessoa_nome() {
		return pessoa_nome;
	}
	public void setPessoa_nome(String pessoa_nome) {
		this.pessoa_nome = pessoa_nome;
	}
	public String getTipoAtendimento() {
		return tipoAtendimento;
	}
	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
}
