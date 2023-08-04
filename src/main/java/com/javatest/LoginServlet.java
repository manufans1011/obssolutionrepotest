package com.javatest;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatest.model.Student;
import com.javatest.model.StudentDataUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		String dummyUsername = "java01";
		String dummyPassword = "pass@word1";
		
		//Get Request Dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome-page.jsp");
		
		if(userName.equals(dummyUsername) && password.equals(dummyPassword)) {
			List<Student> studentList = StudentDataUtil.getStudents();
			
			Map<String, List<Student>> studlistGrouped =
					studentList.stream().collect(Collectors.groupingBy(w -> w.getDepartment()));
			
			Map<String, List<Student>> sortedStudent = new TreeMap<>(studlistGrouped);

			request.setAttribute("username", userName);
			request.setAttribute("student_list", sortedStudent);
			request.setAttribute("loginSuccess","true");
		}
		else {
			request.setAttribute("loginSuccess","false");
		}
		
		//Forward the request to jsp
		dispatcher.forward(request, response);
	}

}
