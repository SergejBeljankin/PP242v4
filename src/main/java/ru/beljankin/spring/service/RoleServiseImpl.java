package ru.beljankin.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beljankin.spring.dao.RoleDAO;
import ru.beljankin.spring.model.Role;

@Service
@Transactional
public class RoleServiseImpl implements RoleServise{

    private RoleDAO roleDAO;
    public RoleServiseImpl(RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }

    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Override
    public Role finRoleByString(String roleName) {
        return roleDAO.finRoleByString(roleName);
    }
}
