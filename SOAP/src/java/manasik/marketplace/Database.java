package manasik.marketplace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/marketplace?zeroDateTimeBehavior=convertToNull";
    private static final String USER = "kuliah";
    private static final String PASS = "kuliah";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    
    static private void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASS);
    }
    
    static public void updateToDb(String sql) throws ClassNotFoundException, SQLException {
        
        if(connection == null) {
            createConnection();
        }
        Statement statement = connection.createStatement();
        int executeUpdate = statement.executeUpdate(sql);
    
        
        
    }
    
    static private ResultSet selectFromDb(String sql) throws ClassNotFoundException, SQLException {
        // Creating Connection
        // Class.forName(JDBC_DRIVER);
        // Connection connection = null;
        
        // try{
        //     connection = DriverManager.getConnection(URL, USER, PASS);
        //     Statement statement = connection.createStatement();
        //     ResultSet resultSet = statement.executeQuery(sql);
        //     return resultSet;
        // } finally {
//            if(connection != null)
//                connection.close();
//        }
        
        if(connection == null) {
            createConnection();
        }
        
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
        // Creating statement
        
        
        
        
    }
    
    static private int getNumsRow(ResultSet resultSet) throws SQLException {
        int count = 0;
        while (resultSet.next()) {
            ++count;
        }
        
        return count;
    }
    
    static private List<String> getAttribut(ResultSet resultSet,String id) throws SQLException, ClassNotFoundException {
        List<String> result = new ArrayList<>();
        
        while(resultSet.next()){
            result.add("id");
            result.add(resultSet.getString("id"));
            result.add("usernamePenjual");
            result.add(resultSet.getString("namaPenjual"));
            result.add("tanggalDiTambah");
            result.add(resultSet.getString("tanggalDiTambah"));
            result.add("name");
            result.add(resultSet.getString("name"));
            result.add("price");
            result.add(resultSet.getString("price"));
            result.add("description");
            result.add(resultSet.getString("description"));
            result.add("gambar");
            result.add(resultSet.getString("gambar"));
            result.add("nLike");
            int nLike = getNlike(resultSet.getString("id"));
            result.add(String.valueOf(nLike));
            result.add("nSales");
            int nSales = getNSales(resultSet.getString("id"));
            result.add(String.valueOf(nSales));
            result.add("liked");
            result.add(getLiked(id,resultSet.getString("id")));
            
        }
        
        return result;
    }
    
    static public List<String> getListCatalog(String id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM produk WHERE deleted = false ORDER BY id DESC ";
        ResultSet resultSet = selectFromDb(sql);
        return getAttribut(resultSet,id);
    }
    
    static public List<String> getListSearchCatalog(String id,String keyword,String category) throws ClassNotFoundException, SQLException {
        String sql;
        if(category.equals("product")) {
            sql = "SELECT * FROM produk WHERE (name LIKE '%"+keyword+"%') AND (deleted = false) ORDER BY id DESC";
        } else {
            sql = "SELECT * FROM produk WHERE (namaPenjual LIKE '%"+keyword+"%') AND (deleted = false) ORDER BY id DESC";
        }
        ResultSet resultSet = selectFromDb(sql);
        return getAttribut(resultSet,id);
    }
    
    static public List<String> getListYourProduct(String id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM produk WHERE (idPenjual="+id+") AND (deleted = false) ORDER BY id DESC";
        ResultSet resultSet = selectFromDb(sql);
        return getAttribut(resultSet,id);
    }
    
    static public List<String> getListSales(String id) throws ClassNotFoundException, SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT * FROM sales WHERE idPenjual = "+id+" ORDER BY id DESC";
        ResultSet resultSet = selectFromDb(sql);
        while(resultSet.next()) {
            result.add("tanggal");
            result.add(resultSet.getString("tanggalDiBeli"));
            result.add("gambar");
            result.add(resultSet.getString("gambarProduk"));
            result.add("nameProduk");
            result.add(resultSet.getString("namaProduk"));
            result.add("totalPrice");
            String totalPrice = String.valueOf(resultSet.getInt("kuantitas") * resultSet.getInt("hargaSatuan"));
            result.add(totalPrice);
            result.add("kuantitas");
            result.add(resultSet.getString("kuantitas"));
            result.add("price");
            result.add(resultSet.getString("hargaSatuan"));
            result.add("username");
            result.add(resultSet.getString("usernamePembeli"));
            result.add("namaPembeli");
            result.add(resultSet.getString("namaPembeli"));
            result.add("fullAddress");
            result.add(resultSet.getString("fullAddress"));
            result.add("postalCode");
            result.add(resultSet.getString("postalCode"));
            result.add("phoneNumber");
            result.add(resultSet.getString("phoneNumber"));
        }
        return result;
    }
    
    static public List<String> getListPurchases(String id) throws ClassNotFoundException, SQLException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT * FROM sales WHERE idPembeli = "+id+" ORDER BY id DESC";
        ResultSet resultSet = selectFromDb(sql);
        while(resultSet.next()) {
            result.add("tanggal");
            result.add(resultSet.getString("tanggalDiBeli"));
            result.add("gambar");
            result.add(resultSet.getString("gambarProduk"));
            result.add("nameProduk");
            result.add(resultSet.getString("namaProduk"));
            result.add("totalPrice");
            String totalPrice = String.valueOf(resultSet.getInt("kuantitas") * resultSet.getInt("hargaSatuan"));
            result.add(totalPrice);
            result.add("kuantitas");
            result.add(resultSet.getString("kuantitas"));
            result.add("price");
            result.add(resultSet.getString("hargaSatuan"));
            result.add("username");
            result.add(resultSet.getString("usernamePenjual"));
            result.add("namaPembeli");
            result.add(resultSet.getString("namaPembeli"));
            result.add("fullAddress");
            result.add(resultSet.getString("fullAddress"));
            result.add("postalCode");
            result.add(resultSet.getString("postalCode"));
            result.add("phoneNumber");
            result.add(resultSet.getString("phoneNumber"));
        }
        return result;
    }
    
    static public String deleteProduct(String idUser,String idProduk) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM produk WHERE id = "+idProduk;
        ResultSet produk = selectFromDb(sql);
        if(produk.next()) {
            if(idUser.equals(produk.getString("idPenjual"))){
                sql = "UPDATE produk SET deleted = true WHERE id = "+idProduk;
                updateToDb(sql);
                return "1";
            }
        }
        
        return "0";
    }
    
    static public List<String> getSingleProduct(String idProduk) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM produk WHERE id = "+idProduk;
        ResultSet produk = selectFromDb(sql);
        List<String> result = new ArrayList<>();
        if(produk.next()){
            result.add(produk.getString("name"));
            result.add(produk.getString("description"));
            result.add(produk.getString("price"));
            result.add(produk.getString("idPenjual"));
            result.add(produk.getString("gambar"));
            
        }
        
        return result;
    }
    
    static public String updateProduk(String idUser,String idProduk,String name,String description,String price) throws ClassNotFoundException, SQLException {
        List<String> produk = getSingleProduct(idProduk);
        System.out.print("aa");
        System.out.print(idProduk);
        if(produk.get(3).equals(idUser)) {
            String sql = "UPDATE produk SET name = '"+name+"', description = '"+description+"', price = "+price+" WHERE id = "+idProduk;
            updateToDb(sql);
            return "1";
        }
        return "0";
    }
    
    static public String doLike(String idUser, String idProduk) throws ClassNotFoundException, SQLException {
        String liked = getLiked(idUser,idProduk);
        String sql;
        if(liked.equals("0")) {
            sql = "INSERT INTO liked(idUser,idProduk) VALUES("+idUser+","+idProduk+")";
        } else {
            sql = "DELETE FROM liked WHERE (idUser = "+idUser+") AND (idProduk = "+idProduk+")";
        }
        updateToDb(sql);
        return "1";
    }
    
    static private int getNlike(String idProduk) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM liked WHERE idProduk = "+idProduk;
        ResultSet resultSet = selectFromDb(sql);
        return getNumsRow(resultSet);
    }
    
    static private int getNSales(String idProduk) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM sales WHERE idProduk = "+idProduk;
        ResultSet resultSet = selectFromDb(sql);
        return getNumsRow(resultSet);
    }
    
    static private String getLiked(String idUser,String idProduk) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM liked WHERE idProduk = "+idProduk+" AND idUser = "+idUser;
        ResultSet resultSet = selectFromDb(sql);
        int liked = getNumsRow(resultSet);
        return String.valueOf(liked);
    }
    
    static public String addProduct(String idPenjual,String name,String description,String price,String gambar, String namaPenjual) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO produk(idPenjual,name,description,price,gambar,namaPenjual,tanggalDiTambah,deleted) VALUES("+idPenjual+",'"+name+"','"+description+"',"+price+",'"+gambar+"','"+namaPenjual+"',CURDATE(),false)";
        updateToDb(sql);
        return "1";
    }
    
    
}
