package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

/** Servlet that displays a list of items being ordered.
 *  Accumulates them in an ArrayList with no attempt at
 *  detecting repeated items. Used to demonstrate basic
 *  session tracking. Updated to use generics.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate, JPA, and Java</a>.
 */

@WebServlet("/show-items")
public class ShowItems extends HttpServlet {
  @Override
  public void doPost (HttpServletRequest request,
                      HttpServletResponse response)
      throws ServletException, IOException {
         HttpSession session = request.getSession();
    synchronized(session) {
      @SuppressWarnings("unchecked")
      List<String> previousItems =
        (List<String>)session.getAttribute("previousItems");
      //Creem un altre array per al contador   
      List<Integer> previousNum =
        (List<Integer>)session.getAttribute("previousNum");
      
      boolean Repetido=false;
      
      if (previousItems == null) {
        previousItems = new ArrayList<String>();
        previousNum = new ArrayList<Integer>();
      }
      //Obtenim els items
      String newItem = request.getParameter("newItem");
      if ((newItem != null) &&
          (!newItem.trim().equals(""))) {
          //Comparem el nou item amb els anteriors
        for(int i=0; i<previousItems.size();i++) {
            if (previousItems.get(i).equalsIgnoreCase(newItem) && Repetido==false) {
                //Si exiteix iterem el numero
                previousNum.set(i, previousNum.get(i)+1);
                //posem el bolean a true per a que en el seguent if no el torni afegir
                Repetido=true;
            }
            
        }
        if (Repetido==false) {
            //afegim el item
                previousItems.add(newItem);
                previousNum.add(1);
            }
      }
      session.setAttribute("previousItems", previousItems);
      session.setAttribute("previousNum", previousNum);
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Items Purchased";
      String docType =
        "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
        "Transitional//EN\">\n";
      out.println(docType +
                  "<HTML>\n" +
                  "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                  "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                  "<H1>" + title + "</H1>");
      if (previousItems.size() == 0) {
        out.println("<I>No items</I>");
      } else {
        out.println("<UL>");
        for(int i = 0; i<previousItems.size();i++) {
          out.println("  <LI>" + previousItems.get(i)+" ("+ previousNum.get(i)+")");
          
        }
        out.println("</UL>");
      }
      out.println("</BODY></HTML>");
    }
  }
}
