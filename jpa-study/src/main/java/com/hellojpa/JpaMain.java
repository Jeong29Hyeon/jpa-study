package com.hellojpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Movie movie = new Movie();
            movie.setPrice(10000);
            movie.setName("바람과사라짐");
            movie.setDirector("이정현");
            movie.setActor("응애");
            em.persist(movie);
            em.flush();
            em.clear();
            Movie findItem = (Movie) em.find(Item.class, 1L);
            System.out.println(findItem.getDirector());
            
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
