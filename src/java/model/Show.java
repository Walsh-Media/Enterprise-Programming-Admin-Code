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
public class Show {
    
    //Change all projects to show
    //change variables to show table variables in databae 
    //change geters & setters
    //

    private int show_id;
    private String show_name;
    private String description;
    private int is_live;
    private int admin_id;
  
    

    public Show(String show_name, String description, int is_live, int admin_id) {
        this.show_name = show_name;
        this.description = description;
        this.is_live = is_live;
        this.admin_id = admin_id;
        
    }
    
    public Show(int show_id, int is_live) {
        this.show_id = show_id;
        this.is_live = is_live;
        
    }

    public Show() {
    }

    public Show(String show_name, String description, int show_id) {       
        this.show_name = show_name;
        this.description = description;
        this.show_id = show_id;
    }

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
   

    /**
     * Set the value of userId
     *
     * @param userId new value of userId
     */
   
    
    public String getShow_name(){
        return show_name;
    }
    
    public void setShow_name(String show_name){
        this.show_name = show_name;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    public int getShow_id() {
        return show_id;
    }
    
    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

   public int getIs_live() {
        return is_live;
    }
    
    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }
    
   
    
    public ArrayList<Show> getAllShows() {

        ArrayList<Show> allshows = new ArrayList<>();

        Connection connection = DatabaseUtilityClass.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String query = "Select * from shows";

        try {

            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Show s = new Show();
                s.setShow_id(resultSet.getInt("show_id"));
                s.setShow_name(resultSet.getString("show_name"));
                s.setDescription(resultSet.getString("description"));
                s.setIs_live(resultSet.getInt("is_live"));
                s.setAdmin_id(resultSet.getInt("admin_id"));
              
                allshows.add(s);
            }

            connection.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        

        return allshows;
    }
    
    public ArrayList<Show> getLiveShows() {
        
        ArrayList<Show> liveShows = new ArrayList<>();

        Connection connection = DatabaseUtilityClass.getConnection();
        String query = "Select * from shows where is_live = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 1);            
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                
                Show s = new Show();
                s.setShow_id(resultSet.getInt("show_id"));
                s.setShow_name(resultSet.getString("show_name"));
                s.setDescription(resultSet.getString("description"));
                s.setIs_live(resultSet.getInt("is_live"));
                s.setAdmin_id(resultSet.getInt("admin_id"));
                
            
                liveShows.add(s);
            }

            connection.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        //System.out.println(userProjects.get(0).end_date);
        return liveShows;
    }
    
    public Show getDrilldownShow(int show_id){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String query = "Select * from shows where show_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, show_id);            
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                this.setShow_id(resultSet.getInt("show_id"));
                this.setShow_name(resultSet.getString("show_name"));
                this.setDescription(resultSet.getString("description"));
                this.setIs_live(resultSet.getInt("is_live"));
                this.setAdmin_id(resultSet.getInt("admin_id"));             
            }
               
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        return this;
    }
    
    public Show saveToDB(){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "INSERT INTO shows (show_name, description, is_live, admin_id) VALUES (?,?,?,?)";
        String query = "SELECT LAST_INSERT_ID()";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps.setString(1, this.getShow_name());
            ps.setString(2, this.getDescription());
            ps.setInt(3, this.getIs_live());
            ps.setInt(4, this.getAdmin_id());
            ps.executeUpdate();
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                this.show_id = rs.getInt(1);
            }
               
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
        return this;
    }
    
    public Show updateShow(){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "UPDATE shows SET show_name = ?, description = ? WHERE show_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, this.getShow_name());
            ps.setString(2, this.getDescription());
            ps.setInt(3, this.getShow_id());


            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return this;
    }
    
    public Show setLive(){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "UPDATE shows SET is_live = ? WHERE show_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, this.getIs_live());
            ps.setInt(2, this.getShow_id());


            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return this;
    }

    public void deleteShow(){
        Connection connection = DatabaseUtilityClass.getConnection();
        
        String sql = "DELETE FROM shows where show_id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, show_id);

            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
