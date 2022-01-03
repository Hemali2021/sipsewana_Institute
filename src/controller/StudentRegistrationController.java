package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import dto.StudentDTO;
import entity.Program;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRegistrationController {
    @FXML
    private JFXTextField txtname;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txttelNo;

    @FXML
    private JFXTextField txtProgram;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TableColumn<?, ?> colStuId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colMail;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private Button btnGoback;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    private final ObservableList<StudentDTO> studentDTOList = FXCollections.observableArrayList();
    StudentBOImpl studentBOImpl = (StudentBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    StudentDTO selectedStudent = null;

    public void initialize(){
        setTableData();

        tblStudent.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            selectedStudent=newValue ;

            txtname.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtEmail.setText(newValue.getEmail());
            txtId.setText(String.valueOf(newValue.getId()));
            txttelNo.setText(newValue.getTel());
        }));
    }

    private void setTableData(){
        colStuId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNo.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("email"));

        List<StudentDTO> allStuList = studentBOImpl.getAllPrograms();

        for(StudentDTO pr : allStuList) {
            StudentDTO tm = new StudentDTO(pr.getId(), pr.getName(), pr.getEmail(), pr.getAddress(),pr.getTel());
            studentDTOList.add(tm);
        }
        tblStudent.setItems(studentDTOList);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        boolean b = studentBOImpl.updateStudent(
                new StudentDTO(selectedStudent.getId(), txtname.getText(),txtEmail.getText(),txtAddress.getText(),txttelNo.getText())
        );

        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Student Updated Done").show();
            studentDTOList.clear();
            tblStudent.refresh();
            setTableData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Student Not Updated!").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        boolean b = studentBOImpl.deleteStudent(selectedStudent);

        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Student Deleted Done").show();
            studentDTOList.clear();
            tblStudent.refresh();
            setTableData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Student Not deleted!").show();
        }
    }


    @FXML
    void goBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnGoback.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MenuForm.fxml")));
        primaryStage.setTitle("Register Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Student student = new Student(Integer.parseInt(txtId.getText()),txtname.getText(),txtEmail.getText(),txtAddress.getText(),txttelNo.getText());

        boolean b = studentBOImpl.addProgram(student);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Student Added Done").show();
            studentDTOList.clear();
            tblStudent.refresh();
            setTableData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Student Not Added!").show();
        }
    }
}
