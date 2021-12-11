package ru.beljankin.spring.model;



import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_role",
            joinColumns = @JoinColumn( name = "person_id"),
            inverseJoinColumns = @JoinColumn( name = "role_id")
    )
    @Column(name = "roleSet")
    private Set<Role> roleSet = new HashSet<>();

    public Person() {
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Person(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Person(String name, String password, Set<Role> roleSet
    ) {
        this.name = name;
        this.password = password;
        this.roleSet = roleSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return this.roleSet;
    }

    public String getRolesToString() {
//        String str = roleSet.stream().map(e -> e.getRoles() + " ").toString();
        String str = new String();
        for (Role rol: roleSet) {
            str += rol.getRoles() + " ";
        }
        return str;
    }

    public void setRoles(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roleSet=" + roleSet +
                '}';
    }
}
