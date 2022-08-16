package br.com.wgalvao.wgalvaoperson.unittests.mapper;

import static br.com.wgalvao.wgalvaoperson.Utils.PersonUtils.createFakePerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.wgalvao.wgalvaoperson.data.vo.v1.PersonVO;
import br.com.wgalvao.wgalvaoperson.mapper.PersonMapper;
import br.com.wgalvao.wgalvaoperson.model.Person;
import br.com.wgalvao.wgalvaoperson.unittests.mapper.mocks.MockPerson;

public class PersonMapperTest {

    MockPerson mockPerson;
    Person person;
    List<Person> persons = new ArrayList<Person>();
    List<PersonVO> personVOs = new ArrayList<PersonVO>();

    @BeforeEach
    public void setUP() {
        mockPerson = new MockPerson();
        person = createFakePerson();
        for (int i = 0; i < 14; i++) {
            persons.add(createFakePerson());
            personVOs.add(PersonMapper.parseObject(
                    mockPerson.mockEntity(createFakePerson()),
                    PersonVO.class));
        }
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO response = PersonMapper.parseObject(mockPerson.mockEntity(person), PersonVO.class);
        assertEquals(person.getId(), response.getId());
        assertEquals(person.getFirstName(), response.getFirstName());
        assertEquals(person.getLastName(), response.getLastName());
        assertEquals(person.getAdress(), response.getAdress());
        assertEquals(person.getGender(), response.getGender());

    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> responses = PersonMapper.parseListObjects(persons, PersonVO.class);

        for (int i = 0; i < responses.size(); i++) {

            PersonVO itemResponse = responses.get(i);
            Person itemPerson = persons.get(i);
            assertEquals(itemPerson.getId(), itemResponse.getId());
            assertEquals(itemPerson.getFirstName(), itemResponse.getFirstName());
            assertEquals(itemPerson.getLastName(), itemResponse.getLastName());
            assertEquals(itemPerson.getAdress(), itemResponse.getAdress());
            assertEquals(itemPerson.getGender(), itemResponse.getGender());
        }

    }

    @Test
    public void parseVOToEntityTest() {
        Person response = PersonMapper.parseObject(mockPerson.mockVO(person), Person.class);
        assertEquals(person.getId(), response.getId());
        assertEquals(person.getFirstName(), response.getFirstName());
        assertEquals(person.getLastName(), response.getLastName());
        assertEquals(person.getAdress(), response.getAdress());
        assertEquals(person.getGender(), response.getGender());

    }

    @Test
    public void parseVOListToEntityListTest() {

        List<Person> responses = PersonMapper.parseListObjects(mockPerson.mockVOList(personVOs), Person.class);

        for (int i = 0; i < responses.size(); i++) {

            Person itemResponse = responses.get(i);
            PersonVO itemPerson = personVOs.get(i);
            assertEquals(itemPerson.getId(), itemResponse.getId());
            assertEquals(itemPerson.getFirstName(), itemResponse.getFirstName());
            assertEquals(itemPerson.getLastName(), itemResponse.getLastName());
            assertEquals(itemPerson.getAdress(), itemResponse.getAdress());
            assertEquals(itemPerson.getGender(), itemResponse.getGender());
        }
    }
}
