package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;



@Repository
public class ResourceRequestDAOImpl extends GenericDAOImpl<ResourceRequest, Long> {

    @PersistenceContext
    protected EntityManager em;

    public ResourceRequestDAOImpl() {
        super(ResourceRequest.class);
    }

    @Override
    public Optional<ResourceRequest> findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public ResourceRequest makePersistent(ResourceRequest instance) {
        return super.makePersistent(instance);
    }

    @Override
    public List<ResourceRequest> findAll() {

        TypedQuery<ResourceRequest> query =
                em.createQuery("SELECT c FROM ResourceRequest c ORDER BY updated DESC ", ResourceRequest.class);
        List<ResourceRequest> results = query.getResultList();

        return results;
        }

        ////public void findByStatus(Status status){
        //final String findByTitle = "select distinct rp from ResourceRequest rp where rp.status like :status";

       // return queryResultList(findByTitle, "status", Status status);
    // }
    }

