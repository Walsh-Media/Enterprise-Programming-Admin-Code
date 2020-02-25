/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User implements Serializable {

    private int user_id;
    private String full_name;
    private int account_type;
    private String email;
    private String k_number;
    private int contact_number;
    private String profile_image;
    private String password;
    private String course_code;
    private String course_year;
    private String profile_description;

    public User() {
    }
    
    public User(int user_id) {
        this.user_id = user_id;
    }

    public User(int user_id, String full_name, String email, int contact_number, String profile_image, String password, String course_year, String profile_description) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.contact_number = contact_number;
        this.profile_image = profile_image;
        this.password = password;
        this.course_year = course_year;
        this.profile_description = profile_description;
    }
    public User(String full_name, int account_type, String email, String k_number, int contact_number, String profile_image, String password, String course_code, String course_year) {

        this.full_name = full_name;
        this.account_type = account_type;
        this.email = email;
        this.k_number = k_number;
        this.contact_number = contact_number;
        this.profile_image = profile_image;
        this.password = password;
        this.course_code = course_code;
        this.course_year = course_year;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int user_id, String full_name, String email, String k_number, int contact_number, String profile_image, String password, String course_code, String course_year, String profile_description) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email = email;
        this.k_number = k_number;
        this.contact_number = contact_number;
        this.profile_image = profile_image;
        this.password = password;
        this.course_code = course_code;
        this.course_year = course_year;
        this.profile_description = profile_description;
    }
    
    
    
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    
    public int getAccount_type(){
        return account_type;
    }
    
    public void setAccount_type(int account_type){
        this.account_type = account_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getK_number() {
        return k_number;
    }
    
     public void setK_number(String k_number) {
        this.k_number = k_number;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourse_code() {
        return course_code;
    }
    
    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_year() {
        return course_year;
    }

    public void setCourse_year(String course_year) {
        this.course_year = course_year;
    }

    public String getProfile_description(){
        return profile_description;
    }
    
    public void setProfile_description(String profile_description){
        this.profile_description = profile_description;
    }

    public User Login(String email, String password) {

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String query = "Select * from User where email = ? AND password = ?";

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            resultSet = ps.executeQuery();
          
           
            while (resultSet.next()) {
                this.user_id = resultSet.getInt("user_id");
                this.full_name = resultSet.getString("full_name");
                this.account_type = resultSet.getInt("account_type");
                this.email = resultSet.getString("email");
                this.k_number = resultSet.getString("k_number");
                this.contact_number = resultSet.getInt("contact_number");  
                this.profile_image = resultSet.getString("profile_image");
                this.password = resultSet.getString("password");
                this.course_code = resultSet.getString("course_code");             
                this.course_year = resultSet.getString("course_year");
                
            }

            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return this;

    }

    public User updateUser() {

        Connection connection = DatabaseUtilityClass.getConnection();
         
        String sql = "UPDATE user SET full_name = ?, email = ?, "
                + "k_number = ?, contact_number = ?, profile_image = ?, "
                + "password = ?, course_code = ?, course_year = ?, profile_description = ? where user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, this.getFull_name());
            ps.setString(2, this.getEmail());
            ps.setString(3, this.getK_number());
            ps.setInt(4, this.getContact_number());
            ps.setString(5, this.getProfile_image());
            ps.setString(6, this.getPassword());
            ps.setString(7, this.getCourse_code());
            ps.setString(8, this.getCourse_year());
            ps.setString(9, this.getProfile_description());
            ps.setInt(10, this.getUser_id());

            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return this;
    }
    
     public ArrayList<User> getAllUsers() {

        ArrayList<User> allUsers = new ArrayList<>();

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "Select * from user";

        try {

            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User u = new User();
                u.setUser_id(resultSet.getInt("user_id"));
                u.setFull_name(resultSet.getString("full_name"));
                u.setAccount_type(resultSet.getInt("account_type"));
                u.setEmail(resultSet.getString("email"));
                u.setK_number(resultSet.getString("k_number"));
                u.setContact_number(resultSet.getInt("contact_number"));
                u.setProfile_image(resultSet.getString("profile_image"));
                u.setPassword(resultSet.getString("password"));
                u.setCourse_code(resultSet.getString("course_code"));
                u.setCourse_year(resultSet.getString("course_year"));
                u.setProfile_description(resultSet.getString("profile_description"));
                allUsers.add(u);     
            }

            connection.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        
        return allUsers;
     }
     
     public User getUser() {

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "Select * from user where user_id = ?";

        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, this.getUser_id());
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                this.setFull_name(resultSet.getString("full_name"));
                this.setAccount_type(resultSet.getInt("account_type"));
                this.setEmail(resultSet.getString("email"));
                this.setK_number(resultSet.getString("k_number"));
                this.setContact_number(resultSet.getInt("contact_number"));
                this.setProfile_image(resultSet.getString("profile_image"));
                this.setPassword(resultSet.getString("password"));
                this.setCourse_code(resultSet.getString("course_code"));
                this.setCourse_year(resultSet.getString("course_year"));
                this.setProfile_description(resultSet.getString("profile_description"));    
            }

            connection.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        
        return this;
     }
     
     public void deleteUser(){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "DELETE FROM user where user_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, user_id);

            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}

