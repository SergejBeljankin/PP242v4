package ru.beljankin.spring.TestBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.model.Role;
import ru.beljankin.spring.service.PersonServise;
import ru.beljankin.spring.service.RoleServise;

import java.util.HashSet;
import java.util.Set;

@Component
public class TestBase {
    private final PersonServise personServise;
    private final RoleServise roleServise;

    @Autowired
    public TestBase(PersonServise personServise, RoleServise roleServise){
        this.personServise = personServise;
        this.roleServise = roleServise;

    }

    @Autowired
    public void dataInitializer(){
        Person person1 = new Person("petr", "123", "Петр", "Иванов", "p.ivanov@gmail.com");
        Person person2 = new Person("ivan", "321", "Иван", "Петров", "i.petrov@yandex.ru");
        Person person3 = new Person("semen", "100", "Семен", "Сидоров", "s.sidorov@bk.ru");

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        Role role3 = new Role("ROLE_MANAGER");

        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(role2);
        roleSet2.add(role3);

        Set<Role> roleSet3 = new HashSet<>();
        roleSet3.add(role3);

        person1.setRoles(roleSet1);
        person2.setRoles(roleSet2);
        person3.setRoles(roleSet3);

        System.out.println(person1.toString());
        System.out.println(person2.toString());
        System.out.println(person3.toString());


        roleServise.save(role1);
        roleServise.save(role2);
        roleServise.save(role3);

        personServise.save(person1);
        personServise.save(person2);
        personServise.save(person3);


    }
}
