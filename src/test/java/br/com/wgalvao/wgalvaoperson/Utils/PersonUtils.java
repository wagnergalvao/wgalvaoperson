package br.com.wgalvao.wgalvaoperson.Utils;

import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.wgalvao.wgalvaoperson.model.Person;

public class PersonUtils {

    private static final Faker faker = Faker.instance(Locale.getDefault());

    public static Person createFakePerson() {
        Person fakePerson = new Person();
        fakePerson.setId(faker.random().nextInt(1, 100000).longValue());
        fakePerson.setFirstName(faker.name().firstName());
        fakePerson.setLastName(faker.name().lastName());
        fakePerson.setAdress(faker.address()
                .streetName()
                .concat(String.format(", %s", faker.address().streetAddressNumber()))
                .concat(String.format(" - %s", faker.address().secondaryAddress()))
                .concat(String.format(" - %s", faker.address().cityName()))
                .concat(String.format("/%s", faker.address().stateAbbr()))
                .concat(String.format(" CEP: %s", faker.address().zipCode())));
        fakePerson.setGender(faker.demographic().sex());
        return fakePerson;
    }

}
