package com.hellojpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
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
            Member member = new Member();
            member.setUserName("member1");
            member.setHomeAddress(new Address("homeCity","street1","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");
            AddressEntity addressEntity1 = new AddressEntity(
                new Address("old1", "street1", "10000"));
            member.getAddressHistory().add(addressEntity1);
            member.getAddressHistory().add(new AddressEntity(new Address("old2","street1","10000")));
            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("====================================");
            Member findMember = em.find(Member.class, member.getId());
            List<AddressEntity> addressHistory = findMember.getAddressHistory();
            System.out.println(addressHistory.getClass());
//            System.out.println("===============AddressHistory 값 사용=================");
//            for (AddressEntity address : addressHistory) {
//                System.out.println("address = " + address);
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            System.out.println("===============favorite Foods 값 사용=================");
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }
            // homeCity -> newCity
            // 값타입은 불변객체로 해야하기때문에 findMember.getHomeAddress().setCity("newCity") 절대 금지
            // 물론 set 은 막아놨지만 저렇게 접근하면 안된다.(사이드이펙트 문제) 통째로 갈아껴야함.
//            Address oldAddres = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCIty", oldAddres.getStreet(),
//                oldAddres.getZipcode()));
//
//            //치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
            System.out.println("====================================");
            findMember.getAddressHistory().remove(addressEntity1);
            System.out.println("====================================");
            findMember.getAddressHistory().add(new AddressEntity(new Address("newCity1","street1","10000")));

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
