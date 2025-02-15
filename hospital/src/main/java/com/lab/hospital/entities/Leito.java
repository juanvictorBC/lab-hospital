package com.lab.hospital.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Leito {

	private Long id;
	private String numero;
	private String setor;

	private Set<Internacao> internacoes = new HashSet<>();

	public Leito() {
	}

	public Leito(Long id, String numero, String setor) {
		super();
		this.id = id;
		this.numero = numero;
		this.setor = setor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public void setInternacoes(Set<Internacao> internacoes) {
		this.internacoes = internacoes;
	}

	public Set<Internacao> getInternacoes() {
		return internacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leito other = (Leito) obj;
		return Objects.equals(id, other.id);
	}

}
