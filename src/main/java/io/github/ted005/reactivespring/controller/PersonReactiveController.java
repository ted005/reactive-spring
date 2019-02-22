package io.github.ted005.reactivespring.controller;

import io.github.ted005.reactivespring.pojo.Person;
import io.github.ted005.reactivespring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Spring WebFlux和Spring MVC公用注解
 */
@RestController
@RequestMapping("/")
public class PersonReactiveController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public Flux<Person> findAllPersons() {
        return personService.findAllPersons();
    }
}
