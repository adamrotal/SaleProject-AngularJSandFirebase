/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manasik.marketplace;

import java.sql.SQLException;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Rotal
 */
@WebService(serviceName = "Sales_WS")
public class Sales_WS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param id
     * @return 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    @WebMethod(operationName = "getListSales")
    public List<String> getListSales(@WebParam(name = "id") String id) throws ClassNotFoundException, SQLException {
        return Database.getListSales(id);
    }

    /**
     * Web service operation
     * @param id
     * @return 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.sql.SQLException 
     */
    @WebMethod(operationName = "getListPurchases")
    public List<String> getListPurchases(@WebParam(name = "id") String id) throws ClassNotFoundException, SQLException {
        return Database.getListPurchases(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buy")
    public String buy(@WebParam(name = "idProduk") String idProduk, @WebParam(name = "namaProduk") String namaProduk, @WebParam(name = "gambarProduk") String gambarProduk, @WebParam(name = "idPembeli") String idPembeli, @WebParam(name = "namaPembeli") String namaPembeli, @WebParam(name = "usernamePembeli") String usernamePembeli, @WebParam(name = "fullAddress") String fullAddress, @WebParam(name = "postalCode") String postalCode, @WebParam(name = "phoneNumber") String phoneNumber, @WebParam(name = "creditCard") String creditCard, @WebParam(name = "codeVerification") String codeVerification, @WebParam(name = "kuantitas") String kuantitas, @WebParam(name = "hargaSatuan") String hargaSatuan, @WebParam(name = "idPenjual") String idPenjual, @WebParam(name = "usernamePenjual") String usernamePenjual) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "INSERT INTO sales(idProduk,namaProduk,gambarProduk,idPembeli,namaPembeli,usernamePembeli,fullAddress,postalCode,phoneNumber,creditCard,codeVerification,kuantitas,hargaSatuan,tanggalDiBeli,idPenjual,usernamePenjual) VALUES("+idProduk+",'"+namaProduk+"','"+gambarProduk+"',"+idPembeli+",'"+namaPembeli+"','"+usernamePembeli+"','"+fullAddress+"','"+postalCode+"','"+phoneNumber+"','"+creditCard+"','"+codeVerification+"','"+kuantitas+"',"+hargaSatuan+",CURDATE(),"+idPenjual+",'"+usernamePenjual+"')";
        Database.updateToDb(sql);
        return "1";
    }
}
