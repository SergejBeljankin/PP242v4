package ru.beljankin.spring.dao;

import ru.beljankin.spring.model.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> getAll();
    Person getPerson(long id);
    Person select(long id);
    void save(Person person);
    void delete(long id);
    void update(long id, Person person);
}
