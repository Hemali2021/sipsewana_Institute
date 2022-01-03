package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.impl.ProgramDAOImpl;
import dto.ProgramDTO;
import entity.Program;


import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    ProgramDAOImpl programDAO = (ProgramDAOImpl) DAOFactory.getInstance().getDAO(DAOType.PROGRAM);

    public List<Program> getPrograms(){
        return programDAO.getAllPrograms();
    }

    public boolean addProgram(Program program){
        return programDAO.addProgram(program);
    }

    public List<ProgramDTO> getAllPrograms(){
        List<Program> programs = programDAO.getAllPrograms();

        ArrayList<ProgramDTO> prList= new ArrayList<>();

        for (Program pr : programs){
            prList.add(new ProgramDTO(
                    pr.getProgramId(),
                    pr.getProgramName(),
                    pr.getDuration(),
                    pr.getFee()
            ));
        }
        return prList;
    }

}