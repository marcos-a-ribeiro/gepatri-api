package com.gepatri.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.gepatri.dominio.Marca;
import com.gepatri.services.validation.MarcaValid;

@MarcaValid
public class MarcaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotBlank(message = "Preenchimento ogrigatório")
	@Length(min=2, max=50, message="O tamanho deve ser entre 2 e 50 caracteres")
	private String nome;
	
	public MarcaDTO() {
	}

	public MarcaDTO(Marca marca) {
		this.id = marca.getId();
		this.nome = marca.getNome();
	}
	
	public MarcaDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
