/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manasik.marketplace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Rotal
 */
@WebService(serviceName = "YourProduct_WS")
public class YourProduct_WS {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlineshop?zeroDateTimeBehavior=convertToNull";
    private static final String USER = "kuliah";
    private static final String PASS = "kuliah";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    /**
     * This is a sample web service operation
     * @param id
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @WebMethod(operationName = "getProduct")
    public List<String> getProducts(@WebParam(name = "id") String id) throws ClassNotFoundException, SQLException {
        return Database.getListYourProduct(id);
    }


    /**
     * Web service operation
     * @param idUser
     * @param idProduk
     * @return 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @WebMethod(operationName = "deleteProduct")
    public String deleteProduct(@WebParam(name = "idUser") String idUser, @WebParam(name = "idProduk") String idProduk) throws SQLException, ClassNotFoundException {
        return Database.deleteProduct(idUser,idProduk);
    }

    /**
     * Web service operation
     * @param idProduk
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @WebMethod(operationName = "getSingleProduct")
    public List<String> getSingleProduct(@WebParam(name = "idProduk") String idProduk) throws ClassNotFoundException, SQLException {
        //TODO write your implementation code here:
        return Database.getSingleProduct(idProduk);
    }

    /**
     * Web service operation
     * @param idUser
     * @param idProduk
     * @param name
     * @param description
     * @param price
     * @return 
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @WebMethod(operationName = "updateProduk")
    public String updateProduk(@WebParam(name = "idUser") String idUser, @WebParam(name = "idProduk") String idProduk, @WebParam(name = "name") String name, @WebParam(name = "description") String description, @WebParam(name = "price") String price) throws ClassNotFoundException, SQLException {
        //TODO write your implementation code here:
        return Database.updateProduk(idUser,idProduk,name,description,price);
    }


    

}
