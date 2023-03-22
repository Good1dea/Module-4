package com.sydoruk.repository;

import com.sydoruk.config.HibernateUtil;
import com.sydoruk.model.Detail;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class Repository {

    private static Repository instance;

    private Repository() {
    }

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void save(final Detail detail) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(detail);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<Detail> findById(final String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Detail.class, id));
    }

    public List<Detail> getAll() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery("from " + Detail.class.getSimpleName(), Detail.class)
                .getResultList();
    }

    public void delete(final String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from " + Detail.class.getSimpleName() + " where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List getAllId(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        String hql = "SELECT id from Detail";
        return entityManager.createQuery(hql).getResultList();
    }

    public String numberOfDetail(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        String hql = "SELECT COUNT(*) from Detail";
        return entityManager.createQuery(hql).getSingleResult().toString();
    }

    public String getSumExtractedFuel(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        String hql = "SELECT SUM(extractedFuel) from Detail";
        return entityManager.createQuery(hql).getSingleResult().toString();
    }

    public String getSumUsedFuel(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        String hql = "SELECT SUM(usedFuel) from Detail";
        return entityManager.createQuery(hql).getSingleResult().toString();
    }

    public String getSumBrokenChips(){
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        String hql = "SELECT SUM(brokenChips) from Detail";
        return entityManager.createQuery(hql).getSingleResult().toString();
    }
}