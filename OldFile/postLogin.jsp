<%-- 
    Document   : postLogin.jsp
    Created on : Nov 10, 2016, 10:47:21 AM
    Author     : mac
--%>


<%@page import="bean.getFromDatabase"%>  
<jsp:useBean id="obj" class="bean.LoginBean"/>  
  
<jsp:setProperty property="*" name="obj"/>  
  
<%  
    boolean status=getFromDatabase.validateLogin(obj);  
    if(status){  
        out.println("You r successfully logged in");  
        session.setAttribute("session","TRUE");
        response.sendRedirect("${root}/HTML/product.jsp");
    }  
    else  
    {  
        out.print("Sorry, email or password error");
    }
%>