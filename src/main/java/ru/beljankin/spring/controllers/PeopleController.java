package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.beljankin.spring.dao.PersonDAO;
import ru.beljankin.spring.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }



    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.getAll());
        // получить всех людей из DAO и передать в представление

        return "people/index"; // адрес ШАБЛОНА страницы в Java
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
}
