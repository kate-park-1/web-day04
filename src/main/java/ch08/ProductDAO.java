package ch08;

import ch09.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
  //conn
  Connection conn;
  // prepareStatement
  PreparedStatement ps=null;
  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost/jwbookdb?serverTimezone=Asia/Seoul";

  public void dbConn(){
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(JDBC_URL,"root","1111");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      ps.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // findAll
  public List<Product> findAll() {
    dbConn();
    List<Product> products = new ArrayList<>();

    try {
      ps = conn.prepareStatement("select * from product");
      ResultSet rs = ps.executeQuery();

      while(rs.next()) {
        Product p = new Product();
        p.setId(rs.getString("id"));
        p.setName(rs.getString("name"));
        p.setMaker(rs.getString("maker"));
        p.setPrice(rs.getInt("price"));
        p.setDate(rs.getString("date"));

        products.add(p);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return products;
  }

  // find
  public Product find(String id){
    Product p = new Product();
    dbConn();

    try {
      ps = conn.prepareStatement("select * from product where id = ?");
      ps.setString(1,"id");
      ResultSet rs = ps.executeQuery();

      p.setId(rs.getString("id"));
      p.setName(rs.getString("name"));
      p.setMaker(rs.getString("maker"));
      p.setPrice(rs.getInt("price"));
      p.setDate(rs.getString("date"));
      } catch (SQLException ex) {
      throw new RuntimeException(ex);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        close();
     }
    return p;
  }

  // update
  public String update(String id, String name, int price, String date){
    Product p = new Product();
    dbConn();

    try {
      ps = conn.prepareStatement("update product set name = ?, price = ? , date = ? " +
                                     "where id = ?");
      ps.setString(1,name);
      ps.setInt(2,price);
      ps.setString(3,date);
      ps.setString(4,id);
      ResultSet rs = ps.executeQuery();
    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return id;
  }
}
