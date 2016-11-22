/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

/**
 *
 * @author afp
 */
public class GeneralConstant {
    private static final String CLIENT = "http://localhost:8080/JSP";
    private static final String REST = "http://localhost:8082/REST";
    private static final String SOAP = "http://localhost:8081/SOAP";
    
    public static String getURLClient(String url) {
        return CLIENT + url;
    }
    
    public static String getURLSoap(String url) {
        return SOAP + url;
    }
    
    public static String getURLRest(String url) {
        System.out.println(REST + url);
        return REST + url;
    }

    
}
