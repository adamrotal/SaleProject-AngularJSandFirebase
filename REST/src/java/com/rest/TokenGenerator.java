/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

// import java.sql.SQLException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author afp
 */
public class TokenGenerator {
    static private final int PANJANG = 15;
    static private final int EXTRA_TIME = 5;
    
    static public boolean invalidToken(String token) throws ClassNotFoundException, SQLException {
       return Database.invalidToken(token); 
    }
    
    static public void addTimeToken(String token) throws ClassNotFoundException, SQLException {
        Date date = new Date();
        long ms;
        ms = date.getTime();
        ms = ms + menitKeMs(EXTRA_TIME);
        Database.addTimeToken(token,ms);
    }
    
    static public boolean isValidToken(String token) throws ClassNotFoundException, SQLException {
        
        return Database.isValid(token);
    }
    
    static public String generateToken(String key) throws ClassNotFoundException, SQLException {
        String seed = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxz1234567890";
        String token;
        Random rand = new Random();
        int ind;
        System.out.println(key);
        do{
            token = "";
            for(int i = 0; i < PANJANG; i++) {
                ind = rand.nextInt(seed.length()-1);
                token = token + seed.charAt(ind);
            }
        } while (isValidToken(token));
        
        Date date = new Date();
        long ms;
        ms = date.getTime();
        ms = ms + menitKeMs(EXTRA_TIME);
        Database.insertToken(token, ms, key);
        
        
        return token;
    }
    
    static private int menitKeMs(int menit) {
        return menit * 60 * 1000;
    }
    
}
