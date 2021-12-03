package ru.beljankin.spring.service;

import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.dao.PersonDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiseImpl implements PersonServise{

    private PersonDAO personDAO;

    @Override
    public Person select(long id) {
        return personDAO.select(id);
    }

    @Override
    public void save(Person person) {
        personDAO.save(person);
    }

    @Override
    public void delete(long id) {
        personDAO.delete(id);
    }


    @Override
    public void update(long id, Person person) {
        personDAO.update(id, person);
    }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }

    @Override
    public Person getPerson(long id) {
        return personDAO.getPerson(id);
    }
}
