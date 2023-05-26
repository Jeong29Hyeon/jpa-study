package com.hellojpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import org.hibernate.Hibernate;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member1 = new Member();
            member1.setUserName("hello1");
            em.persist(member1);


            em.flush();
            em.clear();

            Member m1 = em.getReference(Member.class, member1.getId());
            System.out.println("PersistenceUnitUtil.isLoaded() "+ emf.getPersistenceUnitUtil().isLoaded(m1));
            Hibernate.initialize(m1);
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(m1));

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }



}
