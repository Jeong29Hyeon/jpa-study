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
            Team team = new Team();
            team.setName("teanA");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("teanA2");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUserName("hello1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUserName("hello2");
            member2.setTeam(team2);
            em.persist(member2);



            em.flush();
            em.clear();

//            Member m1 = em.find(Member.class, member1.getId());
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                .getResultList();
            System.out.println("===========");
            System.out.println("===========");
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
