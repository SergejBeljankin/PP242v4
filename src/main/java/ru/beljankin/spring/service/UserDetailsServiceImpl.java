package ru.beljankin.spring.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beljankin.spring.dao.PersonDAO;
import ru.beljankin.spring.model.Person;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonDAO personDAO;

    public UserDetailsServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personDAO.findByUserName(s);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("User %s not found!", s));
        }
        return new User(person.getUsername(), person.getPassword(), person.getAuthorities());
    }

}
