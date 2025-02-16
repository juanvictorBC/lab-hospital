package com.lab.hospital.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
public class Internacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "leito_id", nullable = false)
	private Leito leito;

	@ManyToMany
	@JoinTable(name = "internacao_medico",
	joinColumns = @JoinColumn(name = "internacao_id"),
	inverseJoinColumns = @JoinColumn(name = "medico_id"))
	private Set<Medico> medicos = new HashSet<>();

	@Column(name = "data_entrada", nullable = false)
	private LocalDate dataEntrada = LocalDate.now();

	@Column(name = "data_alta")
	private LocalDate dataAlta = LocalDate.now();

	private StatusInternacao status;

	public Internacao() {
	}

	public Internacao(Long id, Paciente paciente, Leito leito, LocalDate dataEntrada, LocalDate dataAlta,
			StatusInternacao status) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.leito = leito;
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

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public Set<Medico> getMedicos() {
		return medicos;
	}



	public LocalDate getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(LocalDate dataAlta) {
		this.dataAlta = dataAlta;
	}

	public StatusInternacao getStatus() {
		return status;
	}

	public void setStatus(StatusInternacao status) {
		this.status = status;
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
