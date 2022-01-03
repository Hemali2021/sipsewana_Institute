package bo;

import bo.custom.ProgramBO;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return (null == boFactory) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        PROGRAM,STUDENT
    }

    public SuperBO getBO(BOType boType){
        switch (boType){
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
