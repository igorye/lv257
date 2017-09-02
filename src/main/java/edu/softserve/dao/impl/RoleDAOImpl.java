package edu.softserve.dao.impl;

import edu.softserve.dao.RoleDAO;
import edu.softserve.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO  {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDAOImpl() {
    }

    @Override
    public Role findByName(String name) {
        Query query = entityManager.createQuery("select i from Role i where i.name = :name")
                .setParameter("name", name);
        Role role = (Role) query.getSingleResult();
        return role;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        List<?> list = entityManager.createQuery("SELECT p FROM Role p").getResultList();
        return (List<Role>) list;
    }

    @Override
    public void delete(Role role) {

    }





}
