package networking.rmi.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.rmi.api.Accounts;
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
import networking.rmi.client.AdminMenuController;

public class ServiceImpl extends UnicastRemoteObject implements Service {

	public ServiceImpl() throws RemoteException {

	}

	//*************************************************************METHODS************************************************************************//
	public String accountLogin(Accounts a) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request accountLogin() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

        PreparedStatement statement = null;

        String sql = "select account_role from accounts where account_login = ? and account_password = ?";

        try{
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, a.getLogin());
            statement.setString(2, a.getPassword());

			ResultSet result = statement.executeQuery();

			String role = null;
			if(result.next()) {
				role = result.getString("account_role");
			}

			result.close();
			System.out.println("[successful]");
			return role;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}


    //============================================================================================================================================//
	//=============================================================PERSONAL DATA==================================================================//
	//============================================================================================================================================//
	@Override
	public PersonalData insertPersonalData(PersonalData pd) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertPersonalData() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into personalData(personalData_id, personalData_registeration_number, personalData_student_name"
					+ ", personalData_birth_date, personalData_birth_location, personalData_PESEL, personalData_father_name"
					+ ", personalData_mother_name, personalData_parents_adress, personalData_parents_mobile_number)"
					+ " values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setLong(1, pd.getRegisterationNumber());
			statement.setString(2,  pd.getStudentName());
			statement.setDate(3,  Date.valueOf(pd.getBirthDate().toString()));
			statement.setString(4,  pd.getBirthLocation());
			statement.setLong(5, pd.getPESEL());
			statement.setString(6,  pd.getFatherName());
			statement.setString(7,  pd.getMotherName());
			statement.setString(8,  pd.getParentsAddress());
			statement.setString(9,  pd.getParentsMobileNumber());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				pd.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return pd;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updatePersonalData(PersonalData pd) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updatePersonalData() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update personalData set personalData_registeration_number = ?, personalData_student_name = ?"
				+ ", personalData_birth_date = ?, personalData_birth_location = ?, personalData_PESEL = ?"
				+ ", personalData_father_name = ?, personalData_mother_name = ?, personalData_parents_adress = ?"
				+ ", personalData_parents_mobile_number = ?"
				+ " where personalData_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1, pd.getRegisterationNumber());
			statement.setString(2,  pd.getStudentName());
			statement.setDate(3,  Date.valueOf(pd.getBirthDate().toString()));
			statement.setString(4,  pd.getBirthLocation());
			statement.setLong(5, pd.getPESEL());
			statement.setString(6,  pd.getFatherName());
			statement.setString(7,  pd.getMotherName());
			statement.setString(8,  pd.getParentsAddress());
			statement.setString(9,  pd.getParentsMobileNumber());
			statement.setLong(10, pd.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deletePersonalData(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deletePersonalData() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from personalData where personalData_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public PersonalData getPersonalDataById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getPersonalDataById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from personalData where personalData_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			PersonalData pd = null;
			if(result.next()) {
				pd = new PersonalData();
				pd.setId(result.getLong("personalData_id"));
				pd.setRegisterationNumber(result.getLong("personalData_registeration_number"));
				pd.setStudentName(result.getString("personalData_student_name"));
				pd.setBirthDate(LocalDate.parse(result.getDate("personalData_birth_date").toString()));
				pd.setBirthLocation(result.getString("personalData_birth_location"));
				pd.setPESEL(result.getLong("personalData_PESEL"));
				pd.setFatherName(result.getString("personalData_father_name"));
				pd.setMotherName(result.getString("personalData_mother_name"));
				pd.setParentsAddress(result.getString("personalData_parents_adress"));
				pd.setParentsMobileNumber(result.getString("personalData_parents_mobile_number"));
			}

			result.close();
			System.out.println("[successful]");
			return pd;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<PersonalData> getAllPersonalData() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllPersonalData() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from personalData";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<PersonalData> list = new ArrayList<PersonalData>();

			while(result.next()) {
				PersonalData pd = new PersonalData();
				pd.setId(result.getLong("personalData_id"));
				pd.setRegisterationNumber(result.getLong("personalData_registeration_number"));
				pd.setStudentName(result.getString("personalData_student_name"));
				pd.setBirthDate(LocalDate.parse(result.getDate("personalData_birth_date").toString()));
				pd.setBirthLocation(result.getString("personalData_birth_location"));
				pd.setPESEL(result.getLong("personalData_PESEL"));
				pd.setFatherName(result.getString("personalData_father_name"));
				pd.setMotherName(result.getString("personalData_mother_name"));
				pd.setParentsAddress(result.getString("personalData_parents_adress"));
				pd.setParentsMobileNumber(result.getString("personalData_parents_mobile_number"));
				list.add(pd);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================WEEK TIMETABLE=================================================================//
	//============================================================================================================================================//
	@Override
	public WeekTimetable insertWeekTimetable(WeekTimetable wt) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertWeekTimetable() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into weekTimetable(weekTimetable_id, weekTimetable_from, weekTimetable_to"
					+ ", weekTimetable_monday, weekTimetable_teusday, weekTimetable_wednesday, weekTimetable_thursday"
					+ ", weekTimetable_friday)"
					+ " values (null, ?, ?, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setString(1,  wt.getFrom());
			statement.setString(2,  wt.getTo());
			statement.setString(3,  wt.getMonday());
			statement.setString(4,  wt.getTeusday());
			statement.setString(5,  wt.getWednesday());
			statement.setString(6,  wt.getThursday());
			statement.setString(7,  wt.getFriday());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				wt.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return wt;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateWeekTimetable(WeekTimetable wt) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateWeekTimetable() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update weekTimetable set weekTimetable_from = ?, weekTimetable_to = ?"
				+ ", weekTimetable_monday = ?, weekTimetable_teusday = ?, weekTimetable_wednesday = ?"
				+ ", weekTimetable_thursday = ?, weekTimetable_friday = ?"
				+ " where weekTimetable_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1,  wt.getFrom());
			statement.setString(2,  wt.getTo());
			statement.setString(3,  wt.getMonday());
			statement.setString(4,  wt.getTeusday());
			statement.setString(5,  wt.getWednesday());
			statement.setString(6,  wt.getThursday());
			statement.setString(7,  wt.getFriday());
			statement.setLong(8, wt.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteWeekTimetable(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteWeekTimetable() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from weekTimetable where weekTimetable_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public WeekTimetable getWeekTimetableById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getWeekTimetableById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from weekTimetable where WeekTimetable_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			WeekTimetable wt = null;
			if(result.next()) {
				wt = new WeekTimetable();
				wt.setId(result.getLong("weekTimetable_id"));
				wt.setFrom(result.getString("weekTimetable_from"));
				wt.setTo(result.getString("weekTimetable_to"));
				wt.setMonday(result.getString("weekTimetable_monday"));
				wt.setTeusday(result.getString("weekTimetable_teusday"));
				wt.setWednesday(result.getString("weekTimetable_wednesday"));
				wt.setThursday(result.getString("weekTimetable_thursday"));
				wt.setFriday(result.getString("weekTimetable_friday"));
			}

			result.close();
			System.out.println("[successful]");
			return wt;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<WeekTimetable> getAllWeekTimetable() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllWeekTimetable() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from weekTimetable";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<WeekTimetable> list = new ArrayList<WeekTimetable>();

			while(result.next()) {
				WeekTimetable wt = new WeekTimetable();
				wt.setId(result.getLong("weekTimetable_id"));
				wt.setFrom(result.getString("weekTimetable_from"));
				wt.setTo(result.getString("weekTimetable_to"));
				wt.setMonday(result.getString("weekTimetable_monday"));
				wt.setTeusday(result.getString("weekTimetable_teusday"));
				wt.setWednesday(result.getString("weekTimetable_wednesday"));
				wt.setThursday(result.getString("weekTimetable_thursday"));
				wt.setFriday(result.getString("weekTimetable_friday"));
				list.add(wt);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================PROGRAM========================================================================//
	//============================================================================================================================================//
	@Override
	public Program insertProgram(Program prog) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertProgram() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into program(program_id, program_course_name, program_course_title"
					+ ", program_teacher, program_comments)"
					+ " values(null, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setString(1,  prog.getCourseName());
			statement.setString(2,  prog.getCourseTitle());
			statement.setString(3,  prog.getTeacher());
			statement.setString(4,  prog.getComments());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				prog.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return prog;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateProgram(Program prog) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateProgram() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update program set program_course_name = ?, program_course_title = ?"
				+ ", program_teacher = ?, program_comments = ?"
				+ " where program_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1,  prog.getCourseName());
			statement.setString(2,  prog.getCourseTitle());
			statement.setString(3,  prog.getTeacher());
			statement.setString(4,  prog.getComments());
			statement.setLong(5, prog.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteProgram(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteProgram() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from program where program_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Program getProgramById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getProgramById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from program where program_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Program prog = null;
			if(result.next()) {
				prog = new Program();
				prog.setId(result.getLong("program_id"));
				prog.setCourseName(result.getString("program_course_name"));
				prog.setCourseTitle(result.getString("program_course_title"));
				prog.setTeacher(result.getString("program_teacher"));
				prog.setComments(result.getString("program_comments"));
			}

			result.close();
			System.out.println("[successful]");
			return prog;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Program> getAllProgram() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllProgram() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from program";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Program> list = new ArrayList<Program>();

			while(result.next()) {
				Program prog = new Program();
				prog.setId(result.getLong("program_id"));
				prog.setCourseName(result.getString("program_course_name"));
				prog.setCourseTitle(result.getString("program_course_title"));
				prog.setTeacher(result.getString("program_teacher"));
				prog.setComments(result.getString("program_comments"));
				list.add(prog);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================BOOKS==========================================================================//
	//============================================================================================================================================//
	@Override
	public Books insertBooks(Books b) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertBooks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into books(books_id, books_course_name, books_book_title"
					+ ", books_authors, books_publishing_house, books_comments)"
					+ " values (null, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setString(1,  b.getCourseName());
			statement.setString(2,  b.getBookTitle());
			statement.setString(3,  b.getAuthors());
			statement.setString(4,  b.getPublishingHouse());
			statement.setString(5,  b.getComments());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				b.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return b;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateBooks(Books b) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateBooks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update books set books_course_name = ?, books_book_title = ?"
				+ ", books_authors = ?, books_publishing_house = ?, books_comments = ?"
				+ " where books_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1,  b.getCourseName());
			statement.setString(2,  b.getBookTitle());
			statement.setString(3,  b.getAuthors());
			statement.setString(4,  b.getPublishingHouse());
			statement.setString(5,  b.getComments());
			statement.setLong(6, b.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteBooks(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteBooks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from books where books_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Books getBooksById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getBooksById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from books where books_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Books b = null;
			if(result.next()) {
				b = new Books();
				b.setId(result.getLong("books_id"));
				b.setCourseName(result.getString("books_course_name"));
				b.setBookTitle(result.getString("books_book_title"));
				b.setAuthors(result.getString("books_authors"));
				b.setPublishingHouse(result.getString("books_publishing_house"));
				b.setComments(result.getString("books_comments"));
			}

			result.close();
			System.out.println("[successful]");
			return b;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Books> getAllBooks() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllBooks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from books";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Books> list = new ArrayList<Books>();

			while(result.next()) {
				Books b = new Books();
				b.setId(result.getLong("books_id"));
				b.setCourseName(result.getString("books_course_name"));
				b.setBookTitle(result.getString("books_book_title"));
				b.setAuthors(result.getString("books_authors"));
				b.setPublishingHouse(result.getString("books_publishing_house"));
				b.setComments(result.getString("books_comments"));
				list.add(b);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================SUBJECTS=======================================================================//
	//============================================================================================================================================//
	@Override
	public Subjects insertSubjects(Subjects s) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertSubjects() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into subjects(subjects_id, subjects_date, subjects_number"
					+ ", subjects_course_name, subjects_subject, subjects_present, subjects_absent"
					+ ", subjects_teacher)"
					+ " values (null, ?, ?, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setDate(1,  Date.valueOf(s.getDate().toString()));
			statement.setLong(2, s.getNumber());
			statement.setString(3,  s.getCourseName());
			statement.setString(4,  s.getSubject());
			statement.setLong(5, s.getPresent());
			statement.setLong(6, s.getAbsent());
			statement.setString(7, s.getTeacher());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				s.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return s;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateSubjects(Subjects s) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateSubjects() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update subjects set subjects_date = ?, subjects_number = ?"
				+ ", subjects_course_name = ?, subjects_subject = ?, subjects_present = ?"
				+ ", subjects_absent = ?, subjects_teacher = ?"
				+ " where subjects_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setDate(1,  Date.valueOf(s.getDate().toString()));
			statement.setLong(2, s.getNumber());
			statement.setString(3,  s.getCourseName());
			statement.setString(4,  s.getSubject());
			statement.setLong(5, s.getPresent());
			statement.setLong(6, s.getAbsent());
			statement.setString(7, s.getTeacher());
			statement.setLong(8, s.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteSubjects(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteSubjects() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from subjects where subjects_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Subjects getSubjectsById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getSubjectsById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from subjects where subjects_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Subjects s = null;
			if(result.next()) {
				s = new Subjects();
				s.setId(result.getLong("subjects_id"));
				s.setDate(LocalDate.parse(result.getDate("subjects_date").toString()));
				s.setNumber(result.getLong("subjects_number"));
				s.setCourseName(result.getString("subjects_course_name"));
				s.setSubject(result.getString("subjects_subject"));
				s.setPresent(result.getLong("subjects_present"));
				s.setAbsent(result.getLong("subjects_absent"));
				s.setTeacher(result.getString("subjects_teacher"));
			}

			result.close();
			System.out.println("[successful]");
			return s;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Subjects> getAllSubjects() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllSubjects() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from subjects";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Subjects> list = new ArrayList<Subjects>();

			while(result.next()) {
				Subjects s = new Subjects();
				s.setId(result.getLong("subjects_id"));
				s.setDate(LocalDate.parse(result.getDate("subjects_date").toString()));
				s.setNumber(result.getLong("subjects_number"));
				s.setCourseName(result.getString("subjects_course_name"));
				s.setSubject(result.getString("subjects_subject"));
				s.setPresent(result.getLong("subjects_present"));
				s.setAbsent(result.getLong("subjects_absent"));
				s.setTeacher(result.getString("subjects_teacher"));
				list.add(s);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================TRIPS==========================================================================//
	//============================================================================================================================================//
	@Override
	public Trips insertTrips(Trips t) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertTrips() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into trips(trips_id, trips_beggining_date, trips_end_date"
					+ ", trips_duration, trips_participants, trips_destination, trips_teacher)"
					+ " values (null, ?, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setDate(1,  Date.valueOf(t.getBegginingDate().toString()));
			statement.setDate(2,  Date.valueOf(t.getEndDate().toString()));
			statement.setLong(3, t.getDuration());
			statement.setLong(4, t.getParticipants());
			statement.setString(5,  t.getDestination());
			statement.setString(6,  t.getTeacher());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				t.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return t;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateTrips(Trips t) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateTrips() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update trips set trpis_beggining_date = ?, trips_end_date = ?"
				+ ", trips_duration = ?, trips_participants = ?, trips_destination = ?"
				+ ", trips_teacher = ?"
				+ " where trips_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setDate(1,  Date.valueOf(t.getBegginingDate().toString()));
			statement.setDate(2,  Date.valueOf(t.getEndDate().toString()));
			statement.setLong(3, t.getDuration());
			statement.setLong(4, t.getParticipants());
			statement.setString(5,  t.getDestination());
			statement.setString(6,  t.getTeacher());
			statement.setLong(7, t.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteTrips(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteTrips() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from trips where trips_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Trips getTripsById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getTripsById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from trips where trips_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Trips t = null;
			if(result.next()) {
				t = new Trips();
				t.setId(result.getLong("trips_id"));
				t.setBegginingDate(LocalDate.parse(result.getDate("trpis_beggining_date").toString()));
				t.setEndDate(LocalDate.parse(result.getDate("trips_end_date").toString()));
				t.setDuration(result.getLong("trips_duration"));
				t.setParticipants(result.getLong("trips_participants"));
				t.setDestination(result.getString("trips_destination"));
				t.setTeacher(result.getString("trips_teacher"));
			}

			result.close();
			System.out.println("[successful]");
			return t;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Trips> getAllTrips() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllTrips() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from trips";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Trips> list = new ArrayList<Trips>();

			while(result.next()) {
				Trips t = new Trips();
				t.setId(result.getLong("trips_id"));
				t.setBegginingDate(LocalDate.parse(result.getDate("trpis_beggining_date").toString()));
				t.setEndDate(LocalDate.parse(result.getDate("trips_end_date").toString()));
				t.setDuration(result.getLong("trips_duration"));
				t.setParticipants(result.getLong("trips_participants"));
				t.setDestination(result.getString("trips_destination"));
				t.setTeacher(result.getString("trips_teacher"));
				list.add(t);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================EVENTS=========================================================================//
	//============================================================================================================================================//
	@Override
	public Events insertEvents(Events e) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertEvents() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into events(events_id, events_date, events_info)"
					+ " values (null, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setDate(1,  Date.valueOf(e.getDate().toString()));
			statement.setString(2,  e.getInfo());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				e.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return e;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateEvents(Events e) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateEvents() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update events set events_date = ?, events_info = ?"
				+ " where events_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setDate(1,  Date.valueOf(e.getDate().toString()));
			statement.setString(2,  e.getInfo());
			statement.setLong(3, e.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteEvents(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteEvents() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from events where events_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Events getEventsById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getEventsById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from events where events_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Events e = null;
			if(result.next()) {
				e = new Events();
				e.setId(result.getLong("events_id"));
				e.setDate(LocalDate.parse(result.getDate("events_date").toString()));
				e.setInfo(result.getString("events_info"));
			}

			result.close();
			System.out.println("[successful]");
			return e;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Events> getAllEvents() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllEvents() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from events";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Events> list = new ArrayList<Events>();

			while(result.next()) {
				Events e = new Events();
				e.setId(result.getLong("events_id"));
				e.setDate(LocalDate.parse(result.getDate("events_date").toString()));
				e.setInfo(result.getString("events_info"));
				list.add(e);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================ADDITIONAL BEHAVIOUR===========================================================//
	//============================================================================================================================================//
	@Override
	public AdditionalBehaviour insertAdditionalBehaviour(AdditionalBehaviour ab) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertAdditionalBehaviour() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into additionalBehaviour(additionalBehaviour_id, additionalBehaviour_student_name"
					+ ", additionalBehaviour_date, additionalBehaviour_kind, additionalBehaviour_info"
					+ ", additionalBehaviour_teacher)"
					+ " values (null, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setString(1,  ab.getStudentName());
			statement.setDate(2,  Date.valueOf(ab.getDate().toString()));
			statement.setString(3,  ab.getKind());
			statement.setString(4,  ab.getInfo());
			statement.setString(5,  ab.getTeacher());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				ab.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return ab;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateAdditionalBehaviour(AdditionalBehaviour ab) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateAdditionalBehaviour() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update additionalBehaviour set additionalBehaviour_student_name = ?, additionalBehaviour_date = ?"
				+ ", additionalBehaviour_kind = ?, additionalBehaviour_info = ?, additionalBehaviour_teacher = ?"
				+ " where additionalBehaviour_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1,  ab.getStudentName());
			statement.setDate(2,  Date.valueOf(ab.getDate().toString()));
			statement.setString(3,  ab.getKind());
			statement.setString(4,  ab.getInfo());
			statement.setString(5,  ab.getTeacher());
			statement.setLong(6, ab.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteAdditionalBehaviour(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteAdditionalBehaviour() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from additionalBehaviour where additionalBehaviour_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public AdditionalBehaviour getAdditionalBehaviourById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAdditionalBehaviourById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from additionalBehaviour where additionalBehaviour_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			AdditionalBehaviour ab = null;
			if(result.next()) {
				ab = new AdditionalBehaviour();
				ab.setId(result.getLong("additionalBehaviour_id"));
				ab.setStudentName(result.getString("additionalBehaviour_student_name"));
				ab.setDate(LocalDate.parse(result.getDate("additionalBehaviour_date").toString()));
				ab.setKind(result.getString("additionalBehaviour_kind"));
				ab.setInfo(result.getString("additionalBehaviour_info"));
				ab.setTeacher(result.getString("additionalBehaviour_teacher"));
			}

			result.close();
			System.out.println("[successful]");
			return ab;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<AdditionalBehaviour> getAllAdditionalBehaviour() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllAdditionalBehaviour() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from additionalBehaviour";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<AdditionalBehaviour> list = new ArrayList<AdditionalBehaviour>();

			while(result.next()) {
				AdditionalBehaviour ab = new AdditionalBehaviour();
				ab.setId(result.getLong("additionalBehaviour_id"));
				ab.setStudentName(result.getString("additionalBehaviour_student_name"));
				ab.setDate(LocalDate.parse(result.getDate("additionalBehaviour_date").toString()));
				ab.setKind(result.getString("additionalBehaviour_kind"));
				ab.setInfo(result.getString("additionalBehaviour_info"));
				ab.setTeacher(result.getString("additionalBehaviour_teacher"));
				list.add(ab);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================TEACHERS=======================================================================//
	//============================================================================================================================================//
	@Override
	public Teachers insertTeachers(Teachers t) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertTeachers() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into teachers(teachers_id, teachers_teacher_name, teachers_course_name)"
					+ " values (null, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, pd.getId());
			statement.setString(1,  t.getTeacherName());
			statement.setString(2,  t.getCourseName());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				t.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return t;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateTeachers(Teachers t) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateTeachers() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update teachers set teachers_teacher_name = ?, teachers_course_name = ?"
				+ " where teachers_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1,  t.getTeacherName());
			statement.setString(2,  t.getCourseName());
			statement.setLong(3, t.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteTeachers(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteTeachers() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from teachers where teachers_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Teachers getTeachersById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getTeachersById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from teachers where teachers_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Teachers t = null;
			if(result.next()) {
				t = new Teachers();
				t.setId(result.getLong("teachers_id"));
				t.setTeacherName(result.getString("teachers_teacher_name"));
				t.setCourseName(result.getString("teachers_course_name"));
			}

			result.close();
			System.out.println("[successful]");
			return t;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Teachers> getAllTeachers() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllTeachers() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from teachers";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Teachers> list = new ArrayList<Teachers>();

			while(result.next()) {
				Teachers t = new Teachers();
				t.setId(result.getLong("teachers_id"));
				t.setTeacherName(result.getString("teachers_teacher_name"));
				t.setCourseName(result.getString("teachers_course_name"));
				list.add(t);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================ATTENDANCE=====================================================================//
	//============================================================================================================================================//
	@Override
	public Attendance insertAttendance(Attendance a) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertAttendance() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into attendance(attendance_id, attendance_student_name, attendance_course_name"
					+ ", attendance_date, attendance_attendance, attendance_preparation, attendance_teacher)"
					+ " values (null, ?, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,  a.getStudentName());
			statement.setString(2,  a.getCourseName());
			statement.setDate(3,  Date.valueOf(a.getDate().toString()));
			statement.setString(4,  a.getAttendance());
			statement.setString(5,  a.getPreparation());
			statement.setString(6,  a.getTeacher());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				a.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return a;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateAttendance(Attendance a) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateAttendance() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update attendance set attendance_student_name = ?, attendance_course_name = ?"
				+ ", attendance_date = ?, attendance_attendance = ?, attendance_preparation = ?, attendance_teacher = ?"
				+ " where attendance_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1,  a.getStudentName());
			statement.setString(2,  a.getCourseName());
			statement.setDate(3,  Date.valueOf(a.getDate().toString()));
			statement.setString(4,  a.getAttendance());
			statement.setString(5,  a.getPreparation());
			statement.setString(6,  a.getTeacher());
			statement.setLong(7, a.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteAttendance(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteAttendance() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from attendance where attendance_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Attendance getAttendanceById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAttendanceById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from attendance where attendance_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Attendance a = null;
			if(result.next()) {
				a = new Attendance();
				a.setId(result.getLong("attendance_id"));
				a.setStudentName(result.getString("attendance_student_name"));
				a.setCourseName(result.getString("attendance_course_name"));
				a.setDate(LocalDate.parse(result.getDate("attendance_date").toString()));
				a.setAttendance(result.getString("attendance_attendance"));
				a.setPreparation(result.getString("attendance_preparation"));
				a.setTeacher(result.getString("attendance_teacher"));
			}

			result.close();
			System.out.println("[successful]");
			return a;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Attendance> getAllAttendance() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllAttendance() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from attendance";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Attendance> list = new ArrayList<Attendance>();

			while(result.next()) {
				Attendance a = new Attendance();
				a.setId(result.getLong("attendance_id"));
				a.setStudentName(result.getString("attendance_student_name"));
				a.setCourseName(result.getString("attendance_course_name"));
				a.setDate(LocalDate.parse(result.getDate("attendance_date").toString()));
				a.setAttendance(result.getString("attendance_attendance"));
				a.setPreparation(result.getString("attendance_preparation"));
				a.setTeacher(result.getString("attendance_teacher"));
				list.add(a);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//=============================================================MARKS==========================================================================//
	//============================================================================================================================================//
	@Override
	public Marks insertMarks(Marks m) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request insertMarks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into marks(marks_id, marks_student_name, marks_course_name"
					+ ", marks_test1, marks_test2, marks_quiz, marks_oral, marks_paper, marks_activity)"
					+ " values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,  m.getStudentName());
			statement.setString(2,  m.getCourseName());
			statement.setString(3,  m.getTest1());
			statement.setString(4,  m.getTest2());
			statement.setString(5,  m.getQuiz());
			statement.setString(6,  m.getOral());
			statement.setString(7,  m.getPaper());
			statement.setString(8,  m.getActivity());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				m.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return m;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateMarks(Marks m) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request updateMarks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update marks set marks_student_name = ?, marks_course_name = ?, marks_test1, marks_test2"
				+ ", marks_quiz = ?, marks_oral = ?, marks_paper = ?, marks_activity = ?"
				+ " where marks_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1,  m.getStudentName());
			statement.setString(2,  m.getCourseName());
			statement.setString(3,  m.getTest1());
			statement.setString(4,  m.getTest2());
			statement.setString(5,  m.getQuiz());
			statement.setString(6,  m.getOral());
			statement.setString(7,  m.getPaper());
			statement.setString(8,  m.getActivity());
			statement.setLong(9, m.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteMarks(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request deleteMarks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from marks where marks_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Marks getMarksById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getMarksById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from marks where marks_id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Marks m = null;
			if(result.next()) {
				m = new Marks();
				m.setId(result.getLong("marks_id"));
				m.setStudentName(result.getString("marks_student_name"));
				m.setCourseName(result.getString("marks_course_name"));
				m.setTest1(result.getString("marks_test1"));
				m.setTest2(result.getString("marks_test2"));
				m.setQuiz(result.getString("marks_quiz"));
				m.setOral(result.getString("marks_oral"));
				m.setPaper(result.getString("marks_paper"));
				m.setActivity(result.getString("marks_activity"));
			}

			result.close();
			System.out.println("[successful]");
			return m;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Marks> getAllMarks() throws RemoteException {
		try {
			System.out.println("\nClient " + getClientHost() + " request getAllMarks() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from marks";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Marks> list = new ArrayList<Marks>();

			while(result.next()) {
				Marks m = new Marks();
				m.setId(result.getLong("marks_id"));
				m.setStudentName(result.getString("marks_student_name"));
				m.setCourseName(result.getString("marks_course_name"));
				m.setTest1(result.getString("marks_test1"));
				m.setTest2(result.getString("marks_test2"));
				m.setQuiz(result.getString("marks_quiz"));
				m.setOral(result.getString("marks_oral"));
				m.setPaper(result.getString("marks_paper"));
				m.setActivity(result.getString("marks_activity"));
				list.add(m);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//============================================================================================================================================//
	//============================================================================================================================================//
	//============================================================================================================================================//
	@Override
	public Person insertPerson(Person person) throws RemoteException {
		try {
			System.out.println("\nClient" + getClientHost() + " request insertPerson() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "insert into person(id, first_name, last_name, birth_date) values (null, ?, ?, ?)";
		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, person.getFirstName());
			statement.setString(2,  person.getLastName());
			statement.setDate(3,  Date.valueOf(person.getBirthDate().toString()));

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) {
				person.setId(result.getLong(1));
			}

			result.close();
			System.out.println("[successful]");
			return person;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updatePerson(Person person) throws RemoteException {
		try {
			System.out.println("\nClient" + getClientHost() + " request updatePerson() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "update person set first_name = ?"
				+ ", last_name = ?, birth_date = ?"
				+ " where id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1, person.getFirstName());
			statement.setString(2,  person.getLastName());
			statement.setDate(3,  Date.valueOf(person.getBirthDate().toString()));
			statement.setLong(4, person.getId());

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deletePerson(Long id) throws RemoteException {
		try {
			System.out.println("\nClient" + getClientHost() + " request deletePerson() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "delete from person where id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			statement.executeUpdate();
			System.out.println("[successful]");

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Person getPersonById(Long id) throws RemoteException {
		try {
			System.out.println("\nClient" + getClientHost() + " request getPersonById() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		PreparedStatement statement = null;

		String sql = "select * from person where id = ?";

		try {
			statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setLong(1,  id);

			ResultSet result = statement.executeQuery();

			Person person = null;
			if(result.next()) {
				person = new Person();
				person.setId(result.getLong("id"));
				person.setFirstName(result.getString("first_name"));
				person.setLastName(result.getString("last_name"));
				person.setBirthDate(LocalDate.parse(result.getDate("birth_date").toString()));
			}

			result.close();
			System.out.println("[successful]");
			return person;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<Person> getAllPerson() throws RemoteException {
		try {
			System.out.println("\nClient" + getClientHost() + " request getAllPerson() method...");
		} catch (ServerNotActiveException ex) {
			ex.printStackTrace();
		}

		Statement statement = null;

		String sql = "select * from person";

		try{
			statement = DatabaseConnection.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);

			List<Person> list = new ArrayList<Person>();

			while(result.next()) {
				Person person = new Person();
				person.setId(result.getLong("id"));
				person.setFirstName(result.getString("first_name"));
				person.setLastName(result.getString("last_name"));
				person.setBirthDate(LocalDate.parse(result.getDate("birth_date").toString()));
				list.add(person);
			}

			result.close();
			System.out.println("[successful]");
			return list;

		} catch (SQLException ex) {
			System.out.println("[failed]");
			ex.printStackTrace();
			return null;
		} finally {
			if(statement != null) {
				try {
					statement. close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			try {
				DatabaseConnection.getConnection().close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

}