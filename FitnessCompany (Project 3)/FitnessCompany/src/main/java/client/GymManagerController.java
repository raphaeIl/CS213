package client;

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

/**
 * The Controller class responsible for the "control" of the application
 * contains FXML injections and control node functionalities
 * Implements Initializable for initializing class fields and fxml elements
 * @author Michael Liu, Genfu Liu
 */
public class GymManagerController implements Initializable {

    /**
     * The "Console" of our application where
     * all debugging info will be print here instead of Stdin
     */
    @FXML
    private static TextArea console;

    /*
     * Note: All variables below uses a prefix such as m_
     * to indicate which tab that FXML element belongs too (Membership or Fitness Class)
     * (would've preferred separating these into different controller classes :( )
     */

    /**
     * TextField to input the first name of a member when adding/removing membership
     */
    @FXML
    private TextField m_firstNameText;

    /**
     * TextField to input the last name of a member
     */
    @FXML
    private TextField m_lastNameText;

    /**
     * DatePicker to input the dob of a member (auto date validation)
     */
    @FXML
    private DatePicker m_dobText;

    /**
     * ChoiceBox/Dropdown List to input the location of a member
     * all locations are added in initialize()
     */
    @FXML
    private ChoiceBox<Location> m_locationText;

    /**
     * A groups of radio buttons to select which type of membership
     * the member wants
     * m_premiumButton not necessary
     */
    @FXML
    private RadioButton m_standardButton, m_familyButton;

    /**
     * TextField to input the first name of a member when checking-in/checking-out a member
     */
    @FXML
    private TextField fc_firstNameText;

    /**
     * TextField to input the last name of a member
     */
    @FXML
    private TextField fc_lastNameText;

    /**
     * DatePicker to input the dob of a member (auto date validation)
     */
    @FXML
    private DatePicker fc_dobText;

    /**
     * ChoiceBox/Dropdown List to input the FitnessClass the member is trying to check into,
     * all FitnessClasses are added when the user loads the schedule with loadSchedule()
     */
    @FXML
    private ChoiceBox<FitnessClass> fc_fitnessClassText;

    /**
     * A groups of radio buttons to select if the person is a member or a guest
     * guest button not necessary to inject
     */
    @FXML
    private RadioButton fc_memberButton;

    /**
     * Database used to store all members
     * declared here as a private field for convince
     */
    private MemberDatabase memberDatabase;

    /**
     * Database used to store all classes along with the schedule
     * declared here as a private field for convince
     */
    private ClassDatabase classDatabase;

    /**
     * Inherited method of the Initializable interface,
     * this is used to initialize our field as well as some FXML elements
     * @param url inherited param
     * @param resourceBundle inherited param
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberDatabase = DatabaseManager.getInstance().getMemberDatabase();
        classDatabase = DatabaseManager.getInstance().getClassDatabase();

        m_locationText.getItems().addAll(Location.values());
    }

    /* All public methods below are linked to a FXML element which then will be called on a specific action  */

    /**
     * This function will be called when the "Add" button on the Membership tab is clicked
     * adds a member to the member database
     * @param event The event representing that action for clicking the button
     */
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

    /**
     * removes a member from the member database,
     * called when the "Remove" button is clicked
     * @param event The event representing that action for clicking the button
     */
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

    /**
     * print the memberdatabase contents as is,
     * called when the "Print" MenuItem is clicked
     */
    public void print() {
        memberDatabase.print();
    }

    /**
     * print the memberdatabase contents sorted by county and then zipcode,
     * called when the "Print by County/Zipcode" MenuItem is clicked
     */
    public void printByCounty() {
        memberDatabase.printByCounty();
    }

    /**
     * print the memberdatabase contents sorted by last name and then first name
     * called when the "Print by Last/First Names" MenuItem is clicked
     */
    public void printByName() {
        memberDatabase.printByName();
    }

    /**
     * print the memberdatabase contents sorted by the expiration date
     * called when the "Print by Expiration Date" MenuItem is clicked
     */
    public void printByExpirationDate() {
        memberDatabase.printByExpirationDate();
    }

    /**
     * print the memberdatabase contents sorted by the membership fees.
     * called when the "Print by Membership Fee" MenuItem is clicked
     */
    public void printWithMembershipFees() {
        memberDatabase.printWithMembershipFees();
    }

    /**
     * Displays all classes and their schedules,
     * called when the "Show all classes" MenuItem is clicked
     */
    public void displaySchedule() {
        classDatabase.displaySchedule();
    }

    /**
     * This is used to load all the members from a file to the current member database,
     * called when the "Load member list from file" MenuItem is clicked
     */
    public void loadMembers() {
        memberDatabase.loadMembers("src/main/resources/memberList.txt");
    }

    /**
     * This is used to load all the fitness classes from a file to the current class database,
     * called when the "Load class schedule from file" MenuItem is clicked
     */
    public void loadSchedule() {
        classDatabase.loadSchedule("src/main/resources/classSchedule.txt");

        if (!classDatabase.isEmpty())
            fc_fitnessClassText.getItems().addAll(classDatabase.getClassSchedule());
    }

    /**
     * This function will be called when the "Check In" button on the Fitness Class tab is clicked
     * checks-in a member to a class
     */
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

    /**
     * This function will be called when the "Check Out" button on the Fitness Class tab is clicked
     * drops/checks-out a member from a class
     */
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

    /**
     * This is to make sure that the user loads the class schedule
     * before trying to check-in or check-out a member.
     * Opens an alert that will automatically loads the schedule if the users agrees
     */
    public void isScheduleEmpty() {
        if (classDatabase.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Empty Schedule");
            alert.setHeaderText("Your class schedule is current empty!");
            alert.setContentText("Do you wish to load the class schedule from classSchedule.txt?");

            if (alert.showAndWait().get() == ButtonType.OK)
                loadSchedule();
        }

    }

    /**
     * When the controller is created, this method
     * needs to be called to create our custom console
     * which is a static textArea field so it could be accessed anywhere
     * @param textArea
     */
    public void setConsole(TextArea textArea) {
        console = textArea;
    }

    /**
     * Since this is the main client controller class,
     * it will also be responsible for logging messages to our console
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
        console.appendText(String.format(format, args) + "\n");
    }
}