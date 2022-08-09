package br.com.wgalvao.wgalvaoperson.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wgalvao.wgalvaoperson.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
