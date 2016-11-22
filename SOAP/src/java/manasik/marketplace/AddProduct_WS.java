/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manasik.marketplace;

import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author afp
 */
@WebService(serviceName = "AddProduct_WS")
public class AddProduct_WS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addProduk")
    public String addProduk(@WebParam(name = "idPenjual") String idPenjual, @WebParam(name = "name") String name, @WebParam(name = "description") String description, @WebParam(name = "price") String price, @WebParam(name = "gambar") String gambar, @WebParam(name = "namaPenjual") String namaPenjual) throws ClassNotFoundException, SQLException {
        //TODO write your implementation code here:
        return Database.addProduct(idPenjual,name,description,price,gambar,namaPenjual);
    }
}
