// Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private String table;
	private String ttt;
	private static Connection conn = null;
	public void init() throws ServletException{
		// Do required initialization
		table = "";
		ttt = "";
		try {
			conn = DBConnection.connectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ttt = request.getParameter("ttt");
		if(ttt.equals("1")){
			int studentID = Integer.parseInt(request.getParameter("studentID"));
			ResultSet result = null;
			try {
				result = DBOperation.getAllAssignmentByStudentID(studentID, conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.setContentType("text/html");
			// write to table
			table = "";
			table += "<div class=\"container\">" + "<h2>Result</h2>" + "<table class=\"table table-condensed\">"
				    + "<thead>" + "<tr>" + "<th>Assignment</th></tr>"
				    +   "</thead>" + "<tbody>";	    
		    	try {
					while(result.next()){
						table += "<tr>";
						table += "<td>" + result.getString("assignmentName") + "</td>";
						table += "</tr>";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table += "</tbody>" + "</table>" + "</div>";
				request.setAttribute("table", table); // the first is the jsp attribute, the second is the servlet attribute
				getServletContext().getRequestDispatcher("/viewResult.jsp").forward(request, response);
		}else if(ttt.equals("2")){
			int typeID = Integer.parseInt(request.getParameter("typeID"));
			ResultSet result = null;
			try {
				result = DBOperation.getAssignmentByType(typeID, conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.setContentType("text/html");
			// write to table
			table = "";
			table += "<div class=\"container\">" + "<h2>Result</h2>" + "<table class=\"table table-condensed\">"
				    + "<thead>" + "<tr>" + "<th>Assignment</th></tr>"
				    +   "</thead>" + "<tbody>";	    
		    	try {
					while(result.next()){
						table += "<tr>";
						table += "<td>" + result.getString("assignmentName") + "</td>";
						table += "</tr>";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table += "</tbody>" + "</table>" + "</div>";
				request.setAttribute("table", table); // the first is the jsp attribute, the second is the servlet attribute
				getServletContext().getRequestDispatcher("/viewResult.jsp").forward(request, response);
		}else if(ttt.equals("3")){
			int studentID = Integer.parseInt(request.getParameter("studentID"));
			int typeID = Integer.parseInt(request.getParameter("typeID"));
			ResultSet result = null;
			try {
				result = DBOperation.getAssignmentByTypeAndStudentID(typeID, studentID, conn);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			response.setContentType("text/html");
			// write to table
			table = "";
			table += "<div class=\"container\">" + "<h2>Result</h2>" + "<table class=\"table table-condensed\">"
				    + "<thead>" + "<tr>" + "<th>Assignment</th></tr>"
				    +   "</thead>" + "<tbody>";	    
		    	try {
					while(result.next()){
						table += "<tr>";
						table += "<td>" + result.getString("assignmentName") + "</td>";
						table += "</tr>";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table += "</tbody>" + "</table>" + "</div>";
				request.setAttribute("table", table); // the first is the jsp attribute, the second is the servlet attribute
				getServletContext().getRequestDispatcher("/viewResult.jsp").forward(request, response);
		}else if(ttt.equals("4")){
			int studentID = Integer.parseInt(request.getParameter("studentID"));
			double result = 0.0;
			try {
				result = DBOperation.getGPAByStudentID(studentID, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			// write to table
			table = "";
			table += String.valueOf(result);
				request.setAttribute("table", table); // the first is the jsp attribute, the second is the servlet attribute
				getServletContext().getRequestDispatcher("/viewResult.jsp").forward(request, response);
		}else if(ttt.equals("5")){
			int studentID = Integer.parseInt(request.getParameter("studentID"));
			int typeID = Integer.parseInt(request.getParameter("typeID"));
			double result = 0.0;
			try {
				result = DBOperation.getGPAByStudentIDByType(studentID, typeID, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			// write to table
			table = "";
			table += String.valueOf(result);
				request.setAttribute("table", table); // the first is the jsp attribute, the second is the servlet attribute
				getServletContext().getRequestDispatcher("/viewResult.jsp").forward(request, response);
		}else if(ttt.equals("6")){
			int studentID = Integer.parseInt(request.getParameter("studentID"));
			double[] result = new double[2];
			try {
				result = DBOperation.getGPARangeByStudentID(studentID, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			// write to table
			table = "";
			table += "min: " + String.valueOf(result[0]) + " max: " + String.valueOf(result[1]);
				request.setAttribute("table", table); // the first is the jsp attribute, the second is the servlet attribute
				getServletContext().getRequestDispatcher("/viewResult.jsp").forward(request, response);
		}	
	} 
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	public void destroy(){ 
		// do nothing. 
	} 
}