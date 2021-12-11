package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.model.Role;
import ru.beljankin.spring.service.PersonServise;
import ru.beljankin.spring.service.RoleServise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Controller
public class PeopleCRUD {

    private final PersonServise personServise;
    private final RoleServise roleServise;

    @Autowired
    public PeopleCRUD(PersonServise personServise, RoleServise roleServise){
        this.personServise = personServise;
        this.roleServise = roleServise;
    }

    @GetMapping(value = "/index")
    public String index(Model model){
        model.addAttribute("people", personServise.getAll());
        return "index";
    }

    @PostMapping("/index")
    public String create(@ModelAttribute("person") Person person
            , @RequestParam("rolesNames") String[] rolesNames
    ){
        System.out.println(Arrays.toString(rolesNames));
        Set<Role> roleSet = new HashSet<>();
        if(rolesNames.length !=0){
            for (String role: rolesNames) {
                roleSet.add(roleServise.finRoleByString(role));
            }
        } else {
            roleSet.add(roleServise.finRoleByString("ROLE_USER"));
        }
//        roleSet.add(roleServise.finRoleByString("ROLE_USER"));
        person.setRoles(roleSet);
        personServise.save(person);
        return "redirect:/index";
    }


    @GetMapping("new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "new";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personServise.update(id, person);
        return "redirect:/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personServise.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personServise.select(id));
        return "/edit";
    }

}
