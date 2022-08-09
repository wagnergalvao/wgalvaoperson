package br.com.wgalvao.wgalvaoperson.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wgalvao.wgalvaoperson.exceptions.ResourceNotFoundException;
import br.com.wgalvao.wgalvaoperson.model.Person;
import br.com.wgalvao.wgalvaoperson.repositories.PersonRepository;

@Service
public class PersonService {

    // private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {

        logger.info("finding all people!");

        return personRepository.findAll();
    }

    public Person findById(Long id) {

        logger.info("finding one person!");

        // Person person = createFakePerson();
        // person.setId(counter.incrementAndGet());

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "no person found with ID " + id));
    }

    public Person create(Person person) {

        logger.info("creating one person!");

        return personRepository.save(person);
    }

    public Person update(Person person) {

        logger.info("updating one person!");

        Person personEntity = recoverPerson(person.getId());
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setAdress(person.getAdress());
        personEntity.setGender(person.getGender());

        return personRepository.save(personEntity);
    }

    public void delete(Long id) {

        logger.info("deleting one person!");

        Person personEntity = recoverPerson(id);

        personRepository.delete(personEntity);
    }

    protected Person recoverPerson(Long id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "no person found with ID " + id));
    }
}
