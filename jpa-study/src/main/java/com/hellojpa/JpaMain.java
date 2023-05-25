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
            Locker locker = new Locker();
            locker.setName("라커1");
            em.persist(locker);

            Member member = new Member();
            member.setUserName("멤버임");
            member.addLocker(locker);
            locker.getMember().setUserName("멤버이름바꿔봄ㅁ");
            em.persist(member);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
