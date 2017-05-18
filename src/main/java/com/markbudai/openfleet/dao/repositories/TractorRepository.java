package com.markbudai.openfleet.dao.repositories;

import com.markbudai.openfleet.dao.providers.TractorProvider;
import com.markbudai.openfleet.model.Tractor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Tractor> getAllTractors() {
        Query query = entityManager.createQuery("select e from Tractor e");
        return query.getResultList();
    }

    public Tractor getTractorById(long id) {
        return entityManager.find(Tractor.class, id);
    }

    public void addTractor(Tractor t){
        entityManager.persist(t);
    }

    public void updateTractor(Tractor t){
        entityManager.merge(t);
    }
}