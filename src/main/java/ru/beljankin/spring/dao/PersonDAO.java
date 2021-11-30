package ru.beljankin.spring.dao;

import org.springframework.stereotype.Component;
import ru.beljankin.spring.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PERSON_COUNT, "Иван", "Петров"));
        people.add(new Person(++PERSON_COUNT, "Николай", "Сидоров"));
        people.add(new Person(++PERSON_COUNT, "Дмитрий", "Иванов"));
        people.add(new Person(++PERSON_COUNT, "Илья", "Кузнецов"));
        people.add(new Person(++PERSON_COUNT, "Петр", "Романов"));

    }

    public List<Person> getAll(){
        return people;
    }

    public Person select(int id){
        return people.stream().filter(people -> (people.getId() == id)).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PERSON_COUNT);
        people.add(person);
    }
}
