package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.model.Role;
import ru.beljankin.spring.service.PersonServise;
import ru.beljankin.spring.service.RoleServise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonServise personServise;
    private final RoleServise roleServise;

    @Autowired
    public AdminController(PersonServise personServise, RoleServise roleServise){
        this.personServise = personServise;
        this.roleServise = roleServise;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personServise.getAll());
        return "admin/users";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person
            , @RequestParam("rolesNames") String[] rolesNames){

        System.out.println(Arrays.toString(rolesNames));
        Set<Role> roleSet = new HashSet<>();
        if(rolesNames.length !=0){
            for (String role: rolesNames) {
                roleSet.add(roleServise.finRoleByString(role));
            }
        } else {
            roleSet.add(roleServise.finRoleByString("ROLE_USER"));
        }
        person.setRoles(roleSet);
        personServise.save(person);
        return "redirect:/admin";
    }

    @GetMapping("new-person")
    public String newPerson(@ModelAttribute("person") Person person){
        return "admin/new-person";
    }

//
    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personServise.update(id, person);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personServise.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personServise.select(id));
        return "admin/edit";
    }


    }
