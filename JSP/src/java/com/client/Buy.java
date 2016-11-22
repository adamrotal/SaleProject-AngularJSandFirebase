/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import manasik.marketplace.ClassNotFoundException_Exception;
import manasik.marketplace.SQLException_Exception;
import manasik.marketplace.SalesWS_Service;
import manasik.marketplace.YourProductWS_Service;

/**
 *
 * @author afp
 */
public class Buy extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SOAP/Sales_WS.wsdl")
    private SalesWS_Service service_1;

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
            out.println("<title>Servlet Buy</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Buy at " + request.getContextPath() + "</h1>");
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
        String fullName = session.getAttribute("fullName").toString();//"Ahmad Fajar Prasetiyo";
        String fullAddress = session.getAttribute("fullAddress").toString();//"Bandung";
        String postalCode = session.getAttribute("postalCode").toString();//"411";
        String phoneNumber = session.getAttribute("phoneNumber").toString();//"0909019209";
        
        try {
            List<String> produk = getSingleProduct(idProduk);
            request.setAttribute("fullName", fullName);
            request.setAttribute("fullAddress", fullAddress);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("postalCode", postalCode);
            request.setAttribute("idProduk", idProduk);
            request.setAttribute("produk", produk);
        } catch (SQLException_Exception | ClassNotFoundException_Exception ex) {
            Logger.getLogger(Buy.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("username",session.getAttribute("username").toString());
        request.getRequestDispatcher("/WEB-INF/confirmationPurchase.jsp").forward(request, response);
                
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
        String urlParameters;
        String urlTarget;
        urlParameters = "id=" + URLEncoder.encode(request.getParameter("idPenjual"), "UTF-8");
        urlTarget = GeneralConstant.getURLRest("/RESTUserById");
        String usernamePenjual = DoHttpRequest.executePost(urlTarget,urlParameters);
        
        String usernamePembeli = session.getAttribute("username").toString();
        String idPembeli = session.getAttribute("idUser").toString();
        
        String action = request.getParameter("action");
        String namaProduk = request.getParameter("namaProduk");
        String gambar = request.getParameter("gambar");
        String initPrice = request.getParameter("initPrice");
        String kuantitas = request.getParameter("kuantitas");
        String namaPembeli = request.getParameter("namaPembeli");
        String fullAddress = request.getParameter("fullAddress");
        String postalCode = request.getParameter("postalCode");
        String phoneNumber = request.getParameter("phoneNumber");
        String creditCard = request.getParameter("creditCard");
        String codeVerification = request.getParameter("codeVerification");
        String idProduk = request.getParameter("idProduk");
        String idPenjual = request.getParameter("idPenjual");
        if(action.equals("CANCEL")) {
            response.sendRedirect("/JSP/Catalog"); 
        } else {
            try {
                String succes = buy(idProduk, namaProduk, gambar, idPembeli, namaPembeli, usernamePembeli, fullAddress, postalCode, phoneNumber, creditCard, codeVerification, kuantitas, initPrice, idPenjual, usernamePenjual);
            } catch (ClassNotFoundException_Exception | SQLException_Exception ex) {
                Logger.getLogger(Buy.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/JSP/Purchases");
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

    private java.util.List<java.lang.String> getSingleProduct(java.lang.String idProduk) throws SQLException_Exception, ClassNotFoundException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.YourProductWS port = service.getYourProductWSPort();
        return port.getSingleProduct(idProduk);
    }

    private String buy(java.lang.String idProduk, java.lang.String namaProduk, java.lang.String gambarProduk, java.lang.String idPembeli, java.lang.String namaPembeli, java.lang.String usernamePembeli, java.lang.String fullAddress, java.lang.String postalCode, java.lang.String phoneNumber, java.lang.String creditCard, java.lang.String codeVerification, java.lang.String kuantitas, java.lang.String hargaSatuan, java.lang.String idPenjual, java.lang.String usernamePenjual) throws ClassNotFoundException_Exception, SQLException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.SalesWS port = service_1.getSalesWSPort();
        return port.buy(idProduk, namaProduk, gambarProduk, idPembeli, namaPembeli, usernamePembeli, fullAddress, postalCode, phoneNumber, creditCard, codeVerification, kuantitas, hargaSatuan, idPenjual, usernamePenjual);
    }

}
