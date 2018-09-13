package networking.rmi.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Service extends Remote {
	Person insertPerson(Person person) throws RemoteException;
	void updatePerson(Person person) throws RemoteException;
	void deletePerson(Long id) throws RemoteException;
	Person getPersonById(Long id) throws RemoteException;
	List<Person> getAllPerson() throws RemoteException;

	String accountLogin(Accounts a) throws RemoteException;

	PersonalData insertPersonalData(PersonalData pd) throws RemoteException;
	void updatePersonalData(PersonalData pd) throws RemoteException;
	void deletePersonalData(Long id) throws RemoteException;
	PersonalData getPersonalDataById(Long id) throws RemoteException;
	List<PersonalData> getAllPersonalData() throws RemoteException;

	WeekTimetable insertWeekTimetable(WeekTimetable wt) throws RemoteException;
	void updateWeekTimetable(WeekTimetable wt) throws RemoteException;
	void deleteWeekTimetable(Long id) throws RemoteException;
	WeekTimetable getWeekTimetableById(Long id) throws RemoteException;
	List<WeekTimetable> getAllWeekTimetable() throws RemoteException;

	Program insertProgram(Program prog) throws RemoteException;
	void updateProgram(Program prog) throws RemoteException;
	void deleteProgram(Long id) throws RemoteException;
	Program getProgramById(Long id) throws RemoteException;
	List<Program> getAllProgram() throws RemoteException;

	Books insertBooks(Books b) throws RemoteException;
	void updateBooks(Books b) throws RemoteException;
	void deleteBooks(Long id) throws RemoteException;
	Books getBooksById(Long id) throws RemoteException;
	List<Books> getAllBooks() throws RemoteException;

	Subjects insertSubjects(Subjects s) throws RemoteException;
	void updateSubjects(Subjects s) throws RemoteException;
	void deleteSubjects(Long id) throws RemoteException;
	Subjects getSubjectsById(Long id) throws RemoteException;
	List<Subjects> getAllSubjects() throws RemoteException;

	Trips insertTrips(Trips t) throws RemoteException;
	void updateTrips(Trips t) throws RemoteException;
	void deleteTrips(Long id) throws RemoteException;
	Trips getTripsById(Long id) throws RemoteException;
	List<Trips> getAllTrips() throws RemoteException;

	Events insertEvents(Events e) throws RemoteException;
	void updateEvents(Events e) throws RemoteException;
	void deleteEvents(Long id) throws RemoteException;
	Events getEventsById(Long id) throws RemoteException;
	List<Events> getAllEvents() throws RemoteException;

	AdditionalBehaviour insertAdditionalBehaviour(AdditionalBehaviour ab) throws RemoteException;
	void updateAdditionalBehaviour(AdditionalBehaviour ab) throws RemoteException;
	void deleteAdditionalBehaviour(Long id) throws RemoteException;
	AdditionalBehaviour getAdditionalBehaviourById(Long id) throws RemoteException;
	List<AdditionalBehaviour> getAllAdditionalBehaviour() throws RemoteException;

	Teachers insertTeachers(Teachers t) throws RemoteException;
	void updateTeachers(Teachers t) throws RemoteException;
	void deleteTeachers(Long id) throws RemoteException;
	Teachers getTeachersById(Long id) throws RemoteException;
	List<Teachers> getAllTeachers() throws RemoteException;

	Attendance insertAttendance(Attendance a) throws RemoteException;
	void updateAttendance(Attendance a) throws RemoteException;
	void deleteAttendance(Long id) throws RemoteException;
	Attendance getAttendanceById(Long id) throws RemoteException;
	List<Attendance> getAllAttendance() throws RemoteException;

	Marks insertMarks(Marks m) throws RemoteException;
	void updateMarks(Marks m) throws RemoteException;
	void deleteMarks(Long id) throws RemoteException;
	Marks getMarksById(Long id) throws RemoteException;
	List<Marks> getAllMarks() throws RemoteException;
}
