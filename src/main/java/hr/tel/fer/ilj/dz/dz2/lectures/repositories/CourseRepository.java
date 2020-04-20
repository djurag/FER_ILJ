package hr.tel.fer.ilj.dz.dz2.lectures.repositories;

import hr.tel.fer.ilj.dz.dz2.lectures.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
