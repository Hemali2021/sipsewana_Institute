package dao.custom.impl;

import dao.SuperDAO;
import dao.custom.ProgramDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    private final SessionFactory sessionFactory;

    public ProgramDAOImpl(){
        this.sessionFactory = FactoryConfiguration.getSessionFactory();
    }

    public List<Program> getAllPrograms(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Program> list = session.createNativeQuery("SELECT * FROM Program", Program.class).list();

        session.getTransaction().commit();

        return list;
    }

    public boolean addProgram(Program entity) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();

        Serializable save = session.save(entity);

        session.getTransaction().commit();
        return save !=null;
    }

}
