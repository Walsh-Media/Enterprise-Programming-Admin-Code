/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.faces.action.RequestMapping;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Projects;
import model.Show;
import model.User;


public class ShowController extends HttpServlet {
    
    
//change all prjects to show
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            session.setAttribute("user", user);
        }

        String menu = request.getParameter("menu");
        if (menu == null) {
            menu = "home";
        }

        switch (menu) {

            case "home":
                gotoPage("/Homepage.jsp", request, response);
                break;
            case "CreateShow":
                gotoPage("/CreateShow.jsp", request, response);
                break;
            case "SaveShow":
                Show s = new Show();
                ProcessSaveShow(request, session);
                ArrayList<Show> shows = new ArrayList<>();
                shows = s.getAllShows();
                session.setAttribute("allShows", shows);
                gotoPage("/DisplayShows.jsp", request, response);
                break;
            case "Drilldown":
                getDrilldownShow(request, response, session);
                gotoPage("/ViewShow.jsp", request, response);
                break;
            case "UpdateShow":
                gotoPage("/UpdateShow.jsp", request, response);
                break;
            case "ManageShows":
                s = new Show ();
                Projects p =new Projects();
                ArrayList<Show> allShows = s.getAllShows();
                ArrayList<Projects> showProjects = p.getAllProjects();
                session.setAttribute("allShows", allShows);
                session.setAttribute("showProjects", showProjects);
                gotoPage("/DisplayShows.jsp", request, response);
                break;
            case "SaveShowDetails":
                String show_name = request.getParameter("show_name");
                String description = request.getParameter("description");
                int show_id = Integer.parseInt(request.getParameter("show_id"));
                
                s = new Show(show_name, description, show_id);
                s.updateShow();
                gotoPage("/DisplayShows.jsp", request, response);
                break;
            case "LiveShows":
                p =new Projects();
                showProjects = p.getAllProjects();
                session.setAttribute("showProjects", showProjects);
                getLiveShows(request, response, session);
                
                gotoPage("/DisplayLiveShows.jsp", request, response);              
                break;
            case "DeleteShow":
                deleteShow(request, response, session);
                gotoPage("/DisplayShows.jsp", request, response);             
                break;
            case "GoLive":                
                int is_live = 1;
                show_id = Integer.parseInt(request.getParameter("show_id"));
                s = new Show(show_id, is_live);
                s.setLive();
                gotoPage("/DisplayShows.jsp", request, response);             
                break;
            case "RetireShow":
                is_live = 0;
                show_id = Integer.parseInt(request.getParameter("show_id"));
                s = new Show(show_id, is_live);
                s.setLive();
                gotoPage("/DisplayShows.jsp", request, response);             
                break;
            default:
                gotoPage("/invalid.jsp", request, response);
                break;
        }
    }
    
    private void ProcessSaveShow(HttpServletRequest request, HttpSession session){
        String show_name = request.getParameter("show_name");
        String description = request.getParameter("description");
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        int is_live = Integer.parseInt(request.getParameter("is_live"));
        
        Show s = new Show(show_name, description, is_live, admin_id);
        s.saveToDB();
    }
    
    private void getLiveShows(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Show s = new Show();
        ArrayList<Show> liveShows = new ArrayList<>();
        
        liveShows = s.getLiveShows();
        session.setAttribute("liveShows", liveShows);
        System.out.println("asdfghjkqwertyuiozxcvbnmqwertyuiosdfghjkxcvbnm,sdfghjklerthrtyuiowfdsgfdfghjkldf");
    }
    
    private void getDrilldownShow(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        int show_id = Integer.parseInt(request.getParameter("show_id"));
        Show s = new Show();
        
        s.getDrilldownShow(show_id);
        session.setAttribute("showDrilldown", s);
    }
    
    private void deleteShow(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Show s = new Show();
        s = (Show)session.getAttribute("showDrilldown");

        s.deleteShow();
        session.removeAttribute("showDrilldown");
    }

    private void gotoPage(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
