package ru.beljankin.spring.model;



import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "rolesName")
    private String rolesName;

    @Transient
    @ManyToMany(mappedBy = "roleSet")
    @Column(name = "personSet")
    private Set<Person> personSet;

    public Role() {
    }

    public Role(String rolesName) {
        this.rolesName = rolesName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoles() {
        return rolesName;
    }

    public void setRoles(String rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    public String toString() {
        return rolesName;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> person) {
        this.personSet = person;
    }

    @Override
    public String getAuthority() {
        return rolesName;
    }


}
