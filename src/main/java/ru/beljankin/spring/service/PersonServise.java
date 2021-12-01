package ru.beljankin.spring.service;


import ru.beljankin.spring.model.Person;

public interface PersonServise {
    Person select(int id);
    void save(Person person);
    void delete(int id);
    void update(int id, Person person);
}
