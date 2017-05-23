package com.markbudai.openfleet.dao.repositories;

import com.markbudai.openfleet.exception.NotFoundException;
import com.markbudai.openfleet.model.Tractor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Mark on 2017. 04. 28..
 */
@Transactional
@Repository
public class TractorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TractorRepository(){
    }

    public TractorRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Tractor> getAllTractors() {
        Query query = entityManager.createQuery("select e from Tractor e");
        return query.getResultList();
    }

    public Tractor getTractorById(long id) {
        Tractor t = entityManager.find(Tractor.class,id);
        if(t == null) throw new NotFoundException(Tractor.class);
        return t;
    }

    public void addTractor(Tractor t){
        entityManager.persist(t);
    }

    public void updateTractor(Tractor t){
        entityManager.merge(t);
    }
}