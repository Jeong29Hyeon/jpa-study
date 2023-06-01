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

            Member singleResult = em.createQuery("select m from Member m where m.userName = ?1",
                    Member.class)
                .setParameter("1", "member1")
                .getSingleResult();

//            List<Member> resultList = query.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println(member1);
//            }
//            Member singleResult = query.getSingleResult();
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
