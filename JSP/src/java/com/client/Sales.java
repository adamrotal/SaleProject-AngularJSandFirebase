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
import java.util.Map;
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
import manasik.marketplace.SalesWS_Service;

/**
 *
 * @author afp
 */
public class Sales extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SOAP/Sales_WS.wsdl")
    private SalesWS_Service service;

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
            out.println("<title>Servlet Sales</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sales at " + request.getContextPath() + "</h1>");
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
        
        String id = session.getAttribute("idUser").toString();
        
        try {
            List<Map<String,String>> listProduct = Parser.salesParser(getListSales(id));
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("username",session.getAttribute("username").toString());
            request.getRequestDispatcher("/WEB-INF/sales.jsp").forward(request, response);
        } catch (SQLException_Exception | ClassNotFoundException_Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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

    private java.util.List<java.lang.String> getListSales(java.lang.String id) throws SQLException_Exception, ClassNotFoundException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.SalesWS port = service.getSalesWSPort();
        return port.getListSales(id);
    }

}
