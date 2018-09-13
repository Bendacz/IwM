package networking.rmi.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import networking.rmi.api.AdditionalBehaviour;
import networking.rmi.api.Attendance;
import networking.rmi.api.Books;
import networking.rmi.api.Events;
import networking.rmi.api.Marks;
import networking.rmi.api.Person;
import networking.rmi.api.PersonalData;
import networking.rmi.api.Program;
import networking.rmi.api.Service;
import networking.rmi.api.Subjects;
import networking.rmi.api.Teachers;
import networking.rmi.api.Trips;
import networking.rmi.api.WeekTimetable;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;

public class AdminMenuController implements Initializable {
	@FXML private TextField txtId;
	@FXML private TextField txtFirstName;
	@FXML private TextArea txtLastName;
	@FXML private DatePicker dtpBirthDate;
	@FXML private TableView<Person> tableView;
	@FXML private TableColumn<Person, Long> colId;
	@FXML private TableColumn<Person, String> colFirstName;
	@FXML private TableColumn<Person, String> colLastName;
	@FXML private TableColumn<Person, LocalDate> colBirthDate;

	//*************************************************************FIELDS*************************************************************************//

    //============================================================================================================================================//
	//=============================================================PERSONAL DATA==================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtPersonalDataID;
	@FXML private TextField txtPersonalDataRegisterationNumber;
	@FXML private TextField txtPersonalDataStudentName;
	@FXML private DatePicker dtpPersonalDataBirthDate;
	@FXML private TextArea txtPersonalDataBirthLocation;
	@FXML private TextField txtPersonalDataPESEL;
	@FXML private TextField txtPersonalDataFatherName;
	@FXML private TextField txtPersonalDataMotherName;
	@FXML private TextField txtPersonalDataParentsAddress;
	@FXML private TextArea txtPersonalDataParentsMobileNumber;

	@FXML private TableView<PersonalData> personalDataTableView;
	@FXML private TableColumn<PersonalData, Long> colPersonalDataID;
	@FXML private TableColumn<PersonalData, Long> colPersonalDataRegisterationNumber;
	@FXML private TableColumn<PersonalData, String> colPersonalDataStudentName;
	@FXML private TableColumn<PersonalData, LocalDate> colPersonalDataBirthDate;
	@FXML private TableColumn<PersonalData, String> colPersonalDataBirthLocation;
	@FXML private TableColumn<PersonalData, Long> colPersonalDataPESEL;
	@FXML private TableColumn<PersonalData, String> colPersonalDataFatherName;
	@FXML private TableColumn<PersonalData, String> colPersonalDataMotherName;
	@FXML private TableColumn<PersonalData, String> colPersonalDataPatentsAddress;
	@FXML private TableColumn<PersonalData, String> colPersonalDataParentsMobileNumber;

	//============================================================================================================================================//
	//=============================================================WEEK TIMETABLE=================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtWeekTimetableID;
	@FXML private TextField txtWeekTimetableFrom;
	@FXML private TextField txtWeekTimetableTo;
	@FXML private TextField txtWeekTimetableMonday;
	@FXML private TextField txtWeekTimetableTeusday;
	@FXML private TextField txtWeekTimetableWednesday;
	@FXML private TextField txtWeekTimetableThursday;
	@FXML private TextField txtWeekTimetableFriday;

	@FXML private TableView<WeekTimetable> weekTimetableTableView;
	@FXML private TableColumn<WeekTimetable, Long> colWeekTimetableID;
	@FXML private TableColumn<WeekTimetable, String> colWeekTimetableFrom;
	@FXML private TableColumn<WeekTimetable, String> colWeekTimetableTo;
	@FXML private TableColumn<WeekTimetable, String> colWeekTimetableMonday;
	@FXML private TableColumn<WeekTimetable, String> colWeekTimetableTeusday;
	@FXML private TableColumn<WeekTimetable, String> colWeekTimetableWednesday;
	@FXML private TableColumn<WeekTimetable, String> colWeekTimetableThursday;
	@FXML private TableColumn<WeekTimetable, String> colWeekTimetableFriday;

	//============================================================================================================================================//
	//=============================================================PROGRAM========================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtProgramID;
	@FXML private TextField txtProgramCourseName;
	@FXML private TextArea txtProgramCourseTitle;
	@FXML private TextField txtProgramTeacher;
	@FXML private TextArea txtProgramComments;

	@FXML private TableView<Program> programTableView;
	@FXML private TableColumn<Program, Long> colProgramID;
	@FXML private TableColumn<Program, String> colProgramCourseName;
	@FXML private TableColumn<Program, String> colProgramCourseTitle;
	@FXML private TableColumn<Program, String> colProgramTeacher;
	@FXML private TableColumn<Program, String> colProgramComments;

	//============================================================================================================================================//
	//=============================================================BOOKS==========================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtBooksID;
	@FXML private TextField txtBooksCourseName;
	@FXML private TextArea txtBooksBookTitle;
	@FXML private TextArea txtBooksAuthors;
	@FXML private TextArea txtBooksPublishingHouse;
	@FXML private TextArea txtBooksComments;

	@FXML private TableView<Books> booksTableView;
	@FXML private TableColumn<Books, Long> colBooksID;
	@FXML private TableColumn<Books, String> colBooksCourseName;
	@FXML private TableColumn<Books, String> colBooksBookTitle;
	@FXML private TableColumn<Books, String> colBooksAuthors;
	@FXML private TableColumn<Books, String> colBooksPublishingHouse;
	@FXML private TableColumn<Books, String> colBooksComments;

	//============================================================================================================================================//
	//=============================================================SUBJECTS=======================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtSubjectsID;
	@FXML private DatePicker dtpSubjectsDate;
	@FXML private TextField txtSubjectsNumber;
	@FXML private TextField txtSubjectsCourseName;
	@FXML private TextArea txtSubjectsSubject;
	@FXML private TextField txtSubjectsPresent;
	@FXML private TextField txtSubjectsAbsent;
	@FXML private TextField txtSubjectsTeacher;

	@FXML private TableView<Subjects> subjectsTableView;
	@FXML private TableColumn<Subjects, Long> colSubjectsID;
	@FXML private TableColumn<Subjects, LocalDate> colSubjectsDate;
	@FXML private TableColumn<Subjects, Long> colSubjectsNumber;
	@FXML private TableColumn<Subjects, String> colSubjectsCourseName;
	@FXML private TableColumn<Subjects, String> colSubjectsSubject;
	@FXML private TableColumn<Subjects, Long> colSubjectsPresent;
	@FXML private TableColumn<Subjects, Long> colSubjectsAbsent;
	@FXML private TableColumn<Subjects, String> colSubjectsTeacher;

	//============================================================================================================================================//
	//=============================================================TRIPS==========================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtTripsID;
	@FXML private DatePicker dtpTripsBegginingDate;
	@FXML private DatePicker dtpTripsEndDate;
	@FXML private TextField txtTripsDuration;
	@FXML private TextField txtTripsParticipants;
	@FXML private TextArea txtTripsDestination;
	@FXML private TextField txtTripsTeacher;

	@FXML private TableView<Trips> tripsTableView;
	@FXML private TableColumn<Trips, Long> colTripsID;
	@FXML private TableColumn<Trips, LocalDate> colTripsBegginingDate;
	@FXML private TableColumn<Trips, LocalDate> colTripsEndDate;
	@FXML private TableColumn<Trips, Long> colTripsDuration;
	@FXML private TableColumn<Trips, Long> colTripsParticipants;
	@FXML private TableColumn<Trips, String> colTripsDestination;
	@FXML private TableColumn<Trips, String> colTripsTeacher;

	//============================================================================================================================================//
	//=============================================================EVENTS=========================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtEventsID;
	@FXML private DatePicker dtpEventsDate;
	@FXML private TextArea txtEventsInfo;

	@FXML private TableView<Events> eventsTableView;
	@FXML private TableColumn<Events, Long> colEventsID;
	@FXML private TableColumn<Events, LocalDate> colEventsDate;
	@FXML private TableColumn<Events, String> colEventsInfo;

	//============================================================================================================================================//
	//=============================================================ADDITIONAL BEHAVIOUR===========================================================//
	//============================================================================================================================================//
	@FXML private TextField txtAdditionalBehaviourID;
	@FXML private TextField txtAdditionalBehaviourStudentName;
	@FXML private DatePicker dtpAdditionalBehaviourDate;
	@FXML private TextField txtAdditionalBehaviourKind;
	@FXML private TextArea txtAdditionalBehaviourInfo;
	@FXML private TextField txtAdditionalBehaviourTeacher;

	@FXML private TableView<AdditionalBehaviour> additionalBehaviourTableView;
	@FXML private TableColumn<AdditionalBehaviour, Long> colAdditionalBehaviourID;
	@FXML private TableColumn<AdditionalBehaviour, String> colAdditionalBehaviourStudentName;
	@FXML private TableColumn<AdditionalBehaviour, LocalDate> colAdditionalBehaviourDate;
	@FXML private TableColumn<AdditionalBehaviour, String> colAdditionalBehaviourKind;
	@FXML private TableColumn<AdditionalBehaviour, String> colAdditionalBehaviourInfo;
	@FXML private TableColumn<AdditionalBehaviour, String> colAdditionalBehaviourTeacher;

	//============================================================================================================================================//
	//=============================================================TEACHERS=======================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtTeachersID;
	@FXML private TextField txtTeachersTeacherName;
	@FXML private TextField txtTeachersCourseName;

	@FXML private TableView<Teachers> teachersTableView;
	@FXML private TableColumn<Teachers, Long> colTeachersID;
	@FXML private TableColumn<Teachers, String> colTeachersTeacherName;
	@FXML private TableColumn<Teachers, String> colTeachersCourseName;

	//============================================================================================================================================//
	//=============================================================ATTENDANCE=====================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtAttendanceID;
	@FXML private TextField txtAttendanceCourseName;
	@FXML private DatePicker dtpAttendanceDate;
	@FXML private TextField txtAttendanceTeacher;
	@FXML private RadioButton rbAttendance1;
	@FXML private RadioButton rbAbsenceJustified1;
	@FXML private RadioButton rbAbsenceUnexcused1;
	@FXML private RadioButton rbLate1;
	@FXML private RadioButton rbPrepared1;
	@FXML private RadioButton rbUnprepared1;
	@FXML private RadioButton rbAttendance2;
	@FXML private RadioButton rbAbsenceJustified2;
	@FXML private RadioButton rbAbsenceUnexcused2;
	@FXML private RadioButton rbLate2;
	@FXML private RadioButton rbPrepared2;
	@FXML private RadioButton rbUnprepared2;
	@FXML private RadioButton rbAttendance3;
	@FXML private RadioButton rbAbsenceJustified3;
	@FXML private RadioButton rbAbsenceUnexcused3;
	@FXML private RadioButton rbLate3;
	@FXML private RadioButton rbPrepared3;
	@FXML private RadioButton rbUnprepared3;

	@FXML private TableView<Attendance> attendanceTableView;
	@FXML private TableColumn<Attendance, Long> colAttendanceID;
	@FXML private TableColumn<Attendance, String> colAttendanceStudentName;
	@FXML private TableColumn<Attendance, String> colAttendanceCourseName;
	@FXML private TableColumn<Attendance, LocalDate> colAttendanceDate;
	@FXML private TableColumn<Attendance, String> colAttendanceAttendance;
	@FXML private TableColumn<Attendance, String> colAttendancePreaparation;
	@FXML private TableColumn<Attendance, String> colAttendanceTeacher;

	//============================================================================================================================================//
	//=============================================================MARKS==========================================================================//
	//============================================================================================================================================//
	@FXML private TextField txtMarksID;
	@FXML private TextField txtMarksStudentName;
	@FXML private TextField txtMarksCourseName;
	@FXML private TextArea txtMarksTest1;
	@FXML private TextArea txtMarksTest2;
	@FXML private TextArea txtMarksQuiz;
	@FXML private TextArea txtMarksOral;
	@FXML private TextArea txtMarksPaper;
	@FXML private TextArea txtMarksActivity;

	@FXML private TableView<Marks> marksTableView;
	@FXML private TableColumn<Marks, Long> colMarksID;
	@FXML private TableColumn<Marks, String> colMarksStudentName;
	@FXML private TableColumn<Marks, String> colMarksCourseName;
	@FXML private TableColumn<Marks, String> colMarksTest1;
	@FXML private TableColumn<Marks, String> colMarksTest2;
	@FXML private TableColumn<Marks, String> colMarksQuiz;
	@FXML private TableColumn<Marks, String> colMarksOral;
	@FXML private TableColumn<Marks, String> colMarksPaper;
	@FXML private TableColumn<Marks, String> colMarksActivity;

	//============================================================================================================================================//
	//============================================================================================================================================//
	//============================================================================================================================================//

	private RMIClient main;
	private LoginFormController cont;
	private Service personService;
	private String adminLogin;



	@Override
	public void initialize(URL url, ResourceBundle rb) {

		colId.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

		colPersonalDataID.setCellValueFactory(new PropertyValueFactory<PersonalData, Long>("personalDataID"));
		colPersonalDataRegisterationNumber.setCellValueFactory(new PropertyValueFactory<PersonalData, Long>("personalDataRegisterationNumber"));
		colPersonalDataStudentName.setCellValueFactory(new PropertyValueFactory<>("personalDataStudentName"));
		colPersonalDataBirthDate.setCellValueFactory(new PropertyValueFactory<>("personalDataBirthDate"));
		colPersonalDataBirthLocation.setCellValueFactory(new PropertyValueFactory<>("personalDataBirthLocation"));
		colPersonalDataPESEL.setCellValueFactory(new PropertyValueFactory<PersonalData, Long>("personalDataPESEL"));
		colPersonalDataFatherName.setCellValueFactory(new PropertyValueFactory<>("personalDataFatherName"));
		colPersonalDataMotherName.setCellValueFactory(new PropertyValueFactory<>("personalDataMotherName"));
		colPersonalDataPatentsAddress.setCellValueFactory(new PropertyValueFactory<>("personalDataParentsAddress"));
		colPersonalDataParentsMobileNumber.setCellValueFactory(new PropertyValueFactory<>("personalDataParentsMobileNumber"));

		colWeekTimetableID.setCellValueFactory(new PropertyValueFactory<WeekTimetable, Long>("weekTimetableID"));
		colWeekTimetableFrom.setCellValueFactory(new PropertyValueFactory<>("weekTimetableFrom"));
		colWeekTimetableTo.setCellValueFactory(new PropertyValueFactory<>("weekTimetableTo"));
		colWeekTimetableMonday.setCellValueFactory(new PropertyValueFactory<>("weekTimetableMonday"));
		colWeekTimetableTeusday.setCellValueFactory(new PropertyValueFactory<>("weekTimetableTeusday"));
		colWeekTimetableWednesday.setCellValueFactory(new PropertyValueFactory<>("weekTimetableWednesday"));
		colWeekTimetableThursday.setCellValueFactory(new PropertyValueFactory<>("weekTimetableThursday"));
		colWeekTimetableFriday.setCellValueFactory(new PropertyValueFactory<>("weekTimetableFriday"));

		colProgramID.setCellValueFactory(new PropertyValueFactory<Program, Long>("programID"));
		colProgramCourseName.setCellValueFactory(new PropertyValueFactory<>("programCourseName"));
		colProgramCourseTitle.setCellValueFactory(new PropertyValueFactory<>("programCourseTitle"));
		colProgramTeacher.setCellValueFactory(new PropertyValueFactory<>("programTeacher"));
		colProgramComments.setCellValueFactory(new PropertyValueFactory<>("programComments"));

		colBooksID.setCellValueFactory(new PropertyValueFactory<Books, Long>("booksID"));
		colBooksCourseName.setCellValueFactory(new PropertyValueFactory<>("booksCourseName"));
		colBooksBookTitle.setCellValueFactory(new PropertyValueFactory<>("booksBookTitle"));
		colBooksAuthors.setCellValueFactory(new PropertyValueFactory<>("booksAuthors"));
		colBooksPublishingHouse.setCellValueFactory(new PropertyValueFactory<>("booksPublishingHouse"));
		colBooksComments.setCellValueFactory(new PropertyValueFactory<>("booksComments"));

		colSubjectsID.setCellValueFactory(new PropertyValueFactory<Subjects, Long>("subjectsID"));
		colSubjectsDate.setCellValueFactory(new PropertyValueFactory<>("subjectsDate"));
		colSubjectsNumber.setCellValueFactory(new PropertyValueFactory<Subjects, Long>("subjectsNumber"));
		colSubjectsCourseName.setCellValueFactory(new PropertyValueFactory<>("subjectsCourseName"));
		colSubjectsSubject.setCellValueFactory(new PropertyValueFactory<>("subjectsSubject"));
		colSubjectsPresent.setCellValueFactory(new PropertyValueFactory<Subjects, Long>("subjectsPresent"));
		colSubjectsAbsent.setCellValueFactory(new PropertyValueFactory<Subjects, Long>("subjectsAbsent"));
		colSubjectsTeacher.setCellValueFactory(new PropertyValueFactory<>("subjectsTeacher"));

		colTripsID.setCellValueFactory(new PropertyValueFactory<Trips, Long>("tripsID"));
		colTripsBegginingDate.setCellValueFactory(new PropertyValueFactory<>("tripsBegginingDate"));
		colTripsEndDate.setCellValueFactory(new PropertyValueFactory<>("tripsEndDate"));
		colTripsDuration.setCellValueFactory(new PropertyValueFactory<Trips, Long>("tripsDuration"));
		colTripsParticipants.setCellValueFactory(new PropertyValueFactory<Trips, Long>("tripsParticipants"));
		colTripsDestination.setCellValueFactory(new PropertyValueFactory<>("tripsDestination"));
		colTripsTeacher.setCellValueFactory(new PropertyValueFactory<>("tripsTeacher"));

		colEventsID.setCellValueFactory(new PropertyValueFactory<Events, Long>("eventsID"));
		colEventsDate.setCellValueFactory(new PropertyValueFactory<>("eventsDate"));
		colEventsInfo.setCellValueFactory(new PropertyValueFactory<>("eventsInfo"));

		colAdditionalBehaviourID.setCellValueFactory(new PropertyValueFactory<AdditionalBehaviour, Long>("additionalBehaviourID"));
		colAdditionalBehaviourStudentName.setCellValueFactory(new PropertyValueFactory<>("additionalBehaviourStudentName"));
		colAdditionalBehaviourDate.setCellValueFactory(new PropertyValueFactory<>("additionalBehaviourDate"));
		colAdditionalBehaviourKind.setCellValueFactory(new PropertyValueFactory<>("additionalBehaviourKind"));
		colAdditionalBehaviourInfo.setCellValueFactory(new PropertyValueFactory<>("additionalBehaviourInfo"));
		colAdditionalBehaviourTeacher.setCellValueFactory(new PropertyValueFactory<>("additionalBehaviourTeacher"));

		colTeachersID.setCellValueFactory(new PropertyValueFactory<Teachers, Long>("teachersID"));
		colTeachersTeacherName.setCellValueFactory(new PropertyValueFactory<>("teachersTeacherName"));
		colTeachersCourseName.setCellValueFactory(new PropertyValueFactory<>("teachersCourseName"));

		colAttendanceID.setCellValueFactory(new PropertyValueFactory<Attendance, Long>("attendanceID"));
		colAttendanceStudentName.setCellValueFactory(new PropertyValueFactory<>("attendanceStudentName"));
		colAttendanceDate.setCellValueFactory(new PropertyValueFactory<>("attendanceDate"));
		colAttendanceAttendance.setCellValueFactory(new PropertyValueFactory<>("attendanceAttendance"));
		colAttendancePreaparation.setCellValueFactory(new PropertyValueFactory<>("attendancePreparation"));
		colAttendanceTeacher.setCellValueFactory(new PropertyValueFactory<>("attendanceTeacher"));

		colMarksID.setCellValueFactory(new PropertyValueFactory<Marks, Long>("marksID"));
		colMarksStudentName.setCellValueFactory(new PropertyValueFactory<>("marksStudentName"));
		colMarksCourseName.setCellValueFactory(new PropertyValueFactory<>("marksCourseName"));
		colMarksTest1.setCellValueFactory(new PropertyValueFactory<>("marksTest1"));
		colMarksTest2.setCellValueFactory(new PropertyValueFactory<>("marksTest2"));
		colMarksQuiz.setCellValueFactory(new PropertyValueFactory<>("marksQuiz"));
		colMarksOral.setCellValueFactory(new PropertyValueFactory<>("marksOral"));
		colMarksPaper.setCellValueFactory(new PropertyValueFactory<>("marksPaper"));
		colMarksActivity.setCellValueFactory(new PropertyValueFactory<>("marksActivity"));

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

			@Override
			public void changed(ObservableValue<? extends Person> arg0, Person oldPerson, Person newPerson) {
				if(newPerson != null) {
					txtId.setText(Long.toString(newPerson.getId()));
					txtFirstName.setText(newPerson.getFirstName());
					txtLastName.setText(newPerson.getLastName());
					dtpBirthDate.setValue(newPerson.getBirthDate());
				}else {
					clearField();
				}
			}
		});

		personalDataTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonalData>()  {

			@Override
			public void changed(ObservableValue<? extends PersonalData> arg0, PersonalData oldPersonalData, PersonalData newPersonalData) {
				if(newPersonalData != null) {
					txtPersonalDataID.setText(Long.toString(newPersonalData.getId()));
					txtPersonalDataRegisterationNumber.setText(Long.toString(newPersonalData.getRegisterationNumber()));
					txtPersonalDataStudentName.setText(newPersonalData.getStudentName());
					dtpPersonalDataBirthDate.setValue(newPersonalData.getBirthDate());
					txtPersonalDataBirthLocation.setText(newPersonalData.getBirthLocation());
					txtPersonalDataPESEL.setText(Long.toString(newPersonalData.getPESEL()));
					txtPersonalDataFatherName.setText(newPersonalData.getFatherName());
					txtPersonalDataMotherName.setText(newPersonalData.getMotherName());
					txtPersonalDataParentsAddress.setText(newPersonalData.getParentsAddress());
					txtPersonalDataParentsMobileNumber.setText(newPersonalData.getParentsMobileNumber());
				}else {
					clearField();
				}
			}
		});

		weekTimetableTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WeekTimetable>()  {

			@Override
			public void changed(ObservableValue<? extends WeekTimetable> arg0, WeekTimetable oldWeekTimetable, WeekTimetable newWeekTimetable) {
				if(newWeekTimetable != null) {
					txtWeekTimetableID.setText(Long.toString(newWeekTimetable.getId()));
					txtWeekTimetableFrom.setText(newWeekTimetable.getFrom());
					txtWeekTimetableTo.setText(newWeekTimetable.getTo());
					txtWeekTimetableMonday.setText(newWeekTimetable.getMonday());
					txtWeekTimetableTeusday.setText(newWeekTimetable.getTeusday());
					txtWeekTimetableWednesday.setText(newWeekTimetable.getWednesday());
					txtWeekTimetableThursday.setText(newWeekTimetable.getThursday());
					txtWeekTimetableFriday.setText(newWeekTimetable.getFriday());
				}else {
					clearField();
				}
			}
		});

		programTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Program>()  {

			@Override
			public void changed(ObservableValue<? extends Program> arg0, Program oldProgram, Program newProgram) {
				if(newProgram != null) {
					txtProgramID.setText(Long.toString(newProgram.getId()));
					txtProgramCourseName.setText(newProgram.getCourseName());
					txtProgramCourseTitle.setText(newProgram.getCourseTitle());
					txtProgramTeacher.setText(newProgram.getTeacher());
					txtProgramComments.setText(newProgram.getComments());
				}else {
					clearField();
				}
			}
		});

		booksTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Books>()  {

			@Override
			public void changed(ObservableValue<? extends Books> arg0, Books oldBooks, Books newBooks) {
				if(newBooks != null) {
					txtBooksID.setText(Long.toString(newBooks.getId()));
					txtBooksCourseName.setText(newBooks.getCourseName());
					txtBooksBookTitle.setText(newBooks.getBookTitle());
					txtBooksAuthors.setText(newBooks.getAuthors());
					txtBooksPublishingHouse.setText(newBooks.getPublishingHouse());
					txtBooksComments.setText(newBooks.getComments());
				}else {
					clearField();
				}
			}
		});

		subjectsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Subjects>()  {

			@Override
			public void changed(ObservableValue<? extends Subjects> arg0, Subjects oldSubjects, Subjects newSubjects) {
				if(newSubjects != null) {
					txtSubjectsID.setText(Long.toString(newSubjects.getId()));
					dtpSubjectsDate.setValue(newSubjects.getDate());
					txtSubjectsNumber.setText(Long.toString(newSubjects.getNumber()));
					txtSubjectsCourseName.setText(newSubjects.getCourseName());
					txtSubjectsSubject.setText(newSubjects.getSubject());
					txtSubjectsPresent.setText(Long.toString(newSubjects.getPresent()));
					txtSubjectsAbsent.setText(Long.toString(newSubjects.getAbsent()));
					txtSubjectsTeacher.setText(newSubjects.getTeacher());
				}else {
					clearField();
				}
			}
		});

		tripsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Trips>()  {

			@Override
			public void changed(ObservableValue<? extends Trips> arg0, Trips oldTrips, Trips newTrips) {
				if(newTrips != null) {
					txtTripsID.setText(Long.toString(newTrips.getId()));
					dtpTripsBegginingDate.setValue(newTrips.getBegginingDate());
					dtpTripsEndDate.setValue(newTrips.getEndDate());
					txtTripsDuration.setText(Long.toString(newTrips.getDuration()));
					txtTripsParticipants.setText(Long.toString(newTrips.getParticipants()));
					txtTripsDestination.setText(newTrips.getDestination());
					txtTripsTeacher.setText(newTrips.getTeacher());
				}else {
					clearField();
				}
			}
		});

		eventsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Events>()  {

			@Override
			public void changed(ObservableValue<? extends Events> arg0, Events oldEvents, Events newEvents) {
				if(newEvents != null) {
					txtEventsID.setText(Long.toString(newEvents.getId()));
					dtpEventsDate.setValue(newEvents.getDate());
					txtEventsInfo.setText(newEvents.getInfo());
				}else {
					clearField();
				}
			}
		});

		additionalBehaviourTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AdditionalBehaviour>()  {

			@Override
			public void changed(ObservableValue<? extends AdditionalBehaviour> arg0, AdditionalBehaviour oldAdditionalBehaviour, AdditionalBehaviour newAdditionalBehaviour) {
				if(newAdditionalBehaviour != null) {
					txtAdditionalBehaviourID.setText(Long.toString(newAdditionalBehaviour.getId()));
					txtAdditionalBehaviourStudentName.setText(newAdditionalBehaviour.getStudentName());
					dtpAdditionalBehaviourDate.setValue(newAdditionalBehaviour.getDate());
					txtAdditionalBehaviourKind.setText(newAdditionalBehaviour.getKind());
					txtAdditionalBehaviourInfo.setText(newAdditionalBehaviour.getInfo());
					txtAdditionalBehaviourTeacher.setText(newAdditionalBehaviour.getTeacher());
				}else {
					clearField();
				}
			}
		});

		teachersTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Teachers>()  {

			@Override
			public void changed(ObservableValue<? extends Teachers> arg0, Teachers oldTeachers, Teachers newTeachers) {
				if(newTeachers != null) {
					txtTeachersID.setText(Long.toString(newTeachers.getId()));
					txtTeachersTeacherName.setText(newTeachers.getTeacherName());
					txtTeachersCourseName.setText(newTeachers.getCourseName());
				}else {
					clearField();
				}
			}
		});

		attendanceTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Attendance>()  {

			@Override
			public void changed(ObservableValue<? extends Attendance> arg0, Attendance oldAttendance, Attendance newAttendance) {
				if(newAttendance != null) {
					txtAttendanceID.setText(Long.toString(newAttendance.getId()));
					dtpAttendanceDate.setValue(newAttendance.getDate());
					txtAttendanceCourseName.setText(newAttendance.getCourseName());
					txtAttendanceTeacher.setText(newAttendance.getTeacher());
				}else {
					clearField();
				}
			}
		});

		marksTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Marks>()  {

			@Override
			public void changed(ObservableValue<? extends Marks> arg0, Marks oldMarks, Marks newMarks) {
				if(newMarks != null) {
					txtMarksID.setText(Long.toString(newMarks.getId()));
					txtMarksStudentName.setText(newMarks.getStudentName());
					txtMarksCourseName.setText(newMarks.getCourseName());
					txtMarksTest1.setText(newMarks.getTest1());
					txtMarksTest2.setText(newMarks.getTest2());
					txtMarksQuiz.setText(newMarks.getQuiz());
					txtMarksOral.setText(newMarks.getOral());
					txtMarksPaper.setText(newMarks.getPaper());
					txtMarksActivity.setText(newMarks.getActivity());
				}else {
					clearField();
				}
			}
		});
	}

	//*************************************************************METHODS************************************************************************//

    //============================================================================================================================================//
	//=============================================================PERSONAL DATA==================================================================//
	//============================================================================================================================================//
	@FXML
	public void personalDataOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtPersonalDataRegisterationNumber.getText() == null || txtPersonalDataRegisterationNumber.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: numer ewidencyjny!\n";
		}

		if(txtPersonalDataStudentName.getText() == null || txtPersonalDataStudentName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko ucznia!\n";
		}

		if(dtpPersonalDataBirthDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data urodzenia!\n";
		}

		if(txtPersonalDataBirthLocation.getText() == null || txtPersonalDataBirthLocation.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: miejsce urodzenia!\n";
		}

		if(txtPersonalDataPESEL.getText() == null || txtPersonalDataPESEL.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: PESEL!\n";
		}

		if(txtPersonalDataParentsAddress.getText() == null || txtPersonalDataParentsAddress.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: adres rodziców!\n";
		}

		if(txtPersonalDataParentsMobileNumber.getText() == null || txtPersonalDataParentsMobileNumber.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: numer telefonu rodziców!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				PersonalData pd = new PersonalData();
				pd.setRegisterationNumber(Long.valueOf(txtPersonalDataRegisterationNumber.getText()));
				pd.setStudentName(txtPersonalDataStudentName.getText());
				pd.setBirthDate(dtpPersonalDataBirthDate.getValue());
				pd.setBirthLocation(txtPersonalDataBirthLocation.getText());
				pd.setPESEL(Long.valueOf(txtPersonalDataPESEL.getText()));
				pd.setFatherName(txtPersonalDataFatherName.getText());
				pd.setMotherName(txtPersonalDataMotherName.getText());
				pd.setParentsAddress(txtPersonalDataParentsAddress.getText());
				pd.setParentsMobileNumber(txtPersonalDataParentsMobileNumber.getText());

				pd = personService.insertPersonalData(pd);

				personalDataTableView.getItems().add(pd);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void personalDataOnUpdate(ActionEvent event) {
		int index = personalDataTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtPersonalDataRegisterationNumber.getText() == null || txtPersonalDataRegisterationNumber.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: numer ewidencyjny!\n";
		}

		if(txtPersonalDataStudentName.getText() == null || txtPersonalDataStudentName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko ucznia!\n";
		}

		if(dtpPersonalDataBirthDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data urodzenia!\n";
		}

		if(txtPersonalDataBirthLocation.getText() == null || txtPersonalDataBirthLocation.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: miejsce urodzenia!\n";
		}

		if(txtPersonalDataPESEL.getText() == null || txtPersonalDataPESEL.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: PESEL!\n";
		}

		if(txtPersonalDataParentsAddress.getText() == null || txtPersonalDataParentsAddress.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: adres rodziców!\n";
		}

		if(txtPersonalDataParentsMobileNumber.getText() == null || txtPersonalDataParentsMobileNumber.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: numer telefonu rodziców!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				PersonalData pd = new PersonalData();
				pd.setId(Long.valueOf(txtPersonalDataID.getText()));
				pd.setRegisterationNumber(Long.valueOf(txtPersonalDataRegisterationNumber.getText()));
				pd.setStudentName(txtPersonalDataStudentName.getText());
				pd.setBirthDate(dtpPersonalDataBirthDate.getValue());
				pd.setBirthLocation(txtPersonalDataBirthLocation.getText());
				pd.setPESEL(Long.valueOf(txtPersonalDataPESEL.getText()));
				pd.setFatherName(txtPersonalDataFatherName.getText());
				pd.setMotherName(txtPersonalDataMotherName.getText());
				pd.setParentsAddress(txtPersonalDataParentsAddress.getText());
				pd.setParentsMobileNumber(txtPersonalDataParentsMobileNumber.getText());

				personService.updatePersonalData(pd);

				personalDataTableView.getItems().set(index,  pd);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void personalDataOnDelete(ActionEvent event) {
		try {
			PersonalData pd = personalDataTableView.getSelectionModel().getSelectedItem();

			if(pd == null) {
				return;
			}

			personService.deletePersonalData(pd.getId());

			personalDataTableView.getItems().remove(pd);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void personalDataOnRefresh(ActionEvent event) {
		try {
			personalDataTableView.getItems().setAll(personService.getAllPersonalData());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//=============================================================WEEK TIMETABLE=================================================================//
	//============================================================================================================================================//
	@FXML
	public void weekTimetableOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtWeekTimetableFrom.getText() == null || txtWeekTimetableFrom.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: godzina rozpoczęcia zajęć!\n";
		}

		if(txtWeekTimetableTo.getText() == null || txtWeekTimetableTo.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: godzina zakończenia zajęć!\n";
		}

		if(txtWeekTimetableMonday.getText() == null || txtWeekTimetableMonday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: poniedziałek!\n";
		}

		if(txtWeekTimetableTeusday.getText() == null || txtWeekTimetableTeusday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: wtorek!\n";
		}

		if(txtWeekTimetableWednesday.getText() == null || txtWeekTimetableWednesday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: środa!\n";
		}

		if(txtWeekTimetableThursday.getText() == null || txtWeekTimetableThursday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: czwartek!\n";
		}

		if(txtWeekTimetableFriday.getText() == null || txtWeekTimetableFriday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: piątek!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				WeekTimetable wt = new WeekTimetable();
				wt.setFrom(txtWeekTimetableFrom.getText());
				wt.setTo(txtWeekTimetableTo.getText());
				wt.setMonday(txtWeekTimetableMonday.getText());
				wt.setTeusday(txtWeekTimetableTeusday.getText());
				wt.setWednesday(txtWeekTimetableWednesday.getText());
				wt.setThursday(txtWeekTimetableThursday.getText());
				wt.setFriday(txtWeekTimetableFriday.getText());

				wt = personService.insertWeekTimetable(wt);

				weekTimetableTableView.getItems().add(wt);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void weekTimetableOnUpdate(ActionEvent event) {
		int index = weekTimetableTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtWeekTimetableFrom.getText() == null || txtWeekTimetableFrom.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: godzina rozpoczęcia zajęć!\n";
		}

		if(txtWeekTimetableTo.getText() == null || txtWeekTimetableTo.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: godzina zakończenia zajęć!\n";
		}

		if(txtWeekTimetableMonday.getText() == null || txtWeekTimetableMonday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: poniedziałek!\n";
		}

		if(txtWeekTimetableTeusday.getText() == null || txtWeekTimetableTeusday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: wtorek!\n";
		}

		if(txtWeekTimetableWednesday.getText() == null || txtWeekTimetableWednesday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: środa!\n";
		}

		if(txtWeekTimetableThursday.getText() == null || txtWeekTimetableThursday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: czwartek!\n";
		}

		if(txtWeekTimetableFriday.getText() == null || txtWeekTimetableFriday.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: piątek!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				WeekTimetable wt = new WeekTimetable();
				wt.setId(Long.valueOf(txtWeekTimetableID.getText()));
				wt.setFrom(txtWeekTimetableFrom.getText());
				wt.setTo(txtWeekTimetableTo.getText());
				wt.setMonday(txtWeekTimetableMonday.getText());
				wt.setTeusday(txtWeekTimetableTeusday.getText());
				wt.setWednesday(txtWeekTimetableWednesday.getText());
				wt.setThursday(txtWeekTimetableThursday.getText());
				wt.setFriday(txtWeekTimetableFriday.getText());

				personService.updateWeekTimetable(wt);

				weekTimetableTableView.getItems().set(index,  wt);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void weekTimetableOnDelete(ActionEvent event) {
		try {
			WeekTimetable wt = weekTimetableTableView.getSelectionModel().getSelectedItem();

			if(wt == null) {
				return;
			}

			personService.deleteWeekTimetable(wt.getId());

			weekTimetableTableView.getItems().remove(wt);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void weekTimetableOnRefresh(ActionEvent event) {
		try {
			weekTimetableTableView.getItems().setAll(personService.getAllWeekTimetable());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//=============================================================PROGRAM========================================================================//
	//============================================================================================================================================//
	@FXML
	public void programOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtProgramCourseName.getText() == null || txtProgramCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przedmiotu!\n";
		}

		if(txtProgramCourseTitle.getText() == null || txtProgramCourseTitle.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: tytuł programu!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Program p = new Program();
				p.setCourseName(txtProgramCourseName.getText());
				p.setCourseTitle(txtProgramCourseTitle.getText());
				p.setTeacher(adminLogin);
				p.setComments(txtProgramComments.getText());

				p = personService.insertProgram(p);

				programTableView.getItems().add(p);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void programOnUpdate(ActionEvent event) {
		int index = programTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtProgramCourseName.getText() == null || txtProgramCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przedmiotu!\n";
		}

		if(txtProgramCourseTitle.getText() == null || txtProgramCourseTitle.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: tytuł programu!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Program p = new Program();
				p.setId(Long.valueOf(txtProgramID.getText()));
				p.setCourseName(txtProgramCourseName.getText());
				p.setCourseTitle(txtProgramCourseTitle.getText());
				p.setTeacher(adminLogin);
				p.setComments(txtProgramComments.getText());

				personService.updateProgram(p);

				programTableView.getItems().set(index,  p);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void programOnDelete(ActionEvent event) {
		try {
			Program p = programTableView.getSelectionModel().getSelectedItem();

			if(p == null) {
				return;
			}

			personService.deleteProgram(p.getId());

			programTableView.getItems().remove(p);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void programOnRefresh(ActionEvent event) {
		try {
			programTableView.getItems().setAll(personService.getAllProgram());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//=============================================================BOOKS==========================================================================//
	//============================================================================================================================================//
	@FXML
	public void booksOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtBooksCourseName.getText() == null || txtBooksCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przedmiotu!\n";
		}

		if(txtBooksBookTitle.getText() == null || txtBooksBookTitle.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: tytuł podręcznika!\n";
		}

		if(txtBooksAuthors.getText() == null || txtBooksAuthors.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: autorzy!\n";
		}

		if(txtBooksPublishingHouse.getText() == null || txtBooksPublishingHouse.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: wydawnictwo!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Books b = new Books();
				b.setCourseName(txtBooksCourseName.getText());
				b.setBookTitle(txtBooksBookTitle.getText());
				b.setAuthors(txtBooksAuthors.getText());
				b.setPublishingHouse(txtBooksPublishingHouse.getText());
				b.setComments(txtBooksComments.getText());

				b = personService.insertBooks(b);

				booksTableView.getItems().add(b);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void booksOnUpdate(ActionEvent event) {
		int index = booksTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtBooksCourseName.getText() == null || txtBooksCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przedmiotu!\n";
		}

		if(txtBooksBookTitle.getText() == null || txtBooksBookTitle.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: tytuł podręcznika!\n";
		}

		if(txtBooksAuthors.getText() == null || txtBooksAuthors.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: autorzy!\n";
		}

		if(txtBooksPublishingHouse.getText() == null || txtBooksPublishingHouse.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: wydawnictwo!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Books b = new Books();
				b.setId(Long.valueOf(txtBooksID.getText()));
				b.setCourseName(txtBooksCourseName.getText());
				b.setBookTitle(txtBooksBookTitle.getText());
				b.setAuthors(txtBooksAuthors.getText());
				b.setPublishingHouse(txtBooksPublishingHouse.getText());
				b.setComments(txtBooksComments.getText());

				personService.updateBooks(b);

				booksTableView.getItems().set(index,  b);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void booksOnDelete(ActionEvent event) {
		try {
			Books b = booksTableView.getSelectionModel().getSelectedItem();

			if(b == null) {
				return;
			}

			personService.deleteBooks(b.getId());

			booksTableView.getItems().remove(b);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void booksOnRefresh(ActionEvent event) {
		try {
			booksTableView.getItems().setAll(personService.getAllBooks());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//=============================================================SUBJECTS=======================================================================//
	//============================================================================================================================================//
	@FXML
	public void subjectsOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(dtpSubjectsDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if(txtSubjectsNumber.getText() == null || txtSubjectsNumber.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nr kol. zajęć!\n";
		}

		if(txtSubjectsCourseName.getText() == null || txtSubjectsCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: przedmiot!\n";
		}

		if(txtSubjectsSubject.getText() == null || txtSubjectsSubject.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: temat zajęć edukacyjnych!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Subjects s = new Subjects();
				s.setDate(dtpSubjectsDate.getValue());
				s.setNumber(Long.valueOf(txtSubjectsNumber.getText()));
				s.setCourseName(txtSubjectsCourseName.getText());
				s.setSubject(txtSubjectsSubject.getText());
				s.setPresent(Long.valueOf(txtSubjectsPresent.getText()));
				s.setAbsent(Long.valueOf(txtSubjectsAbsent.getText()));
				s.setTeacher(adminLogin);

				s = personService.insertSubjects(s);

				subjectsTableView.getItems().add(s);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void subjectsOnUpdate(ActionEvent event) {
		int index = subjectsTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(dtpSubjectsDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if(txtSubjectsNumber.getText() == null || txtSubjectsNumber.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nr kol. zajęć!\n";
		}

		if(txtSubjectsCourseName.getText() == null || txtSubjectsCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: przedmiot!\n";
		}

		if(txtSubjectsSubject.getText() == null || txtSubjectsSubject.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: temat zajęć edukacyjnych!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Subjects s = new Subjects();
				s.setId(Long.valueOf(txtSubjectsID.getText()));
				s.setDate(dtpSubjectsDate.getValue());
				s.setNumber(Long.valueOf(txtSubjectsNumber.getText()));
				s.setCourseName(txtSubjectsCourseName.getText());
				s.setSubject(txtSubjectsSubject.getText());
				s.setPresent(Long.valueOf(txtSubjectsPresent.getText()));
				s.setAbsent(Long.valueOf(txtSubjectsAbsent.getText()));
				s.setTeacher(adminLogin);

				personService.updateSubjects(s);

				subjectsTableView.getItems().set(index,  s);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void subjectsOnDelete(ActionEvent event) {
		try {
			Subjects s = subjectsTableView.getSelectionModel().getSelectedItem();

			if(s == null) {
				return;
			}

			personService.deleteSubjects(s.getId());

			subjectsTableView.getItems().remove(s);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void subjectsOnRefresh(ActionEvent event) {
		try {
			subjectsTableView.getItems().setAll(personService.getAllSubjects());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//=============================================================TRIPS==========================================================================//
	//============================================================================================================================================//
	@FXML
	public void tripsOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(dtpTripsBegginingDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data rozpoczęcia!\n";
		}

		if(dtpTripsEndDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data zakończenia!\n";
		}

		if(txtTripsParticipants.getText() == null || txtTripsParticipants.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: liczba uczestników!\n";
		}

		if(txtTripsDestination.getText() == null || txtTripsDestination.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: miejsce i cel wycieczki!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Trips t = new Trips();
				t.setBegginingDate(dtpTripsBegginingDate.getValue());
				t.setEndDate(dtpTripsEndDate.getValue());
				long daysBetween = ChronoUnit.DAYS.between(t.getBegginingDate(), t.getEndDate());
				System.out.println(Long.valueOf(daysBetween).toString());
				t.setDuration(Long.valueOf(daysBetween));
				t.setParticipants(Long.valueOf(txtTripsParticipants.getText()));
				t.setDestination(txtTripsParticipants.getText());
				t.setTeacher(adminLogin);

				t = personService.insertTrips(t);

				tripsTableView.getItems().add(t);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void tripsOnUpdate(ActionEvent event) {
		int index = tripsTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(dtpTripsBegginingDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data rozpoczęcia!\n";
		}

		if(dtpTripsEndDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data zakończenia!\n";
		}

		if(txtTripsParticipants.getText() == null || txtTripsParticipants.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: liczba uczestników!\n";
		}

		if(txtTripsDestination.getText() == null || txtTripsDestination.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: miejsce i cel wycieczki!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Trips t = new Trips();
				t.setId(Long.valueOf(txtTripsID.getText()));
				t.setBegginingDate(dtpTripsBegginingDate.getValue());
				t.setEndDate(dtpTripsEndDate.getValue());
				long daysBetween = ChronoUnit.DAYS.between(dtpTripsBegginingDate.getValue(), dtpTripsEndDate.getValue());
				t.setDuration(Long.valueOf(daysBetween));
				t.setParticipants(Long.valueOf(txtTripsParticipants.getText()));
				t.setDestination(txtTripsDestination.getText());
				t.setTeacher(adminLogin);

				personService.updateTrips(t);

				tripsTableView.getItems().set(index,  t);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void tripsOnDelete(ActionEvent event) {
		try {
			Trips t = tripsTableView.getSelectionModel().getSelectedItem();

			if(t == null) {
				return;
			}

			personService.deleteTrips(t.getId());

			tripsTableView.getItems().remove(t);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void tripsOnRefresh(ActionEvent event) {
		try {
			tripsTableView.getItems().setAll(personService.getAllTrips());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//=============================================================EVENTS=========================================================================//
	//============================================================================================================================================//
	@FXML
	public void eventsOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(dtpEventsDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if(txtEventsInfo.getText() == null || txtEventsInfo.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: informacja o przebiegu wydarzenia!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Events e = new Events();
				e.setDate(dtpEventsDate.getValue());
				e.setInfo(txtEventsInfo.getText());

				e = personService.insertEvents(e);

				eventsTableView.getItems().add(e);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void eventsOnUpdate(ActionEvent event) {
		int index = eventsTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(dtpEventsDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if(txtEventsInfo.getText() == null || txtEventsInfo.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: informacja o przebiegu wydarzenia!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Events e = new Events();
				e.setId(Long.valueOf(txtEventsID.getText()));
				e.setDate(dtpEventsDate.getValue());
				e.setInfo(txtEventsInfo.getText());

				personService.updateEvents(e);

				eventsTableView.getItems().set(index,  e);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void eventsOnDelete(ActionEvent event) {
		try {
			Events e = eventsTableView.getSelectionModel().getSelectedItem();

			if(e == null) {
				return;
			}

			personService.deleteEvents(e.getId());

			eventsTableView.getItems().remove(e);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void eventsOnRefresh(ActionEvent event) {
		try {
			eventsTableView.getItems().setAll(personService.getAllEvents());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//=============================================================ADDITIONAL BEHAVIOUR===========================================================//
	//============================================================================================================================================//
	@FXML
	public void additionalBehaviourOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtAdditionalBehaviourStudentName.getText() == null || txtAdditionalBehaviourStudentName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko ucznia!\n";
		}

		if(dtpAdditionalBehaviourDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data wpisu!\n";
		}

		if(txtAdditionalBehaviourKind.getText() == null || txtAdditionalBehaviourKind.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: rodzaj!\n";
		}

		if(txtAdditionalBehaviourInfo.getText() == null || txtAdditionalBehaviourInfo.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: treść!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				AdditionalBehaviour ab = new AdditionalBehaviour();
				ab.setStudentName(txtAdditionalBehaviourStudentName.getText());
				ab.setDate(dtpAdditionalBehaviourDate.getValue());
				ab.setKind(txtAdditionalBehaviourKind.getText());
				ab.setInfo(txtAdditionalBehaviourInfo.getText());
				ab.setTeacher(adminLogin);

				ab = personService.insertAdditionalBehaviour(ab);

				additionalBehaviourTableView.getItems().add(ab);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void additionalBehaviourOnUpdate(ActionEvent event) {
		int index = additionalBehaviourTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtAdditionalBehaviourStudentName.getText() == null || txtAdditionalBehaviourStudentName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko ucznia!\n";
		}

		if(dtpAdditionalBehaviourDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data wpisu!\n";
		}

		if(txtAdditionalBehaviourKind.getText() == null || txtAdditionalBehaviourKind.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: rodzaj!\n";
		}

		if(txtAdditionalBehaviourInfo.getText() == null || txtAdditionalBehaviourInfo.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: treść!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				AdditionalBehaviour ab = new AdditionalBehaviour();
				ab.setId(Long.valueOf(txtAdditionalBehaviourID.getText()));
				ab.setStudentName(txtAdditionalBehaviourStudentName.getText());
				ab.setDate(dtpAdditionalBehaviourDate.getValue());
				ab.setKind(txtAdditionalBehaviourKind.getText());
				ab.setInfo(txtAdditionalBehaviourInfo.getText());
				ab.setTeacher(adminLogin);

				personService.updateAdditionalBehaviour(ab);

				additionalBehaviourTableView.getItems().set(index,  ab);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void additionalBehaviourOnDelete(ActionEvent event) {
		try {
			AdditionalBehaviour ab = additionalBehaviourTableView.getSelectionModel().getSelectedItem();

			if(ab == null) {
				return;
			}

			personService.deleteAdditionalBehaviour(ab.getId());

			additionalBehaviourTableView.getItems().remove(ab);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void additionalBehaviourOnRefresh(ActionEvent event) {
		try {
			additionalBehaviourTableView.getItems().setAll(personService.getAllAdditionalBehaviour());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

    //============================================================================================================================================//
	//=============================================================TEACHERS=======================================================================//
	//============================================================================================================================================//
	@FXML
	public void teachersOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtTeachersTeacherName.getText() == null || txtTeachersTeacherName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko nauczyciela!\n";
		}

		if(txtTeachersCourseName.getText() == null || txtTeachersCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa prowadzonego przedmiotu!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Teachers t = new Teachers();
				t.setTeacherName(txtTeachersTeacherName.getText());
				t.setCourseName(txtTeachersCourseName.getText());

				t = personService.insertTeachers(t);

				teachersTableView.getItems().add(t);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void teachersOnUpdate(ActionEvent event) {
		int index = teachersTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtTeachersTeacherName.getText() == null || txtTeachersTeacherName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko nauczyciela!\n";
		}

		if(txtTeachersCourseName.getText() == null || txtTeachersCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa prowadzonego przedmiotu!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Teachers t = new Teachers();
				t.setId(Long.valueOf(txtTeachersID.getText()));
				t.setTeacherName(txtTeachersTeacherName.getText());
				t.setCourseName(txtTeachersCourseName.getText());

				personService.updateTeachers(t);

				teachersTableView.getItems().set(index,  t);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void teachersOnDelete(ActionEvent event) {
		try {
			Teachers t = teachersTableView.getSelectionModel().getSelectedItem();

			if(t == null) {
				return;
			}

			personService.deleteTeachers(t.getId());

			teachersTableView.getItems().remove(t);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void teachersOnRefresh(ActionEvent event) {
		try {
			teachersTableView.getItems().setAll(personService.getAllTeachers());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

    //============================================================================================================================================//
	//=============================================================ATTENDANCE=====================================================================//
	//============================================================================================================================================//
	@FXML
	public void attendanceOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtAttendanceCourseName.getText() == null || txtAttendanceCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przemiotu!\n";
		}

		if(dtpAttendanceDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				//pierwsza osoba z listy
				Attendance a1 = new Attendance();
				a1.setStudentName("Jakub Benduch");
				a1.setCourseName(txtAttendanceCourseName.getText());
				a1.setDate(dtpAttendanceDate.getValue());
				if (rbAttendance1.isSelected()){
					a1.setAttendance("ob.");
				}
				if (rbAbsenceUnexcused1.isSelected()){
					a1.setAttendance("nob.u");
				}
				if (rbAbsenceJustified1.isSelected()){
					a1.setAttendance("nob.n");
				}
				if (rbLate1.isSelected()){
					a1.setAttendance("s.");
				}
				if (rbPrepared1.isSelected()){
					a1.setPreparation("p.");
				}
				if (rbUnprepared1.isSelected()){
					a1.setPreparation("np.");
				}
				a1.setTeacher(adminLogin);
				a1 = personService.insertAttendance(a1);
				attendanceTableView.getItems().add(a1);

				//druga osoba z listy
				Attendance a2 = new Attendance();
				a2.setStudentName("Ewelina Grzelak");
				a2.setCourseName(txtAttendanceCourseName.getText());
				a2.setDate(dtpAttendanceDate.getValue());
				if (rbAttendance2.isSelected()){
					a2.setAttendance("ob.");
				}
				if (rbAbsenceUnexcused2.isSelected()){
					a2.setAttendance("nob.u");
				}
				if (rbAbsenceJustified2.isSelected()){
					a2.setAttendance("nob.n");
				}
				if (rbLate2.isSelected()){
					a2.setAttendance("s.");
				}
				if (rbPrepared2.isSelected()){
					a2.setPreparation("p.");
				}
				if (rbUnprepared2.isSelected()){
					a2.setPreparation("np.");
				}
				a2.setTeacher(adminLogin);
				a2 = personService.insertAttendance(a2);
				attendanceTableView.getItems().add(a2);

				//trzecia osoba z listy
				Attendance a3 = new Attendance();
				a3.setStudentName("Jan Kowalski");
				a3.setCourseName(txtAttendanceCourseName.getText());
				a3.setDate(dtpAttendanceDate.getValue());
				if (rbAttendance3.isSelected()){
					a3.setAttendance("ob.");
				}
				if (rbAbsenceUnexcused3.isSelected()){
					a3.setAttendance("nob.u");
				}
				if (rbAbsenceJustified3.isSelected()){
					a3.setAttendance("nob.n");
				}
				if (rbLate3.isSelected()){
					a3.setAttendance("s.");
				}
				if (rbPrepared3.isSelected()){
					a3.setPreparation("p.");
				}
				if (rbUnprepared3.isSelected()){
					a3.setPreparation("np.");
				}
				a3.setTeacher(adminLogin);
				a3 = personService.insertAttendance(a3);
				attendanceTableView.getItems().add(a3);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void attendanceOnUpdate1(ActionEvent event) {
		int index = attendanceTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtAttendanceCourseName.getText() == null || txtAttendanceCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przemiotu!\n";
		}

		if(dtpAttendanceDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Attendance a = new Attendance();
				a.setId(Long.valueOf(txtAttendanceID.getText()));
				a.setStudentName("Jakub Benduch");
				a.setCourseName(txtAttendanceCourseName.getText());
				a.setDate(dtpAttendanceDate.getValue());
				if (rbAttendance1.isSelected()){
					a.setAttendance("ob.");
				}
				if (rbAbsenceUnexcused1.isSelected()){
					a.setAttendance("nob.u");
				}
				if (rbAbsenceJustified1.isSelected()){
					a.setAttendance("nob.n");
				}
				if (rbLate1.isSelected()){
					a.setAttendance("s.");
				}
				if (rbPrepared1.isSelected()){
					a.setPreparation("p.");
				}
				if (rbUnprepared1.isSelected()){
					a.setPreparation("np.");
				}
				a.setTeacher(adminLogin);

				personService.updateAttendance(a);

				attendanceTableView.getItems().set(index,  a);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void attendanceOnUpdate2(ActionEvent event) {
		int index = attendanceTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtAttendanceCourseName.getText() == null || txtAttendanceCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przemiotu!\n";
		}

		if(dtpAttendanceDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Attendance a = new Attendance();
				a.setId(Long.valueOf(txtAttendanceID.getText()));
				a.setStudentName("Ewelina Grzelak");
				a.setCourseName(txtAttendanceCourseName.getText());
				a.setDate(dtpAttendanceDate.getValue());
				if (rbAttendance2.isSelected()){
					a.setAttendance("ob.");
				}
				if (rbAbsenceUnexcused2.isSelected()){
					a.setAttendance("nob.u");
				}
				if (rbAbsenceJustified2.isSelected()){
					a.setAttendance("nob.n");
				}
				if (rbLate2.isSelected()){
					a.setAttendance("s.");
				}
				if (rbPrepared2.isSelected()){
					a.setPreparation("p.");
				}
				if (rbUnprepared2.isSelected()){
					a.setPreparation("np.");
				}
				a.setTeacher(adminLogin);

				personService.updateAttendance(a);

				attendanceTableView.getItems().set(index,  a);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void attendanceOnUpdate3(ActionEvent event) {
		int index = attendanceTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtAttendanceCourseName.getText() == null || txtAttendanceCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: nazwa przemiotu!\n";
		}

		if(dtpAttendanceDate.getValue() == null) {
			errorMessage += "Nieprawidłowo wypełnione pole: data!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Attendance a = new Attendance();
				a.setId(Long.valueOf(txtAttendanceID.getText()));
				a.setStudentName("Jan Kowalski");
				a.setCourseName(txtAttendanceCourseName.getText());
				a.setDate(dtpAttendanceDate.getValue());
				if (rbAttendance3.isSelected()){
					a.setAttendance("ob.");
				}
				if (rbAbsenceUnexcused3.isSelected()){
					a.setAttendance("nob.u");
				}
				if (rbAbsenceJustified3.isSelected()){
					a.setAttendance("nob.n");
				}
				if (rbLate3.isSelected()){
					a.setAttendance("s.");
				}
				if (rbPrepared3.isSelected()){
					a.setPreparation("p.");
				}
				if (rbUnprepared3.isSelected()){
					a.setPreparation("np.");
				}
				a.setTeacher(adminLogin);

				personService.updateAttendance(a);

				attendanceTableView.getItems().set(index,  a);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void attendanceOnDelete(ActionEvent event) {
		try {
			Attendance a = attendanceTableView.getSelectionModel().getSelectedItem();

			if(a == null) {
				return;
			}

			personService.deleteAttendance(a.getId());

			attendanceTableView.getItems().remove(a);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void attendanceOnRefresh(ActionEvent event) {
		try {
			attendanceTableView.getItems().setAll(personService.getAllAttendance());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

    //============================================================================================================================================//
	//=============================================================MARKS==========================================================================//
	//============================================================================================================================================//
	@FXML
	public void marksOnInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtMarksStudentName.getText() == null || txtMarksStudentName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko ucznia!\n";
		}

		if(txtMarksCourseName.getText() == null || txtMarksCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: przedmiot!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Marks m = new Marks();
				m.setStudentName(txtMarksStudentName.getText());
				m.setCourseName(txtMarksCourseName.getText());
				m.setTest1(txtMarksTest1.getText());
				m.setTest2(txtMarksTest2.getText());
				m.setQuiz(txtMarksQuiz.getText());
				m.setOral(txtMarksOral.getText());
				m.setPaper(txtMarksPaper.getText());
				m.setActivity(txtMarksActivity.getText());

				m = personService.insertMarks(m);

				marksTableView.getItems().add(m);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void marksOnUpdate(ActionEvent event) {
		int index = marksTableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtMarksStudentName.getText() == null || txtMarksStudentName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: imię i nazwisko ucznia!\n";
		}

		if(txtMarksCourseName.getText() == null || txtMarksCourseName.getText().isEmpty()) {
			errorMessage += "Nieprawidłowo wypełnione pole: przedmiot!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Marks m = new Marks();
				m.setId(Long.valueOf(txtMarksID.getText()));
				m.setStudentName(txtMarksStudentName.getText());
				m.setCourseName(txtMarksCourseName.getText());
				m.setTest1(txtMarksTest1.getText());
				m.setTest2(txtMarksTest2.getText());
				m.setQuiz(txtMarksQuiz.getText());
				m.setOral(txtMarksOral.getText());
				m.setPaper(txtMarksPaper.getText());
				m.setActivity(txtMarksActivity.getText());

				personService.updateMarks(m);

				marksTableView.getItems().set(index,  m);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	public void marksOnDelete(ActionEvent event) {
		try {
			Marks m = marksTableView.getSelectionModel().getSelectedItem();

			if(m == null) {
				return;
			}

			personService.deleteMarks(m.getId());

			marksTableView.getItems().remove(m);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void marksOnRefresh(ActionEvent event) {
		try {
			marksTableView.getItems().setAll(personService.getAllMarks());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	//============================================================================================================================================//
	//============================================================================================================================================//
	//============================================================================================================================================//


	// Event Listener on Button.onAction
	@FXML
	public void onInsert(ActionEvent event) {

		String errorMessage = "";

		if(txtFirstName.getText() == null || txtFirstName.getText().isEmpty()) {
			errorMessage += "No valid first name!\n";
		}

		if(dtpBirthDate.getValue() == null) {
			errorMessage += "No valid birth date!\n";
		}

		if(errorMessage.length() == 0) {
			try {
				Person person = new Person();
				person.setFirstName(txtFirstName.getText());
				person.setLastName(txtLastName.getText());
				person.setBirthDate(dtpBirthDate.getValue());

				person = personService.insertPerson(person);

				tableView.getItems().add(person);

				clearField();

			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void onUpdate(ActionEvent event) {
		int index = tableView.getSelectionModel().getSelectedIndex();

		String errorMessage = "";

		if(txtFirstName.getText() == null || txtFirstName.getText().isEmpty()) {
			errorMessage += "No valid first name!\n";
		}

		if(dtpBirthDate.getValue() == null) {
			errorMessage += "No valid birth date!\n";
		}

		if (errorMessage.length() == 0) {
			try {
				Person person = new Person();
				person.setId(Long.valueOf(txtId.getText()));
				person.setFirstName(txtFirstName.getText());
				person.setLastName(txtLastName.getText());
				person.setBirthDate(dtpBirthDate.getValue());

				personService.updatePerson(person);

				tableView.getItems().set(index,  person);

				clearField();
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.setTitle("Nieprawidłowo wypełnione pola!");
			alert.setHeaderText("Proszę poprawić nieprawidłowo wypełnione pola!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void onDelete(ActionEvent event) {
		try {
			Person person = tableView.getSelectionModel().getSelectedItem();

			if(person == null) {
				return;
			}

			personService.deletePerson(person.getId());

			tableView.getItems().remove(person);

			clearField();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void onRefresh(ActionEvent event) {
		try {
			tableView.getItems().setAll(personService.getAllPerson());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
		clearField();
	}

	public void setMain(RMIClient main, LoginFormController cont) {
		this.main = main;
		this.personService = main.getPersonService();
		this.adminLogin = cont.getAdminLogin();
		try {
			tableView.getItems().setAll(personService.getAllPerson());
			personalDataTableView.getItems().setAll(personService.getAllPersonalData());
			weekTimetableTableView.getItems().setAll(personService.getAllWeekTimetable());
			programTableView.getItems().setAll(personService.getAllProgram());
			booksTableView.getItems().setAll(personService.getAllBooks());
			subjectsTableView.getItems().setAll(personService.getAllSubjects());
			tripsTableView.getItems().setAll(personService.getAllTrips());
			eventsTableView.getItems().setAll(personService.getAllEvents());
			additionalBehaviourTableView.getItems().setAll(personService.getAllAdditionalBehaviour());
			teachersTableView.getItems().setAll(personService.getAllTeachers());
			attendanceTableView.getItems().setAll(personService.getAllAttendance());
			marksTableView.getItems().setAll(personService.getAllMarks());
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}

	private void clearField() {
		txtId.setText("");
		txtFirstName.setText("");;
		txtLastName.setText("");
		dtpBirthDate.setValue(null);

		txtPersonalDataID.setText("");
		txtPersonalDataRegisterationNumber.setText("");
		txtPersonalDataStudentName.setText("");
		dtpPersonalDataBirthDate.setValue(null);
		txtPersonalDataBirthLocation.setText("");
		txtPersonalDataPESEL.setText("");
		txtPersonalDataFatherName.setText("");
		txtPersonalDataMotherName.setText("");
		txtPersonalDataParentsAddress.setText("");
		txtPersonalDataParentsMobileNumber.setText("");

		txtWeekTimetableID.setText("");
		txtWeekTimetableFrom.setText("");
		txtWeekTimetableTo.setText("");
		txtWeekTimetableMonday.setText("");
		txtWeekTimetableTeusday.setText("");
		txtWeekTimetableWednesday.setText("");
		txtWeekTimetableThursday.setText("");
		txtWeekTimetableFriday.setText("");

		txtProgramID.setText("");
		txtProgramCourseName.setText("");
		txtProgramCourseTitle.setText("");
		txtProgramTeacher.setText("");
		txtProgramComments.setText("");

		txtBooksID.setText("");
		txtBooksCourseName.setText("");
		txtBooksBookTitle.setText("");
		txtBooksAuthors.setText("");
		txtBooksPublishingHouse.setText("");
		txtBooksComments.setText("");

		txtSubjectsID.setText("");
		dtpSubjectsDate.setValue(null);
		txtSubjectsNumber.setText("");
		txtSubjectsCourseName.setText("");
		txtSubjectsSubject.setText("");
		txtSubjectsPresent.setText("");
		txtSubjectsAbsent.setText("");
		txtSubjectsTeacher.setText("");

		txtTripsID.setText("");
		dtpTripsBegginingDate.setValue(null);
		dtpTripsEndDate.setValue(null);
		txtTripsDuration.setText("");
		txtTripsParticipants.setText("");
		txtTripsDestination.setText("");
		txtTripsTeacher.setText("");

		txtEventsID.setText("");
		dtpEventsDate.setValue(null);
		txtEventsInfo.setText("");

		txtAdditionalBehaviourID.setText("");
		txtAdditionalBehaviourStudentName.setText("");
		dtpAdditionalBehaviourDate.setValue(null);
		txtAdditionalBehaviourKind.setText("");
		txtAdditionalBehaviourInfo.setText("");
		txtAdditionalBehaviourTeacher.setText("");

		txtTeachersID.setText("");
		txtTeachersTeacherName.setText("");
		txtTeachersCourseName.setText("");

		txtAttendanceID.setText("");
		txtAttendanceCourseName.setText("");
		dtpAttendanceDate.setValue(null);
		txtAttendanceTeacher.setText("");

		txtMarksID.setText("");
		txtMarksStudentName.setText("");
		txtMarksCourseName.setText("");
		txtMarksTest1.setText("");
		txtMarksTest2.setText("");
		txtMarksQuiz.setText("");
		txtMarksOral.setText("");
		txtMarksPaper.setText("");
		txtMarksActivity.setText("");
	}
}
