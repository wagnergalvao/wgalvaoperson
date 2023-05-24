package br.com.wgalvao.wgalvaoperson.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wgalvao.wgalvaoperson.controller.PersonController;
import br.com.wgalvao.wgalvaoperson.data.vo.v1.PersonVO;
import br.com.wgalvao.wgalvaoperson.data.vo.v2.PersonVOV2;
import br.com.wgalvao.wgalvaoperson.exceptions.ResourceNotFoundException;
import br.com.wgalvao.wgalvaoperson.mapper.PersonMapper;
import br.com.wgalvao.wgalvaoperson.mapper.v2.PersonMapperV2;
import br.com.wgalvao.wgalvaoperson.model.Person;
import br.com.wgalvao.wgalvaoperson.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapperV2 personMapperV2;

    public List<PersonVO> findAll() {

        logger.info("finding all people!");

        List<PersonVO> response = PersonMapper.parseListObjects(
                personRepository.findAll(),
                PersonVO.class);
        response
        .stream()
        .forEach(item -> item.add(linkTo(methodOn(PersonController.class).findById(item.getKey())).withSelfRel()));
        return response;
    }

    public PersonVO findById(Long id) {

        logger.info("finding one person!");

        PersonVO response = PersonMapper.parseObject(
                recoverPerson(id),
                PersonVO.class);
        return response.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    }

    public PersonVO create(PersonVO personVO) {

        logger.info("creating one person!");

        Person person = PersonMapper.parseObject(personVO, Person.class);
        PersonVO response = PersonMapper.parseObject(
                personRepository.save(person),
                PersonVO.class);

        return response.add(linkTo(methodOn(PersonController.class).findById(response.getKey())).withSelfRel());
    }

    public PersonVOV2 createV2(PersonVOV2 personVOV2) {

        logger.info("creating one person with V2!");

        Person person = personMapperV2.convertVOToEntity(personVOV2);
        PersonVOV2 response = personMapperV2.convertEntityToVO(personRepository.save(person));

        return response.add(linkTo(methodOn(PersonController.class).findById(response.getKey())).withSelfRel());
    }

    public PersonVO update(PersonVO personVO) {

        logger.info("updating one person!");

        Person person = PersonMapper.parseObject(personVO, Person.class);
        Person personEntity = recoverPerson(person.getId());

        personEntity.setFirstName(personVO.getFirstName());
        personEntity.setLastName(personVO.getLastName());
        personEntity.setAddress(personVO.getAddress());
        personEntity.setGender(personVO.getGender());

        PersonVO response = PersonMapper.parseObject(
                personRepository.save(personEntity),
                PersonVO.class);

        return response.add(linkTo(methodOn(PersonController.class).findById(response.getKey())).withSelfRel());
    }

    public void delete(Long id) {

        logger.info("deleting one person!");

        Person personEntity = recoverPerson(id);

        personRepository.delete(personEntity);
    }

    protected Person recoverPerson(Long id) {

        Person response = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "no person found with ID " + id));

        return response;
    }
}
