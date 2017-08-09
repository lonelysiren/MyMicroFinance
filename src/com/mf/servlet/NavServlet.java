package com.mf.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mf.dao.NavDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class NavServlet
 */
@WebServlet("/NavServlet")
public class NavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NavDao Dao = new NavDao();
		List<Map<String, Object>> navBar = Dao.NavBar();
		List<Map<String, Object>> delList = new ArrayList<Map<String, Object>>();
	
		for (Map<String, Object> parent : navBar) {
			List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
			Boolean isParent = 
			  parent.get("pid").toString().isEmpty();
			if(isParent) {
				parent.put("spread", false);
				for (Map<String, Object> child : navBar) {
					
					if(child.get("pid") == parent.get("id")) {
						children.add(child);
						parent.put("children", children);
						child.remove("spread");
						delList.add(child);
					}
				}
			}
		}
		
		navBar.removeAll(delList);
		JSONArray nav = JSONArray.fromObject(navBar);
		
		 Object sessionObj = request.getSession().getAttribute("role");
		response.getWriter().write(nav.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
