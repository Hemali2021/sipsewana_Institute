package dao.custom;

import dao.SuperDAO;
import entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO {
    public List<Student> getAllStudents();
    public boolean addStudent(Student entity);
    public boolean deleteStudent(Student student);
    public boolean update(Student student);
}
