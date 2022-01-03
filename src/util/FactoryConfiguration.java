package util;

import entity.Program;
import entity.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class FactoryConfiguration {
    private static final SessionFactory sessionFactory=buildSessionFactory();

    private static SessionFactory buildSessionFactory(){

        StandardServiceRegistry sReg=new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();

        Metadata mData=new MetadataSources(sReg).addAnnotatedClass(Program.class).addAnnotatedClass(Student.class).
                getMetadataBuilder().build();

        return mData.getSessionFactoryBuilder().build();

    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
