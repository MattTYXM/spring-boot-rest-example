package com.mbss.springbootrestexample;

import com.mbss.springbootrestexample.entity.Person;
import com.mbss.springbootrestexample.repository.PersonRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootRestExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestExampleApplication.class, args);
    }

    @Bean
    ApplicationRunner seedData(final PersonRepository personRepository) {
        return args -> {
            final List<Person> people = IntStream.rangeClosed(1, 10)
                    .mapToObj(i -> Person.builder()
                            .firstName("Person")
                            .lastName(String.valueOf(i))
                            .build()
                    )
                    .toList();

            personRepository.saveAll(people);

            people.get(0).setFriends(people.subList(1, 6));
            people.get(4).setFriends(people.subList(5, 10));

            personRepository.saveAll(
                    List.of(people.get(0), people.get(4))
            );
        };
    }
}
