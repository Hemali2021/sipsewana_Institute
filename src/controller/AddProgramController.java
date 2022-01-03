package controller;

import bo.BOFactory;
import bo.custom.impl.ProgramBOImpl;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramDTO;
import entity.Program;
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
import java.util.List;
import java.util.Objects;

public class AddProgramController {
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtFee;

    @FXML
    private TableView<ProgramDTO> tblProgram;

    @FXML
    private TableColumn<ProgramDTO, ?> colId;

    @FXML
    private TableColumn<ProgramDTO, ?> colName;

    @FXML
    private TableColumn<ProgramDTO, ?> colDuration;

    @FXML
    private TableColumn<ProgramDTO, ?> colFee;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private final ObservableList<ProgramDTO> programList = FXCollections.observableArrayList();
    ProgramBOImpl programBOImpl= (ProgramBOImpl) BOFactory.getInstance().getBO(BOFactory.BOType.PROGRAM);

    public void initialize(){
        setTableData();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Program program = new Program(txtId.getText(),txtName.getText(),txtDuration.getText(),Integer.parseInt(txtFee.getText()));

        boolean b = programBOImpl.addProgram(program);
        if(b){
            new Alert(Alert.AlertType.CONFIRMATION,"Program Added Done").show();
            programList.clear();
            tblProgram.refresh();
            setTableData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Program Not Added!").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MenuForm.fxml")));
        primaryStage.setTitle("Register Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void setTableData(){
        colId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));


        List<ProgramDTO> all = programBOImpl.getAllPrograms();

        for(ProgramDTO pr : all) {
            ProgramDTO tm = new ProgramDTO(
                    pr.getProgramId(),
                    pr.getProgramName(),
                    pr.getDuration(),
                    pr.getFee()
            );
            programList.add(tm);
        }
        tblProgram.setItems(programList);
    }
}
