package com.hellojpa;

import java.util.List;
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
            System.out.println("멤버 찾음------------");
            Member findMember = em.find(Member.class, 2L);
            System.out.println("-------------------");
            List<Member> members = findMember.getTeam().getMembers();
            System.out.println("멤버의 팀에서 멤버들 찾음-------");
            for (Member member : members) {
                System.out.println(member.getUserName());
            }
            System.out.println("-------------------");
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
