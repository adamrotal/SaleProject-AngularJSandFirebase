/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import manasik.marketplace.ClassNotFoundException_Exception;
import manasik.marketplace.SQLException_Exception;
import manasik.marketplace.YourProductWS_Service;

/**
 *
 * @author afp
 */
public class Edit extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SOAP/YourProduct_WS.wsdl")
    private YourProductWS_Service service;

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
            out.println("<title>Servlet Edit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Edit at " + request.getContextPath() + "</h1>");
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
                response.sendRedirect("/JSP/Login");
                return;
            }
        } else {
            session.invalidate();
            response.sendRedirect("/JSP/Login");
            return;
        }
        
        String idProduk = request.getParameter("idProduk");
        String idUsername = session.getAttribute("idUser").toString();
        try {
            List<String> produk = getSingleProduct(idProduk);
            request.setAttribute("idProduk", idProduk);
            request.setAttribute("name", produk.get(0));
            request.setAttribute("description", produk.get(1));
            request.setAttribute("price", produk.get(2));
            request.setAttribute("username",session.getAttribute("username").toString());
            request.getRequestDispatcher("/WEB-INF/editProduct.jsp").forward(request, response);
        } catch (SQLException_Exception | ClassNotFoundException_Exception ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
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
                response.sendRedirect("/JSP/Login");
                return;
            }
        } else {
            session.invalidate();
            response.sendRedirect("/JSP/Login");
            return;
        }
        String idUsername = session.getAttribute("idUser").toString();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String idProduk = request.getParameter("idProduk");
        System.out.print(idProduk);
        System.out.print(name);
        try {
            String succes = updateProduk(idUsername, idProduk, name, description, price);
        } catch (ClassNotFoundException_Exception | SQLException_Exception ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("/JSP/YourProduct");
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

    private java.util.List<java.lang.String> getSingleProduct(java.lang.String idProduk) throws SQLException_Exception, ClassNotFoundException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.YourProductWS port = service.getYourProductWSPort();
        return port.getSingleProduct(idProduk);
    }

    private String updateProduk(java.lang.String idUser, java.lang.String idProduk, java.lang.String name, java.lang.String description, java.lang.String price) throws ClassNotFoundException_Exception, SQLException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.YourProductWS port = service.getYourProductWSPort();
        return port.updateProduk(idUser, idProduk, name, description, price);
    }

}
