package br.com.wgalvao.wgalvaoperson.unittests.mapper.mocks;

import java.util.List;

import br.com.wgalvao.wgalvaoperson.data.vo.v1.PersonVO;
import br.com.wgalvao.wgalvaoperson.model.Person;

public class MockPerson {

    Person person = new Person();

    public List<Person> mockEntityList(List<Person> persons) {

        return persons;
    }

    public List<PersonVO> mockVOList(List<PersonVO> personVOs) {

        return personVOs;
    }

    public Person mockEntity(Person person) {

        return person;
    }

    public PersonVO mockVO(Person person) {
        PersonVO personVO = new PersonVO();
        personVO.setId(person.getId());
        personVO.setFirstName(person.getFirstName());
        personVO.setLastName(person.getLastName());
        personVO.setAdress(person.getAdress());
        personVO.setGender(person.getGender());
        return personVO;
    }

}
