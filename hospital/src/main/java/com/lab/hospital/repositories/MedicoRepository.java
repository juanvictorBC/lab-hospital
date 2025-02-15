package com.lab.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.hospital.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
