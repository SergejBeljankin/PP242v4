package ru.beljankin.spring.dao;

import org.springframework.stereotype.Component;
import ru.beljankin.spring.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PERSON_COUNT, "Ivan", "Petrov"));
        people.add(new Person(++PERSON_COUNT, "Nokolaj", "Sidorov"));
        people.add(new Person(++PERSON_COUNT, "Dmitrij", "Ivanov"));
        people.add(new Person(++PERSON_COUNT, "Ilya", "Kuznetsov"));
        people.add(new Person(++PERSON_COUNT, "Petr", "Romanov"));

    }

    public List<Person> getAll(){
        return people;
    }

    public Person select(int id){
        return people.stream().filter(people -> (people.getId() == id)).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PERSON_COUNT);
        people.add(person);
    }
    public void delete(int id){
        people.removeIf(person -> person.getId() == id);

    }
    public void update(int id, Person personVariable){
        Person updatePerson = select(id);
        updatePerson.setName(personVariable.getName());
        updatePerson.setSurname(personVariable.getSurname());
    }
}
