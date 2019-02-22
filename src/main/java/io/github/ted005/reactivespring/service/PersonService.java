package io.github.ted005.reactivespring.service;

import io.github.ted005.reactivespring.pojo.Person;
import reactor.core.publisher.Flux;

public interface PersonService {

    Flux<Person> findAllPersons();
}
