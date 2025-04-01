package br.com.github.jasson.repository;

import br.com.github.jasson.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
