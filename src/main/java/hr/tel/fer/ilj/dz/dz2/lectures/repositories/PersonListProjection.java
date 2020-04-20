package hr.tel.fer.ilj.dz.dz2.lectures.repositories;

import hr.tel.fer.ilj.dz.dz2.lectures.entities.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "short", types = {Person.class})
public interface PersonListProjection {
    @Value("#{target.firstName} #{target.lastName}")
    String getName();

    Long getId();

    @Value("#{target.firstName}")
    String getFirstName();

    @Value("#{target.lastName}")
    String getLastName();

    @Value("#{target.phone}")
    String getPhone();

    @Value("#{target.room}")
    String getRooom();
}
