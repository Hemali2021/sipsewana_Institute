package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import entity.Student;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean addProgram(Student student);
    public List<StudentDTO> getAllPrograms();
    public boolean deleteStudent(StudentDTO st);
    public boolean updateStudent(StudentDTO st);
}
