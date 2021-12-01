package ru.beljankin.spring.service;

import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiseImpl implements PersonServise{

    @Autowired
    private PersonDAO personDAO;

    @Transactional
    @Override
    public Person select(int id) {
        return personDAO.select(id);
    }

    @Transactional
    @Override
    public void save(Person person) {
        personDAO.save(person);

    }

    @Transactional
    @Override
    public void delete(int id) {
        personDAO.delete(id);
    }

    @Transactional
    @Override
    public void update(int id, Person person) {
        personDAO.update(id, person);


    }
}
