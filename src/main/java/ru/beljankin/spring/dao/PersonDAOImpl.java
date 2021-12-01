package ru.beljankin.spring.dao;


import ru.beljankin.spring.model.Person;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    private static int PERSON_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PERSON_COUNT, "Ivan", "Petrov", "petrov.ivan@gmail.com"));
        people.add(new Person(++PERSON_COUNT, "Nokolaj", "Sidorov", "nikolay.sidorov@gmail.com"));
        people.add(new Person(++PERSON_COUNT, "Dmitrij", "Ivanov", "dmitrij.ivanov@gmail.com"));
        people.add(new Person(++PERSON_COUNT, "Ilya", "Kuznetsov", "ilya.kuznetsov@gmail.com"));
        people.add(new Person(++PERSON_COUNT, "Petr", "Romanov", "petr.romanov@gmail.com"));

    }

    public List<Person> getAll(){
        return people;
    }

    public Person select(int id){
        return people.stream().filter(people -> (people.getId() == id)).findAny().orElse(null);
    }

    public void save(Person person){
        entityManager.persist(person);
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
        updatePerson.setEmail(personVariable.getEmail());
    }
}
