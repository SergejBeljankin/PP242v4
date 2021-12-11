package ru.beljankin.spring.dao;

import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.model.Role;

import java.util.List;
import java.util.Set;

public interface PersonDAO {
    List<Person> getAll();
    Person select(long id);
    void save(Person person);
    void delete(long id);
    void update(long id, Person person);
//    public void setRoles(Set<Role> roleSet);
    List<Person> findPersonByRole(String roleName);
    Person findByUserName(String username);
}
