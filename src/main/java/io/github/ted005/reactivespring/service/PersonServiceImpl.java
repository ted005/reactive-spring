package io.github.ted005.reactivespring.service;

import io.github.ted005.reactivespring.pojo.Person;
import io.github.ted005.reactivespring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * 在这一层转换为Reactive数据类型
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public Flux<Person> findAllPersons() {
        Iterable<Person> iterable = personRepository.findAll();
        Flux<Person> personFlux = Flux.fromIterable(iterable);
        return personFlux;
    }
}
