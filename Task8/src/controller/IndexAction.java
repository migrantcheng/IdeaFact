package controller;

import javax.servlet.http.HttpServletRequest;

public class IndexAction extends Action {
	public String getName() { return "index.do"; }
    
    public String perform(HttpServletRequest request) {
       	return "search.jsp";
    }
}
