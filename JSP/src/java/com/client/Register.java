/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author afp
 */
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
                response.sendRedirect("/JSP/YourProduct");
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
        System.out.println("fullName");
        String fullName = request.getParameter("fullName");
        System.out.println("password");
        String password = request.getParameter("password");
        System.out.println("fullAddress");
        String fullAddress = request.getParameter("fullAddress");
        System.out.println("postalCode");
        String postalCode = request.getParameter("postalCode");
        System.out.println("phoneNumber");
        String phoneNumber = request.getParameter("phoneNumber");
        System.out.println("username");
        String username = request.getParameter("username");
        System.out.println("email");
        String email = request.getParameter("email");
        
        HttpSession session = request.getSession();

        String urlParameters;
        String urlTarget;
        
        urlParameters = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8")+ "&fullName=" + URLEncoder.encode(fullName, "UTF-8")+ "&fullAddress=" + URLEncoder.encode(fullAddress, "UTF-8")+ "&postalCode=" + URLEncoder.encode(postalCode, "UTF-8")+ "&phoneNumber=" + URLEncoder.encode(phoneNumber, "UTF-8")+ "&username=" + URLEncoder.encode(username, "UTF-8");
        
        
        urlTarget = GeneralConstant.getURLRest("/RESTRegister");
        
        String result = DoHttpRequest.executePost(urlTarget,urlParameters);
        
        if(result.equals("false")) {
            session.invalidate();
            response.sendRedirect("/JSP/Register");
        } else {
            session.setAttribute("token",result);
            response.sendRedirect("/JSP/YourProduct");
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
