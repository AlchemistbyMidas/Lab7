package com.devcolibri.database;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import Store.Basket;
import Store.Product;



public class Main {


    public static void main(String[] args) throws SQLException {
        Product p= new Product("cucumber", LocalDate.of(2017, 2, 7), 1, 2.50);
        Product p2= new Product("milk", LocalDate.of(2017, 2, 7), 1, 2.50);
        Product p3= new Product("orange", LocalDate.of(2017, 2, 7), 1, 2.50);
        Basket b = new Basket(LocalDate.of(2012, 2, 7),"yellow",6);
        b.addNewProducts(p);
        b.addNewProducts(p2);
        b.addNewProducts(p3);
    //insertProduct(p2);
      //  clearAllProducts();
       // insertProduct(p);
       // insertBasket(b);
        /*for(Product pq : getAllProducts()){
            System.out.println(pq.getName());
        }*/
        //insertProduct(p);
//clearAllProducts();
//clearAllBaskets();
       // clearAllProducts();
      //  insertProduct(p);
       // insertBasket(b);

//    insertProduct(p);
  //  insertBasket(b);
      //  insertProduct(p2);
      //  insertBasket(b);
       // clearAllProducts();
       // clearAllBaskets();



    }
    public static Connection getConnection() throws SQLException {
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_java_lab6","root","root");
       return connection;
    }
    public static void  insertProduct (Product p ) throws SQLException {
        Connection conn= getConnection();
        //Statement statement = conn.createStatement();
        PreparedStatement statement = conn.prepareStatement("insert into products (name,dateofcreation,shelftime,price) values('"+p.getName()+"','"+p.getDateOfCreation()+"','"+p.getShelfTime()+"','"+p.getPrice()+"')");
        statement.execute();

       // statement.execute("insert into products (name,dateofcreation,shelftime,price) values('"+p.getName()+"','"+p.getDateOfCreation()+"','"+p.getShelfTime()+"','"+p.getPrice()+"')");
        conn.close();
    }
    public static void insertBasket(Basket b) throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("insert into baskets (time_of_pick_up,color,spaciousness) values('"+b.getTimeOfPicingkUp()+"','"+b.getColor()+"','"+b.getSpaciousness()+"')");
        ResultSet rs = statement.executeQuery("SELECT id FROM baskets WHERE time_of_pick_up='" + b.getTimeOfPicingkUp() + "';");
        rs.next();
        int id_for_products =rs.getInt("id");
        for(Product p : b.getListOfProduct()) {
            statement.execute("insert into products (name,dateofcreation,shelftime,price,frombasket) values('" + p.getName() + "','" + p.getDateOfCreation() + "','" + p.getShelfTime() + "','" + p.getPrice() + "','" + id_for_products + "')");
        }
    }
    //Foreign Keys
    //Prepared statement
    //Butch statement
    // Dao
    //Один метод для корзини
    public static  List<Product> getAllProducts() throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products;");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"));
            res.add(p);
        }
        return res;
    }
    public static List<Product> getProductsByOriginId(int id_origin) throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE frombasket=" + id_origin + ";");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"));
            res.add(p);
        }
        return res;
    }
    public static List<Basket> getAllBaskets() throws SQLException {
        List<Basket> res = new ArrayList<Basket>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM baskets;");
        while(rs.next()){
           // Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"));
            //res.add(p);
            Basket b= new Basket(rs.getTimestamp("time_of_pick_up").toLocalDateTime().toLocalDate(),rs.getString("color"),rs.getInt("spaciousness"),getProductsByOriginId(rs.getInt("id")));
            res.add(b);
        }
        return res;
    }
    public static Basket getBasketById(int id) throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE id=" + id + ";");
        Basket b= new Basket(rs.getTimestamp("time_of_pick_up").toLocalDateTime().toLocalDate(),rs.getString("color"),rs.getInt("spaciousness"),getProductsByOriginId(rs.getInt("id")));
        return b;
    }
    public static void clearAllProducts() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("delete from database_java_lab6.products where id>0;");
        conn.close();
    }
    public static void clearAllBaskets() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("delete from database_java_lab6.baskets where id>0;");
        conn.close();

    }



}
