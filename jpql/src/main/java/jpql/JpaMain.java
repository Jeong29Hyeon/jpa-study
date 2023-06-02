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

            List<Member> members= em.createQuery("select m from Member m",
                    Member.class)
                .getResultList();

//            List<jpql.Member> resultList = query.getResultList();
//            for (jpql.Member member1 : resultList) {
//                System.out.println(member1);
//            }
//            jpql.Member singleResult = query.getSingleResult();
//            System.out.println("singleResult = " + singleResult);

            TypedQuery<String> query1 = em.createQuery("select m.userName from Member m",String.class);
            Query query2 = em.createQuery("select m.userName,m.age from Member m");
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
