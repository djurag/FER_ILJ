package hr.tel.fer.ilj.dz.dz2.lectures.repositories;

import hr.tel.fer.ilj.dz.dz2.lectures.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PersonListProjection.class)
public interface PersonRepository extends JpaRepository<Person, Long> {
}
