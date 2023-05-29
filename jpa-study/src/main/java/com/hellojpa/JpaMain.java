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
            Address address = new Address("city","street","zipcode");
            Member member = new Member();
            member.setUserName("hello1");
            member.setHomeAddress(address);
            em.persist(member);
            Address address2 = new Address("city2","street","zipcode");
            member.setHomeAddress(address2);


            Address copyAddress = new Address(address.getCity(), address.getStreet(),
                address.getZipcode());
            Member member2 = new Member();
            member2.setUserName("hello1");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);


            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }



}
