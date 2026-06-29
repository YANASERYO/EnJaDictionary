package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.EjDictLogic;
import model.EjDict;


@WebServlet("/EjDictServlet")
public class EjDictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String word = request.getParameter("word");
		String explanation = request.getParameter("explanation");
	}
	
	RequestDispatcher dispatcher =
			request.getRequestDispatcher(
					"WEB-INF/jsp/dictionary.jsp");
	dispatcher.forward(request,response);
}