package ru.beljankin.spring.dao;

import ru.beljankin.spring.model.Person;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> getAll(){
        return entityManager.createQuery("from Person", Person.class).getResultList();
    }


    @Override
    public Person select(long id){
        return entityManager.find(Person.class, id);
    }

    @Override
    public void save(Person person){
        entityManager.persist(person);
    }

    @Override
    public void delete(long id){
        entityManager.remove(getPerson(id));
    }

    @Override
    public void update(long id, Person personVariable){
        Person person = getPerson(id);
        person.setName(personVariable.getName());
        person.setSurname(personVariable.getSurname());
        person.setEmail(personVariable.getEmail());
    }

    @Override
    public Person getPerson(long id) {
        return entityManager.find(Person.class, id);
    }
}
