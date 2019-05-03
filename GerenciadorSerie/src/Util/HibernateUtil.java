/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author ComputaDor
 */
public class HibernateUtil {
    
    private static Session session;
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession(){
        if(session == null) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void beginTransaction() {
        getSession().beginTransaction();
    }
    public static void commit() {
        getSession().getTransaction().commit();
    }
    public static void rollback() {
        getSession().getTransaction().rollback();
    }
    
    public static void close(){
        if(session != null) {
        session.close();
        }
    }
}
