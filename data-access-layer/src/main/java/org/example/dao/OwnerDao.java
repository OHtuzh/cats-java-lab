package org.example.dao;

import org.example.models.owner.Owner;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDao {

    public Owner findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Owner owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }

    public void save(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(owner);
        tx.commit();
        session.close();
    }

    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(owner);
        tx.commit();
        session.close();
    }

    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(owner);
        tx.commit();
        session.close();
    }

    public List<Owner> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Owner> owners = session.createQuery("from Owner").list();
        session.close();
        return owners;
    }
}
