package ch09;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
	StudentDAO dao = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new StudentDAO();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String view="";

		if (action == null) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(req, resp);
		} else {
			if (action.equals("list")) {
				view = list(req,resp);
			} else if (action.equals("insert")) {
				view = insert(req,resp);
			}
		}
		getServletContext().getRequestDispatcher("/ch09/"+view).forward(req,resp);
	}

	public String list(HttpServletRequest req, HttpServletResponse resp){
		req.setAttribute("students", dao.findAll());
		return "studentInfo.jsp";
	}

	public String insert(HttpServletRequest req, HttpServletResponse resp){
		Student s = new Student();
		try {
			BeanUtils.populate(s, req.getParameterMap());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		System.out.println(s.getUniv());
		dao.insert(s);
		return list(req,resp);
	}
}
