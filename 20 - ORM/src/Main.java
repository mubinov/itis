import test.ImdbEntity;

import java.math.BigDecimal;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.metamodel.*;
import org.hibernate.service.*;
import org.hibernate.service.*;

public class Main {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        addFilm("СНЕГУРОЧКА", 1990, 9.9);
        listFilm();
        updateFilm("СНЕГУРОЧКА", 9.8);
        deleteFilm("СНЕГУРОЧКА");
    }

    public static String addFilm(String name, int year, double rating){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        String filmID = null;
        try{
            tx = session.beginTransaction();
            ImdbEntity imdbObject = new ImdbEntity();
            imdbObject.setName(name);
            imdbObject.setYear(year);
            imdbObject.setRating(new BigDecimal(rating));

            filmID = (String) session.save(imdbObject);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return filmID;
    }

    public static void listFilm( ){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("from ImdbEntity where rating>8.5").list();
            for (Iterator iterator =
                         employees.iterator(); iterator.hasNext();){
                ImdbEntity film = (ImdbEntity) iterator.next();
                System.out.println(film.getName() + " (" +  film.getYear() + ") - " + film.getRating());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    public static void updateFilm(String filmID, double rating ){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ImdbEntity film =
                    (ImdbEntity)session.get(ImdbEntity.class, filmID);
            film.setRating(new BigDecimal(rating));
            session.update(film);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    public static void deleteFilm(String filmID){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ImdbEntity film =
                    (ImdbEntity)session.get(ImdbEntity.class, filmID);
            session.delete(film);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
