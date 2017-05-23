package com.markbudai.openfleet.dao.repositories;

import com.markbudai.openfleet.exception.NotFoundException;
import com.markbudai.openfleet.model.Transport;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Mark on 2017. 04. 29..
 */
@Transactional
@Repository
public class TransportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TransportRepository(){}

    public TransportRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Transport> getAllTransports(){
        Query query = entityManager.createQuery("select t from Transport t");
        return query.getResultList();
    }

    public void addTransport(Transport t){
        entityManager.persist(t);
    }

    public void updateTransport(Transport t){
        entityManager.merge(t);
    }

    public Transport getTransportById(long id){
        Transport t = entityManager.find(Transport.class, id);
        if(t == null) throw new NotFoundException(Transport.class);
        return t;
    }
}
