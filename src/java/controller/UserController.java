/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Projects;
import model.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Elizabeth.Bourke
 */
public class UserController extends HttpServlet {

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
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        List<FileItem> items = null;
        String menu = request.getParameter("menu");
        HttpSession session = request.getSession();
        
        if (isMultipart) {
            System.out.println("multi request");
            //get list of item in request
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                items = upload.parseRequest(request);

            } catch (Exception e) {
                e.printStackTrace();
            }
            menu = getMultiRequest(items, "menu");

        } else {
            System.out.println("single request");
            menu = request.getParameter("menu");

        }
        
        User user = (User) session.getAttribute("user");
        ArrayList<Projects> myProjects = new ArrayList<>();
        
        if (user == null) {
            user = new User();
            System.out.println("user object is null");
            session.setAttribute("user", user);
        }else{
            System.out.println("user object is found");
        }

        switch (menu) {
            case "Login":
                gotoPage("/login.jsp", request, response);
                break;
            case "Logout":
                logout(request, response, session);
                gotoPage("/index.jsp", request, response);
                break;
            case "adminHomepage":
                gotoPage("/AdminHomepage.jsp", request, response);
                break; 
            case "userProfile":
                getProfile(request, response, session);
                User userProfile = (User)session.getAttribute("userProfile");
                Projects p = new Projects();
                    myProjects = p.getUserProjects(userProfile.getUser_id());
                    session.setAttribute("myProjects", myProjects);
                gotoPage("/UserProfile.jsp", request, response);
                break;
            case "Process Login":
                boolean validLogin = ProcessLogin(request, session);

                if (!validLogin) {
                    String message = "invalid logon details.. try again";
                    session.setAttribute("message", message);
                    gotoPage("/login.jsp", request, response);
                } else {
                    session.setAttribute("isLoggedIn", true);
                    User u = (User)session.getAttribute("user");
                    int userType = u.getAccount_type();
                    
                    if(userType == 1){
            
                    p = new Projects();
                    myProjects = p.getUserProjects(user.getUser_id());
                    System.out.println("PROJECT NAME: ");
                    session.setAttribute("myProjects", myProjects);
                    gotoPage("/MyProfile.jsp", request, response);
                    
                } else {
                        gotoPage ("/AdminHomepage.jsp", request, response);
                        }
                }
                break;
            case "UpdateUserDetailsAdmin":
                gotoPage("/UpdateUserDetailsAdmin.jsp", request, response);
                break; 
            case "SaveUserDetailsAdmin":
                //get new user details from user
                String full_name = getMultiRequest(items, "full_name");
                String email = getMultiRequest(items, "email");
                String k_number = getMultiRequest(items, "k_number");
                int contact_number = Integer.parseInt(getMultiRequest(items, "contact_number"));               
                String profile_image = doFileUpload(items, response);
                String password = getMultiRequest(items, "password");
                String course_code = getMultiRequest(items, "course_code");
                String course_year = getMultiRequest(items, "course_year");
                String profile_description = getMultiRequest(items, "profile_description");
                
                
                //get userid
                User olduserdetails = (User) session.getAttribute("userProfile");
                User us = new User(olduserdetails.getUser_id(),full_name, email, k_number, contact_number, profile_image, password, course_code, course_year, profile_description);
                //save user details to database
                us.updateUser();
                
                //update session object (user) with new deatils
                session.setAttribute("userProfile", us);
                gotoPage("/UserProfile.jsp", request, response);
                break;
            case "displayAllUsers":
                User u = new User () ;
                ArrayList<User> myUsers = u.getAllUsers();
                session.setAttribute("allUsers", myUsers);
                gotoPage("/DisplayUsers.jsp", request, response);
                break;
            case "delete":
                deleteUser(request, response, session);
                u = new User () ;
                myUsers = u.getAllUsers();
                session.setAttribute("allUsers", myUsers);
                gotoPage("/DisplayUsers.jsp", request, response);
            default:
                gotoPage("/invalid.jsp", request, response);
                break;
        }
    }

    private boolean ProcessLogin(HttpServletRequest request, HttpSession session) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User us = new User(email, password);
        us.Login(email, password);
        session.setAttribute("user", us);
        if (us.getUser_id() != 0) {
            return true;    
        } else {
            return false;
        }

    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
    }

    private void ProcessSave(HttpServletRequest request, HttpSession session) {
        try{
            
            User user = (User)session.getAttribute("user");
            User us = null;
            if (user.getAccount_type() == 1)
            {
                 String full_name = request.getParameter("full_name");
                 int account_type = Integer.parseInt(request.getParameter("account_type"));
                 String email = request.getParameter("email");
                 String k_number = request.getParameter("k_number");
                 int contact_number = Integer.parseInt(request.getParameter("contact_number"));
                 String profile_image = request.getParameter("profile_image");
                 String password = request.getParameter("password");
                 String course_code = request.getParameter("course_code");
                 String course_year = request.getParameter("course_year");

        us = new User(full_name, account_type, email, k_number, contact_number, profile_image, password, course_code, course_year);
        us.saveToDatabase(); 
            }
            
        

        session.setAttribute("user", us);
        System.out.println("userid" + us.getUser_id());
        } catch (NumberFormatException e){
            System.out.println("Num format exception: " + e.getStackTrace());
        }
        
    }
    
    private void getProfile(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try{
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            User u = new User(user_id);
            u.getUser();
            session.setAttribute("userProfile", u);
            System.out.println(u.getContact_number());
        } catch (NumberFormatException e){
            System.out.println("Num format exception: " + e.getStackTrace());
        }
        
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        try{
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            User u = new User(user_id);
            u.deleteUser();
            Projects p = new Projects();
            p.deleteUserProjects(user_id);
        } catch (NumberFormatException e){
            System.out.println("Num format exception: " + e.getStackTrace());
        }
    }
    
        private String doFileUpload(List<FileItem> items,
            HttpServletResponse response) {
        
        String fileName=null;
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
          //  List items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();

                if (!item.isFormField()) {

                    fileName = item.getName();

                    String root = getServletContext().getRealPath("/");
                    File path = new File(root + "/assets/img/profileImages");
                    if (!path.exists()) {
                        boolean status = path.mkdirs();
                    }

                    File uploadedFile = new File(path + "/" + fileName);
                    System.out.println(uploadedFile.getAbsolutePath());
                    item.write(uploadedFile);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private String getMultiRequest(List<FileItem> items, String fieldnameRequired) {
      
        String fname = null;

        
        for (FileItem uploadItem : items) {
         
            String fieldName = uploadItem.getFieldName();
           
            if (fieldnameRequired.equals(fieldName)) {
                System.out.println(uploadItem.getString());
                return uploadItem.getString();
            }

        }

        return fname;
    }

    private void gotoPage(String url,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
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
