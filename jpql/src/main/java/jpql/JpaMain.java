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
            member.setUserName("member");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);
            em.flush();
            em.clear();
            String query = "select m from Member m left join Team t on m.userName = t.name";
            List<Member> resultList = em.createQuery(query,
                    Member.class)
                .getResultList();

            Member findMember = resultList.get(0);
            System.out.println(
                "findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
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
