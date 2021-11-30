package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beljankin.spring.dao.PersonDAO;
import ru.beljankin.spring.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    public PersonDAO personDAO;


    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.getAll());
        // получить всех людей из DAO и передать в представление

        return "people/index"; // адрес ШАБЛОНА страницы в Java
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
}
