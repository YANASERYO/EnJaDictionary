package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.EjDict;
import model.EjDictLogic;

@WebServlet("/EjDictServlet")
public class EjDictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String word = request.getParameter("word");
		String maxCount = request.getParameter("maxCount");

		// 初期表示
		if (word == null && maxCount == null) {
			maxCount = "20";
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/dictionary.jsp");
			dispatcher.forward(request, response);
			return;
		}

		EjDictLogic logic = new EjDictLogic();

		List<String> errorList = logic.validate(word, maxCount);

		request.setAttribute("word", word);
		request.setAttribute("maxCount", maxCount);

		if (errorList.size() > 0) {
			request.setAttribute("errorList", errorList);
		} else {
			List<EjDict> dictList = logic.execute(word, maxCount);
			request.setAttribute("dictList", dictList);
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/dictionary.jsp");
		dispatcher.forward(request, response);
	}
}