package jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            em.persist(member);
            em.flush();
            em.clear();

            List<MemberDTO> resultList = em.createQuery(
                    "select distinct new jpql.MemberDTO(m.userName,m.age) from Member m",
                    MemberDTO.class)
                .getResultList();

            MemberDTO memberDTO = resultList.get(0);
            System.out.println("userName " + memberDTO.getUserName());
            System.out.println("age " + memberDTO.getAge());

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
