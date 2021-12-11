package ru.beljankin.spring.service;


import ru.beljankin.spring.model.Person;

import java.util.List;

    public interface PersonServise {
    List<Person> getAll();
    Person select(long id);
    void save(Person person);
    void delete(long id);
    void update(long id, Person person);
    Person findByUserName(String username);
}
