package ru.beljankin.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.beljankin.spring.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role finRoleByString(String roleName) {
        return entityManager.createQuery("select role from Role role where role.rolesName =:roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

}
