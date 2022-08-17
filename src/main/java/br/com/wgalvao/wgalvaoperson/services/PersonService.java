package br.com.wgalvao.wgalvaoperson.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wgalvao.wgalvaoperson.data.vo.v1.PersonVO;
import br.com.wgalvao.wgalvaoperson.data.vo.v2.PersonVOV2;
import br.com.wgalvao.wgalvaoperson.exceptions.ResourceNotFoundException;
import br.com.wgalvao.wgalvaoperson.mapper.PersonMapper;
import br.com.wgalvao.wgalvaoperson.mapper.v2.PersonV2Mapper;
import br.com.wgalvao.wgalvaoperson.model.Person;
import br.com.wgalvao.wgalvaoperson.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonV2Mapper personV2Mapper;

    public List<PersonVO> findAll() {

        logger.info("finding all people!");

        return PersonMapper.parseListObjects(
                personRepository.findAll(),
                PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("finding one person!");

        return PersonMapper.parseObject(
                recoverPerson(id),
                PersonVO.class);
    }

    public PersonVO create(PersonVO personVO) {

        logger.info("creating one person!");

        Person person = PersonMapper.parseObject(personVO, Person.class);
        PersonVO response = PersonMapper.parseObject(
                personRepository.save(person),
                PersonVO.class);

        return response;
    }

    public PersonVOV2 createV2(PersonVOV2 personVOV2) {

        logger.info("creating one person with V2!");

        Person person = personV2Mapper.convertVOToEntity(personVOV2);
        PersonVOV2 response = personV2Mapper.convertEntityToVO(personRepository.save(person));

        return response;
    }

    public PersonVO update(PersonVO personVO) {

        logger.info("updating one person!");

        Person person = PersonMapper.parseObject(personVO, Person.class);
        Person personEntity = recoverPerson(person.getId());

        personEntity.setFirstName(personVO.getFirstName());
        personEntity.setLastName(personVO.getLastName());
        personEntity.setAdress(personVO.getAdress());
        personEntity.setGender(personVO.getGender());

        PersonVO response = PersonMapper.parseObject(
                personRepository.save(personEntity),
                PersonVO.class);

        return response;
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
