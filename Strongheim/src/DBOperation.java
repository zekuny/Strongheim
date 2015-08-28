import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperation {
	// All assignments by a student
	public static ResultSet getAllAssignmentByStudentID(int id, Connection conn) throws SQLException{
		String sql = "select assignmentName from gradebook where studentID = " + id;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
	// All assignments of a particular type by anyone
	public static ResultSet getAssignmentByType(int tid, Connection conn) throws SQLException{
		String sql = "select assignmentName from gradebook where typeID = " + tid;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
	// All assignments of a particular type by a particular student
	public static ResultSet getAssignmentByTypeAndStudentID(int tid, int id, Connection conn) throws SQLException{
		String sql = "select assignmentName from gradebook where studentID = " + id + " and typeID = " + tid;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
	// The average for a student
	public static double getGPAByStudentID(int id, Connection conn) throws SQLException{
		String sql = "select grade from gradebook where studentID = " + id;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		double gpa = 0.0;
		int count = 0;
		while(result.next()){
			gpa += Double.parseDouble(result.getString("grade"));
			count++;
		}
		return gpa / count;
	}
	// The average for a student by assignment type
	public static double getGPAByStudentIDByType(int id, int tid, Connection conn) throws SQLException{
		String sql = "select grade from gradebook where studentID = " + id + " and typeID = " + tid;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		double gpa = 0.0;
		int count = 0;
		while(result.next()){
			gpa += Double.parseDouble(result.getString("grade"));
			count++;
		}
		return gpa / count;
	}
	
	// The highest and lowest grade for a particular assignment type (highest quiz grade, lowest project grade, etc)
	public static double[] getGPARangeByStudentID(int id, Connection conn) throws SQLException{
		String sql = "select grade from gradebook where studentID = " + id;
		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		double max = 0.0;
		double min = 100.0;
		while(result.next()){
			double grade = Double.parseDouble(result.getString("grade"));
			if(grade > max){
				max = grade;
			}
			if(grade < min){
				min = grade;
			}
		}
		return new double[]{min, max};
	}
}
