package com.gepatri.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gepatri.dominio.Marca;
import com.gepatri.dominio.Patrimonio;
import com.gepatri.dominio.TomboNumero;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatrimonioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "Preenchimento ogrigatório")
	@Length(min=2, max=50, message="O tamanho deve ser entre 2 e 50 caracteres")
	private String nome;

	private String descricao;
	private Integer marcaId;
	
	@JsonIgnore
	private TomboNumero tombo;

	@JsonIgnore
	private Marca marca;
	
	public PatrimonioDTO() {
	}

	public PatrimonioDTO(Patrimonio patri) {
		id = patri.getId();
		nome = patri.getNome();
		descricao = patri.getDescricao();
		marca = patri.getMarca();
		if (patri.getMarca() != null) {
			marcaId = patri.getMarca().getId();
		}
		tombo = patri.getTombo();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TomboNumero getTombo() {
		return tombo;
	}

	public void setTombo(TomboNumero tombo) {
		this.tombo = tombo;
	}
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public String getNomeMarca() {
		if (marca == null) {
			return "";
		}
		return marca.getNome();
	}
	
	public Integer getNumeroTombo() {
		return tombo.getId();
	}

	public Integer getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}

	@Override
	public String toString() {
		return "PatrimonioDTO [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", tombo=" + tombo
				+ ", marca=" + marca + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatrimonioDTO other = (PatrimonioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
