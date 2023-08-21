package edu.pe.idat.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.idat.Model.*;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}
