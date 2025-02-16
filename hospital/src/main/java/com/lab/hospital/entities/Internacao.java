package com.lab.hospital.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.hospital.entities.enums.StatusInternacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Internacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "leito_id", nullable = false)
	private Set<Leito> leitos = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "internacao_medico", 
	joinColumns = @JoinColumn(name = "internacao_id"), 
	inverseJoinColumns = @JoinColumn(name = "medico_id"))
	private Set<Medico> medicos = new HashSet<>();

	@Column(name = "data_entrada", nullable = false)
	private LocalDateTime dataEntrada;

	@Column(name = "data_alta")
	private LocalDateTime dataAlta;

	private StatusInternacao status;

	public Internacao() {
	}

	public Internacao(Long id, Paciente paciente, Leito leito, LocalDateTime dataEntrada, LocalDateTime dataAlta,
			StatusInternacao status) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.dataEntrada = dataEntrada;
		this.dataAlta = dataAlta;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Set<Leito> getLeitos() {
		return leitos;
	}

	public Set<Medico> getMedicos() {
		return medicos;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(LocalDateTime dataAlta) {
		this.dataAlta = dataAlta;
	}

	public StatusInternacao getStatus() {
		return status;
	}

	public void setStatus(StatusInternacao status) {
		this.status = status;
	}

	public void adicionarLeito(Leito leito) {
		leitos.add(leito);
	}

	public void removerLeito(Leito leito) {
		leitos.remove(leito);
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
		Internacao other = (Internacao) obj;
		return Objects.equals(id, other.id);
	}

}
