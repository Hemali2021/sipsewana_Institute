package dao.custom.impl;

import bo.custom.StudentBO;
import dao.custom.StudentDAO;
import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private final SessionFactory sessionFactory;

    public StudentDAOImpl(){
        this.sessionFactory = FactoryConfiguration.getSessionFactory();
    }

    @Override
    public List<Student> getAllStudents(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Student> list = session.createNativeQuery("SELECT * FROM Student", Student.class).list();

        session.getTransaction().commit();

        return list;
    }

    @Override
    public boolean addStudent(Student entity) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();

        Serializable save = session.save(entity);

        session.getTransaction().commit();
        return save !=null;
    }

    @Override
    public boolean deleteStudent(Student student){
        Session session=sessionFactory.openSession();
        session.beginTransaction();

        session.delete(student);

        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Student student) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();

        session.update(student);

        session.getTransaction().commit();
        return true;
    }
}
