package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.beljankin.spring.dao.PersonDAOImpl;
import ru.beljankin.spring.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAOImpl personDAO;
    @Autowired
    public PeopleController(PersonDAOImpl personDAO){
        this.personDAO = personDAO;
    }



    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.getAll());
        // получить всех людей из DAO и передать в представление

        return "people/index"; // адрес ШАБЛОНА страницы в Java
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.select(id));
        return "people/show";
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

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.select(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
