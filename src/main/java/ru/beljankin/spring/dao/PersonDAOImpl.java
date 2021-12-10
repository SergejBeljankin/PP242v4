package ru.beljankin.spring.dao;

import org.springframework.stereotype.Component;
import ru.beljankin.spring.model.Person;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import ru.beljankin.spring.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

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
        entityManager.remove(select(id));
    }

    @Override
    public void update(long id, Person personVariable){
        Person person = select(id);
        person.setName(personVariable.getName());
        person.setPassword(personVariable.getPassword());
    }



    @Override
    public List<Person> findPersonByRole(String roleName) {
        return entityManager.createQuery("select person from Person person inner join Role role on person.id = role.id where role.roles = :roleName", Person.class)
                .setParameter("roleName", roleName).getResultList();
    }

    @Override
    public void setRoles(Set<Role> roleSet) {
//        entityManager.createQuery()
    }
}
