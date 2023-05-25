package com.hellojpa;

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
            movie.setDirector("aaa");
            movie.setActor("bbb");
            movie.setName("바람과함께 사라짐ㅋ");
            movie.setPrice(10000);
            em.persist(movie);
            em.flush();
            em.clear();

            Movie movie1 = em.find(Movie.class, movie.getId());
            System.out.println("item = " + movie1.getName());

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
