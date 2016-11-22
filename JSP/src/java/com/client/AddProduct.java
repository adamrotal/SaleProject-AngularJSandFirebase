/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import manasik.marketplace.AddProductWS_Service;
import manasik.marketplace.ClassNotFoundException_Exception;
import manasik.marketplace.SQLException_Exception;

/**
 *
 * @author afp
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100,
                 location = "/")   	// 100 MB

public class AddProduct extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SOAP/AddProduct_WS.wsdl")
    private AddProductWS_Service service;

  
    private static final String UPLOAD_DIR = "asset/gambar";
    

    

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
            out.println("<title>Servlet AddProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("username",session.getAttribute("username").toString());
        request.getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
        
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
        
        if(request.getParameter("act").equals("CANCEL")) {
            response.sendRedirect("/JSP/YourProduct");
            return;
        }
        
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("/");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        String namaFile = request.getParameter("namaFile");
        System.out.println(namaFile);
        String fileName = null;
        
        Part part = request.getPart("photo");
        fileName = getFileName(part);
        part.write(uploadFilePath + File.separator + fileName);
    
        String idPenjual =session.getAttribute("idUser").toString();
        String name =request.getParameter("name");//kerjakan
        String description =request.getParameter("description");//kerjakan
        String price =request.getParameter("price");//kerjakan
        String gambar =fileName;//kerjakan
        String namaPenjual =session.getAttribute("username").toString();
        try {
            String succes = addProduk(idPenjual, name, description, price, gambar, namaPenjual);
        } catch (SQLException_Exception | ClassNotFoundException_Exception ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
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

    private String addProduk(java.lang.String idPenjual, java.lang.String name, java.lang.String description, java.lang.String price, java.lang.String gambar, java.lang.String namaPenjual) throws SQLException_Exception, ClassNotFoundException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        manasik.marketplace.AddProductWS port = service.getAddProductWSPort();
        return port.addProduk(idPenjual, name, description, price, gambar, namaPenjual);
    }
    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

   

    

    

}
