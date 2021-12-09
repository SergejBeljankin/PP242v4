package ru.beljankin.spring.model;



import javax.persistence.*;
import java.util.Set;

@Entity(name = "role")
public class Role {

    @Id
    @Column
    private Long id;

    @Column
    private String roles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Person person;

//    @ManyToMany(mappedBy = "roles")
//    private Set<Person> person;

    public Role() {
    }

    public Role(String roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


//    public Set<Person> getPerson() {
//        return person;
//    }

//    public void setUsers(Set<Person> person) {
//        this.person = person;
//    }


}
