/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Elizabeth.Bourke
 */
public class Projects {

    private int project_id;
    private String project_name;
    private String project_description;
    private String project_body;
    private String project_image;
    private String video_link;
    private int student_id;
    private int show_id;
    private double project_price;
    private double project_rating;
    private String name;
    private String email;
    private int contact_number;
    

    public Projects(String project_name, String project_image, String project_description, String project_body, String video_link, int student_id, double project_price) {
        this.project_name = project_name;
        this.project_image = project_image;
        this.project_description = project_description;
        this.project_body = project_body;
        this.video_link = video_link;
        this.student_id = student_id;
        this.project_price = project_price;
    }

    public Projects() {
    }

    public Projects(int project_id, String project_name, String project_image, String project_description, String project_body, String video_link, int student_id, double project_price) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_image = project_image;
        this.project_description = project_description;
        this.project_body = project_body;
        this.video_link = video_link;
        this.student_id = student_id;
        this.project_price = project_price;
    }

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
    public int getStudent_id() {
        return student_id;
    }

    /**
     * Set the value of userId
     *
     * @param userId new value of userId
     */
    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
    
    public String getProject_name(){
        return project_name;
    }
    
    public void setProject_name(String project_name){
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProject_body() {
        return project_body;
    }

    public void setProject_body(String project_body) {
        this.project_body = project_body;
    }
    
    public String getVideo_link(){
        return video_link;
    }
    
    public void setVideo_link(String video_link){
        this.video_link = video_link;
    }

    public String getProject_image() {
        return project_image;
    }

    public void setProject_image(String project_image) {
        this.project_image = project_image;
    }

    public int getProject_id() {
        return project_id;
    }
    
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getShow_id(){
        return show_id;
    }
    
    public void setShow_id(int show_id){
        this.show_id = show_id;
    }
    
    public double getProject_price(){
        return project_price;
    }
    
    public void setProject_price(double project_price){
        this.project_price = project_price;
    }
    
    public double getProject_rating(){
        return project_rating;
    }
    
    public void setProject_rating(double project_rating){
        this.project_rating = project_rating;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getContact_number(){
        return contact_number;
    }
    
    public void setContact_number(int contact_number){
        this.contact_number = contact_number;
    }
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public ArrayList<Projects> getAllProjects() {

        ArrayList<Projects> allprojects = new ArrayList<>();

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "Select * from project";

        try {

            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Projects n = new Projects();
                n.setProject_id(resultSet.getInt("project_id"));
                n.setProject_name(resultSet.getString("project_name"));
                n.setProject_image(resultSet.getString("project_image"));
                n.setProject_description(resultSet.getString("project_description"));
                n.setProject_body(resultSet.getString("project_body"));
                n.setStudent_id(resultSet.getInt("student_id"));
                n.setShow_id(resultSet.getInt("show_id"));
                n.setProject_price(resultSet.getDouble("project_price"));
                n.setProject_rating(resultSet.getDouble("project_rating"));
                allprojects.add(n);
            }

            connection.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        

        return allprojects;
    }
    
    public void deleteUserProjects(int user_id){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "DELETE FROM project where user_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, user_id);

            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void enterShow(int show_id, int project_id){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "UPDATE project set show_id = ? where project_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, show_id);
            ps.setInt(2, project_id);

            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
