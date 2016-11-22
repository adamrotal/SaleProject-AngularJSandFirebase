/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

/**
 *
 * @author afp
 */
public class GeneralConstant {
    String client = "http://localhost:8080/JSP";
    String rest = "http://localhost:8082/REST";
    String soap = "http://localhost:8081/SOAP";
    static private GeneralConstant addr = null;
    
    private GeneralConstant() {
        
    }
    
    static public GeneralConstant instance() {
        if(addr == null) {
            addr = new GeneralConstant();
        }
        
        return addr;
    }
    
    public String getURLClient(String url) {
        return client + url;
    }
    
    public String getURLSoap(String url) {
        return soap + url;
    }
    
    public String getURLRest(String url) {
        return rest + url;
    }
    
}
