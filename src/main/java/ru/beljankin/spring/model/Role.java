package ru.beljankin.spring.model;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "roles")
    private String roles;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
//    private Person person;

//    @ManyToMany(mappedBy = "roles")
//    private Set<Person> person;
    @Transient
    @ManyToMany(mappedBy = "role")
    private Set<Person> users;

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
