package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.beljankin.spring.dao.PersonDAOImpl;
import ru.beljankin.spring.model.Person;

@Controller
public class PeopleCRUD {

    private final PersonDAOImpl personDAO;

    @Autowired
    public PeopleCRUD(PersonDAOImpl personDAO){
        this.personDAO = personDAO;
    }

    @GetMapping(value = "/index")
    public String index(Model model){
        model.addAttribute("people", personDAO.getAll());
        return "index";
    }

    @PostMapping("/index")
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/index";
    }


    @GetMapping("new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "new";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.select(id));
        return "/edit";
    }

}
