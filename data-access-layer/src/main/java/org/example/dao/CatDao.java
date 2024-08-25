package org.example.dao;

import org.example.models.cat.Cat;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatDao {

    public Cat findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Cat cat = session.get(Cat.class, id);
        session.close();
        return cat;
    }

    public void save(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cat);
        transaction.commit();
        session.close();
    }

    public void update(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cat);
        transaction.commit();
        session.close();
    }

    public void delete(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cat);
        transaction.commit();
        session.close();
    }

    public List<Cat> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Cat> cats = (List<Cat>) session.createQuery("From Cat").list();
        session.close();
        return cats;
    }

}
