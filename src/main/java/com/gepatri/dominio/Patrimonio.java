package com.gepatri.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SequenceGenerator(name = "tombo_sequence", initialValue = 1, allocationSize = 100)
public class Patrimonio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50)
	private String nome;

	@Column(length = 150)
	private String descricao;

	@JsonIgnore
	@OneToOne(mappedBy = "patrimonio", cascade = CascadeType.ALL)
	private TomboNumero tombo;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@Lob
	private byte[] imagem;

	public Patrimonio() {
		this.setTombo(new TomboNumero());
	}

	public Patrimonio(Integer id, String nome, String descricao, Marca marca) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		if (id==null) {
			this.setTombo(new TomboNumero());
		}
		this.marca = marca;
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

	public void setTombo(TomboNumero tomboNumber) {
		this.tombo = tomboNumber;
		this.tombo.setPatrimonio(this);
	}
	
	public Integer getNumeroTombo() {
		return tombo.getId();
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "Patrimonio [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", tombo=" + tombo + ", marca="
				+ marca + "]";
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
		Patrimonio other = (Patrimonio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
