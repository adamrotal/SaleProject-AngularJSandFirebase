/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author afp
 */
public class Login extends HttpServlet {
    
    
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();
        
        if(session.getAttribute("token") != null) {
            String token;
            token = session.getAttribute("token").toString();
            String urlParameters;
            String urlTarget;
            urlParameters = "token=" + URLEncoder.encode(token, "UTF-8");
            urlTarget = GeneralConstant.getURLRest("/RESTToken");
            String result = DoHttpRequest.executePost(urlTarget,urlParameters);
            if(result.equals("false")) {
                session.invalidate();
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } else {
                session.setAttribute("token",result);
                response.sendRedirect("/JSP/Catalog");
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        String urlParameters;
        String urlTarget;
        urlParameters = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
        urlTarget = GeneralConstant.getURLRest("/RESTLogin");
        
        String result = DoHttpRequest.executePost(urlTarget,urlParameters);
        System.out.print("aaa");
        if(result.equals("false")) {
            session.invalidate();
            response.sendRedirect("/JSP/Login");
        } else {
            session.setAttribute("token",result);
            System.out.print(result);
            
            urlParameters = "token=" + URLEncoder.encode(result, "UTF-8");
            urlTarget = GeneralConstant.getURLRest("/RESTFullName");
            String fullName = DoHttpRequest.executePost(urlTarget,urlParameters);
            session.setAttribute("fullName",fullName);
            System.out.print(fullName);
            
            urlTarget = GeneralConstant.getURLRest("/RESTFullAddress");
            String fullAddress = DoHttpRequest.executePost(urlTarget,urlParameters);
            session.setAttribute("fullAddress",fullAddress);
            System.out.print(fullAddress);
            
            urlTarget = GeneralConstant.getURLRest("/RESTPostalCode");
            String postalCode = DoHttpRequest.executePost(urlTarget,urlParameters);
            session.setAttribute("postalCode",postalCode);
            System.out.print(postalCode);
            
            urlTarget = GeneralConstant.getURLRest("/RESTPhoneNumber");
            String phoneNumber = DoHttpRequest.executePost(urlTarget,urlParameters);
            session.setAttribute("phoneNumber",phoneNumber);
            System.out.print(phoneNumber);
            
            urlTarget = GeneralConstant.getURLRest("/RESTIdUser");
            String idUser = DoHttpRequest.executePost(urlTarget,urlParameters);
            session.setAttribute("idUser",idUser);
            System.out.print(idUser);
            
            urlTarget = GeneralConstant.getURLRest("/RESTUser");
            String username = DoHttpRequest.executePost(urlTarget,urlParameters);
            session.setAttribute("username",username);
            System.out.print(username);
            
            response.sendRedirect("/JSP/Catalog");
        }
        
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
