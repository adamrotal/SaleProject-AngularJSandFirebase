/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;
import java.sql.* ;  

/**
 *
 * @author afp
 */
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/service?zeroDateTimeBehavior=convertToNull";
    private static final String USER = "kuliah";
    private static final String PASS = "kuliah";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    
    static private void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASS);
    }
    
    static public void updateToDb(String sql) throws ClassNotFoundException, SQLException {
        
        if(connection == null) {
            createConnection();
        }
        Statement statement = connection.createStatement();
        int executeUpdate = statement.executeUpdate(sql);
    
        
        
    }
    
    static private ResultSet selectFromDb(String sql) throws ClassNotFoundException, SQLException {
        // Creating Connection
        // Class.forName(JDBC_DRIVER);
        // Connection connection = null;
        
        // try{
        //     connection = DriverManager.getConnection(URL, USER, PASS);
        //     Statement statement = connection.createStatement();
        //     ResultSet resultSet = statement.executeQuery(sql);
        //     return resultSet;
        // } finally {
//            if(connection != null)
//                connection.close();
//        }
        
        if(connection == null) {
            createConnection();
        }
        
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
        // Creating statement
        
        
        
        
    }
    
    static public boolean login(String key, String password) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "SELECT * FROM user WHERE (username='"+key+"' OR email='"+key+"') AND password='"+password+"'";
        ResultSet resultSet = selectFromDb(sql);
        
        return resultSet.next();
    }
    
    static public void insertToken(String token, long waktu, String key) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "UPDATE user SET token='"+token+"', tanggalExp="+waktu+" WHERE email='"+key+"' OR username='"+key+"'";
        System.out.println(sql);
        updateToDb(sql);
    }
    
    static public boolean isValid(String token) throws ClassNotFoundException, SQLException {
        String sql;
        java.util.Date date = new java.util.Date();
        long ms;
        ms = date.getTime();
        
        sql = "SELECT * FROM user WHERE token='"+token+"' AND tanggalEXP > "+ms;
        ResultSet resultSet = selectFromDb(sql);
        
        return resultSet.next();
    }
    
    static public void addTimeToken(String token, long waktu) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "UPDATE user SET tanggalExp="+waktu+" WHERE token='"+token+"'";
        updateToDb(sql);
    }
    
    static public boolean invalidToken(String token) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "UPDATE user SET tanggalExp=0 WHERE token='"+token+"'";
        updateToDb(sql);
        return true;
    }
    
    static public boolean isExistKey(String key) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "SELECT * FROM user WHERE username='"+key+"' OR email='"+key+"'";
        ResultSet resultSet = selectFromDb(sql);
        
        return resultSet.next();
    }
    
    static public void insertUser(String fullName, String username, String email, String password, String fullAddress, String postalCode, String phoneNumber) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "INSERT INTO user(fullName,username,email,password,fullAddress,postalCode,phoneNumber) VALUES('"+fullName+"','"+username+"','"+email+"','"+password+"','"+fullAddress+"','"+postalCode+"','"+phoneNumber+"')";
        updateToDb(sql);
    }
    
    static public ResultSet getUser(String token) throws ClassNotFoundException, SQLException {
        String sql;
        java.util.Date date = new java.util.Date();
        long ms;
        ms = date.getTime();
        
        sql = "SELECT * FROM user WHERE token='"+token+"'";
        ResultSet resultSet = selectFromDb(sql);
        
        return resultSet;
    }
    
    static public ResultSet getUserById(String id) throws ClassNotFoundException, SQLException {
        String sql;
        java.util.Date date = new java.util.Date();
        long ms;
        ms = date.getTime();
        
        sql = "SELECT * FROM user WHERE id="+id;
        ResultSet resultSet = selectFromDb(sql);
        
        return resultSet;
    }
}
