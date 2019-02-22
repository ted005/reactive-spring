package io.github.ted005.reactivespring.repository;

import io.github.ted005.reactivespring.pojo.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository层仍然是非Reactive的
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

}
