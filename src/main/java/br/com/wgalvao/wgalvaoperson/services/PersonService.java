package br.com.wgalvao.wgalvaoperson.services;

import static br.com.wgalvao.wgalvaoperson.Utils.PersonUtils.createFakePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.wgalvao.wgalvaoperson.model.Person;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {

        logger.info("finding all people!");

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            Person person = createFakePerson();
            person.setId(counter.incrementAndGet());
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {

        logger.info("finding one person!");

        Person person = createFakePerson();
        person.setId(counter.incrementAndGet());

        return person;
    }

    public Person create(Person person) {

        logger.info("creating one person!");

        return person;
    }

    public Person update(Person person) {

        logger.info("updating one person!");

        return person;
    }
}
