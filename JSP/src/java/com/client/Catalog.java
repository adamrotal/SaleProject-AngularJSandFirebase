/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import manasik.marketplace.CatalogWS_Service;
import manasik.marketplace.ClassNotFoundException_Exception;
import manasik.marketplace.SQLException_Exception;


/**
 *
 * @author afp
 */
public class Catalog extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SOAP/Catalog_WS.wsdl")
    private CatalogWS_Service service;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SOAP/Catalog_WS.wsdl")
    

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
            out.println("<title>Servlet Catalog</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Catalog at " + request.getContextPath() + "</h1>");
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
            List<String> listCatalog;
            if(request.getParameter("keyword")!=null) {
                String keyword = request.getParameter("keyword");
                String category;
                if(request.getParameter("category")!=null) {
                    category = request.getParameter("category");
                } else {
                    category = "product";
                }
                request.setAttribute("keyword", keyword);
                request.setAttribute("category", category);
                listCatalog = searchCatalog(keyword,category,id);
            } else {
                listCatalog = getCatalog(id);
                request.setAttribute("keyword", "");
                request.setAttribute("category", "");
            }
            List<Map<String,String>> listProduct;
            listProduct = Parser.catalogParser(listCatalog);
            request.setAttribute("listCatalog", listProduct);
            request.setAttribute("username",session.getAttribute("username").toString());
            request.getRequestDispatcher("/WEB-INF/catalog.jsp").forward(request, response);
        } catch (ClassNotFoundException_Exception | SQLException_Exception ex) {
            Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex);
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

    private java.util.List<java.lang.String> getCatalog(java.lang.String token) throws ClassNotFoundException_Exception, SQLException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.CatalogWS port = service.getCatalogWSPort();
        return port.getCatalog(token);
    }

    private java.util.List<java.lang.String> searchCatalog(java.lang.String keyword, java.lang.String category, java.lang.String id) throws SQLException_Exception, ClassNotFoundException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.CatalogWS port = service.getCatalogWSPort();
        return port.searchCatalog(keyword, category, id);
    }

  
    

    
    
    
    

  

}
