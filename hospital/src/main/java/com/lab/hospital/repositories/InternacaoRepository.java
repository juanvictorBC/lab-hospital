package com.lab.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.hospital.entities.Internacao;

public interface InternacaoRepository extends JpaRepository<Internacao, Long> {

}
