package javafx;

import core.ClassDatabase;
import core.FitnessClass;
import core.MemberDatabase;
import core.entity.Member;
import datatypes.Date;
import datatypes.Location;
import datatypes.MemberType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import managers.DatabaseManager;
import utils.MemberValidator;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class GymManagerController implements Initializable {

    @FXML
    private static TextArea console;

    @FXML
    private TextField m_firstNameText;

    @FXML
    private TextField m_lastNameText;

    @FXML
    private DatePicker m_dobText;

    @FXML
    private ChoiceBox<Location> m_locationText;

    @FXML
    private RadioButton m_standardButton, m_familyButton;

    @FXML
    private TextField fc_firstNameText;

    @FXML
    private TextField fc_lastNameText;

    @FXML
    private DatePicker fc_dobText;

    @FXML
    private ChoiceBox<FitnessClass> fc_fitnessClassText;

    @FXML
    private RadioButton fc_memberButton;

    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberDatabase = DatabaseManager.getInstance().getMemberDatabase();
        classDatabase = DatabaseManager.getInstance().getClassDatabase();

        m_locationText.getItems().addAll(Location.values());
    }

    public void add(ActionEvent event) {
        MemberType memberType = m_standardButton.isSelected() ? MemberType.Standard : m_familyButton.isSelected() ? MemberType.Family : MemberType.Premium;

        if (m_firstNameText.getText().isEmpty()) {
            log("Please enter your first name");
            return;
        }

        if (m_lastNameText.getText().isEmpty()) {
            log("Please enter your last name");
            return;
        }

        if (m_dobText.getValue() == null) {
            log("Please enter your date of birth");
            return;
        }

        if (m_locationText.getSelectionModel().getSelectedItem() == null) {
            log("Please enter your location");
            return;
        }

        String formattedDate = m_dobText.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Location location = m_locationText.getSelectionModel().getSelectedItem();

        Member newMember = MemberValidator.validateAndCreateMember(m_firstNameText.getText(), m_lastNameText.getText(), formattedDate, null, location, memberType);

        if (newMember == null || !MemberValidator.validateMemberDatabaseInsertion(memberDatabase, newMember))
            return;

        if (memberDatabase.add(newMember))
            GymManagerController.logf("%s %s added.\n", newMember.getFname(), newMember.getLname());
    }

    public void remove(ActionEvent event) {
        if (m_firstNameText.getText().isEmpty()) {
            log("Please enter your first name");
            return;
        }

        if (m_lastNameText.getText().isEmpty()) {
            log("Please enter your last name");
            return;
        }

        if (m_dobText.getValue() == null) {
            log("Please enter your date of birth");
            return;
        }

        if (m_locationText.getSelectionModel().getSelectedItem() == null) {
            log("Please enter your location");
            return;
        }

        String formattedDate = m_dobText.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Member target = memberDatabase.get(new Member(m_firstNameText.getText(), m_lastNameText.getText(), new Date(formattedDate), null, null));

        if (memberDatabase.remove(target))
            GymManagerController.logf("%s %s removed.\n", target.getFname(), target.getLname());
        else
            GymManagerController.logf("%s %s is not in the database.\n", m_firstNameText.getText(), m_lastNameText.getText());
    }

    public void print() {
        memberDatabase.print();
    }

    public void printByCounty() {
        memberDatabase.printByCounty();
    }

    public void printByName() {
        memberDatabase.printByName();
    }

    public void printByExpirationDate() {
        memberDatabase.printByExpirationDate();
    }

    public void printWithMembershipFees() {
        memberDatabase.printWithMembershipFees();
    }

    public void displaySchedule() {
        classDatabase.displaySchedule();
    }

    public void loadMembers() {
        memberDatabase.loadMembers("src/main/resources/memberList.txt");
    }

    public void loadSchedule() {
        classDatabase.loadSchedule("src/main/resources/classSchedule.txt");

        fc_fitnessClassText.getItems().addAll(classDatabase.getClassSchedule());
    }

    public void checkIn() {
        Member target;
        FitnessClass fitnessClass = fc_fitnessClassText.getSelectionModel().getSelectedItem();
        String fname = fc_firstNameText.getText();
        String lname = fc_lastNameText.getText();
        LocalDate dob = fc_dobText.getValue();

        if (fname.isEmpty()) {
            log("Please enter your first name");
            return;
        }

        if (lname.isEmpty()) {
            log("Please enter your last name");
            return;
        }

        if (dob == null) {
            log("Please enter your date of birth");
            return;
        }

        if (classDatabase.isEmpty()) {
            log("No fitness class exists! Please load them first!");
            return;
        }

        if (fitnessClass == null) {
            log("Please enter your class");
            return;
        }

        boolean isMember = fc_memberButton.isSelected();
        String formattedDate = dob.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        target = memberDatabase.get(new Member(fname, lname, new Date(formattedDate), null, null));

        if (isMember) {
            if (!MemberValidator.validateMemberCheckIn(classDatabase, memberDatabase, fitnessClass, fname, lname, formattedDate))
                return;

            if (classDatabase.checkIn(fitnessClass, target))
                GymManagerController.logf("%s %s checked in ", target.getFname(), target.getLname(), fitnessClass);
        } else {
            if (!MemberValidator.validateGuestCheckIn(classDatabase, memberDatabase, fitnessClass, fname, lname, formattedDate))
                return;

            if (classDatabase.checkInGuest(fitnessClass, target))
                GymManagerController.logf("%s %s (guest) checked in ", target.getFname(), target.getLname(), fitnessClass);
        }

        fitnessClass.displaySchedule();
    }

    public void checkOut() {
        Member target;
        FitnessClass fitnessClass = fc_fitnessClassText.getSelectionModel().getSelectedItem();

        String fname = fc_firstNameText.getText();
        String lname = fc_lastNameText.getText();
        LocalDate dob = fc_dobText.getValue();

        if (fname.isEmpty()) {
            log("Please enter your first name");
            return;
        }

        if (lname.isEmpty()) {
            log("Please enter your last name");
            return;
        }

        if (dob == null) {
            log("Please enter your date of birth");
            return;
        }

        if (classDatabase.isEmpty()) {
            log("No fitness class exists! Please load them first!");
            return;
        }

        if (fitnessClass == null) {
            log("Please enter your class");
            return;
        }

        boolean isMember = fc_memberButton.isSelected();
        String formattedDate = dob.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        target = memberDatabase.get(new Member(fname, lname, new Date(formattedDate), null, null));

        if (isMember) {
            if (!MemberValidator.validateMemberDrop(classDatabase, memberDatabase, fitnessClass, fname, lname, formattedDate))
                return;

            if (classDatabase.drop(fitnessClass, target))
                GymManagerController.logf("%s %s done with the class.\n", target.getFname(), target.getLname());
        } else {
            if (!MemberValidator.validateGuestDrop(classDatabase, memberDatabase, fitnessClass, fname, lname, formattedDate))
                return;

            if (classDatabase.dropGuest(fitnessClass, target))
                GymManagerController.logf("%s %s Guest done with the class.\n", target.getFname(), target.getLname());
        }

        fitnessClass.displaySchedule();
    }

    public void setConsole(TextArea textArea) {
        console = textArea;
    }

    /**
     * Since this is the main client interface class, it will also be responsible for logging messages,
     * such as helpful info as well as errors/warnings,
     * these can also be toggled with a boolean variable like boolean debug = true;
     * @param message the message to be logged
     */
    public static void log(String message) {
        console.appendText(message + "\n");
    }

    /**
     * Logs a message on the console with formatting
     * @param format a format string of the message to be logged
     * @param args arguments for the format string
     */
    public static void logf(String format, Object ... args) {
        console.appendText(String.format(format, args));
    }
}