package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    public List<Student> getPrograms(){
        return studentDAO.getAllStudents();
    }

    public boolean addProgram(Student student){
        return studentDAO.addStudent(student);
    }

    public List<StudentDTO> getAllPrograms(){
        List<Student> programs = studentDAO.getAllStudents();

        ArrayList<StudentDTO> prList= new ArrayList<>();

        for (Student pr : programs){
            prList.add(new StudentDTO(
                    pr.getId(),
                    pr.getName(),
                    pr.getEmail(),pr.getAddress(),
                    pr.getTel()
            ));
        }
        return prList;
    }

    public boolean deleteStudent(StudentDTO st){
        Student student = new Student(st.getId(),st.getName(),st.getEmail(),st.getAddress(),st.getTel());
        return studentDAO.deleteStudent(student);
    }

    public boolean updateStudent(StudentDTO st) {
        Student student = new Student(st.getId(),st.getName(),st.getEmail(),st.getAddress(),st.getTel());
        return studentDAO.update(student);
    }
}
