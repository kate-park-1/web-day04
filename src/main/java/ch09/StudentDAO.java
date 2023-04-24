package ch09;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
  Connection conn = null;
  PreparedStatement pstmt;

  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost/jwbookexdb?serverTimezone=Asia/Seoul";

  public void open(){
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(JDBC_URL,"root","1111");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void close() {
    try {
      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insert(Student s) {
  //public void insert(String username, String univ, String birth, String email){
    open();
    String sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, s.getUsername());
      pstmt.setString(2, s.getUniv());
      pstmt.setDate(3, s.getBirth());
      pstmt.setString(4, s.getEmail());
//      pstmt.setString(1, username);
//      pstmt.setString(2, univ);
//      pstmt.setDate(3, Date.valueOf(birth));
//      pstmt.setString(4, email);

      pstmt.executeUpdate();

    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public List<Student> findAll() {
    open();
    List<Student> students = new ArrayList<>();

    try {
      pstmt = conn.prepareStatement("select * from student");
      ResultSet rs = pstmt.executeQuery();

      while(rs.next()) {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setUsername(rs.getString("username"));
        s.setUniv(rs.getString("univ"));
        s.setBirth(rs.getDate("birth"));
        s.setEmail(rs.getString("email"));

        students.add(s);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return students;
  }
}