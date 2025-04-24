package src;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class MainController {

    @FXML
    private TextField nameField, gradeField;

    @FXML
    private ListView<String> studentListView, attendanceListView;

    private StudentManager manager = new StudentManager();

    @FXML
    private void handleAdd() {
        String name = nameField.getText();
        try {
            int grade = Integer.parseInt(gradeField.getText());
            manager.addStudent(name, grade);
            updateStudentList();
        } catch (NumberFormatException e) {
            showAlert("Invalid grade input.");
        }
    }

    @FXML
    private void handleRemove() {
        String name = nameField.getText();
        manager.removeStudent(name);
        updateStudentList();
    }

    @FXML
    private void handleSort() {
        manager.sortByGrade();
        updateStudentList();    }

    @FXML
    private void handleMarkAttendance() {
        String name = nameField.getText();
        manager.markAttendance(name);
        updateAttendanceList();
    }

    @FXML
    private void handleClearAttendance() {
        manager.clearAttendance();
        updateAttendanceList();
    }

    private void updateStudentList() {
        studentListView.setItems(FXCollections.observableArrayList(manager.getStudentList()));
    }

    private void updateAttendanceList() {
        attendanceListView.setItems(FXCollections.observableArrayList(manager.getAttendanceList()));
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

