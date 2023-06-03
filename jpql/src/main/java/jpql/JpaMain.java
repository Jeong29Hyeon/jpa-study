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
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);
            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team);
            em.persist(member);
            em.flush();
            em.clear();
            String query = "select m.userName, 'HELLO', m.type, TRUE from Member m where m.type =:type";
            List<Object[]> resultList = em.createQuery(query)
                .setParameter("type",MemberType.ADMIN)
                .getResultList();

            for (Object[] objects : resultList) {
                System.out.println(objects[0]);
                System.out.println(objects[1]);
                System.out.println(objects[2]);
                System.out.println(objects[3]);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
