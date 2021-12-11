package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.service.PersonServise;
import ru.beljankin.spring.service.RoleServise;

@Controller
@RequestMapping("/user")
public class UserController {
    private final PersonServise personServise;

    @Autowired
    public UserController(PersonServise personServise, RoleServise roleServise){
        this.personServise = personServise;
    }

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable("id") int id){
        Person person = new Person();
        person = personServise.select(id);
        model.addAttribute("user",personServise.select(id))
                .addAttribute("username", personServise.findByUserName(person.getUsername()));
        return "user/index";
    }
}
