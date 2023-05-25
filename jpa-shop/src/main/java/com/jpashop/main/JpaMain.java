package com.jpashop.main;

import com.jpashop.domain.Book;
import com.jpashop.domain.Item;
import com.jpashop.domain.Order;
import com.jpashop.domain.OrderItem;
import com.sun.org.apache.xpath.internal.operations.Or;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);
            em.flush();
            em.clear();
            Item findItem =em.find(Item.class, book.getId());
            Book findBook = (Book) findItem;
            System.out.println("findItem.getAuthor() = " + findBook.getAuthor());
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
