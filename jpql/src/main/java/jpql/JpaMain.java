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
            for(int i = 0; i < 100; i++){
                Member member = new Member();
                member.setUserName("member"+i);
                member.setAge(i);
                em.persist(member);
            }
            em.flush();
            em.clear();

            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc",
                    Member.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

            System.out.println("resultList.size() = " + resultList.size());
            for (Member findMember : resultList) {
                System.out.println("findMember = " + findMember);
            }

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
