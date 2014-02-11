
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	public void init() throws ServletException{
//		Model model = new Model();
		
//		Action.add(new TransactionHistoryAction(model));
		Action.add(new IndexAction());
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    private String performTheAction(HttpServletRequest request) {
    	HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
//        Customer    user = (Customer) session.getAttribute("customer");
//        Employee employee = (Employee) session.getAttribute("employee");
        String      action = getActionName(servletPath);

        // System.out.println("servletPath="+servletPath+" requestURI="+request.getRequestURI()+"  user="+user);

        if (action.equals("register.do") || action.equals("employeeLogin.do") || action.equals("click.do")) {
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
        
        
//        if (employee == null) {
//        	// If the user hasn't logged in, direct him to the login page
//			return Action.perform("employeeLogin.do",request);
//        }
        
        return Action.perform(action,request);
    }
    
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}
    	
    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	if (nextPage.endsWith(".com")){
    		response.sendRedirect("http://"+nextPage);
	   		return;
    	}
    	
    	
    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }
    
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }

}
