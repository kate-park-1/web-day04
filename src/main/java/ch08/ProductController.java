package ch08;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
  ProductService pService;
  // 인터페이스 pService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    pService = new ProductService();
    // pService= new ProductDAO();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    String id = req.getParameter("id");
    String view="";

    if(action == null){
      getServletContext().getRequestDispatcher("/pcontrol?action=list").forward(req, resp);
    }else{
      if (action.equals("list")) {
        view = list(req, resp);
      } else if (action.equals("info")) {
        view = info(req, resp);
      }
      getServletContext().getRequestDispatcher("/ch08/"+view).forward(req, resp);
    }
  }

  private String info(HttpServletRequest req, HttpServletResponse resp){
    req.setAttribute("p", pService.find(req.getParameter("id")));
    return "productInfo.jsp";
  }

  private String list(HttpServletRequest req, HttpServletResponse resp){
    req.setAttribute("products", pService.findAll());
    return "productList.jsp";
  }
}

