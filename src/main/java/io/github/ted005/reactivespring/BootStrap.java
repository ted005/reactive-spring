package io.github.ted005.reactivespring;

import io.github.ted005.reactivespring.pojo.Person;
import io.github.ted005.reactivespring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Person person1 = new Person("ted", 1234, "shanghai");
        personRepository.save(person1);

        Person person2 = new Person("mattie", 9876, "beijing");
        personRepository.save(person2);

    }
}
