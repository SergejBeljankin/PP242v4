package ru.beljankin.spring.TestBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.model.Role;
import ru.beljankin.spring.service.PersonServise;
import ru.beljankin.spring.service.RoleServise;

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
        Person person1 = new Person("Иван", "123");
        Person person2 = new Person("Петр", "321");
        Person person3 = new Person("Семен", "100");

        personServise.save(person1);
        personServise.save(person2);
        personServise.save(person3);

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        Role role3 = new Role("ROLE_MANAGER");

        roleServise.save(role1);
        roleServise.save(role2);
        roleServise.save(role3);



    }
}
