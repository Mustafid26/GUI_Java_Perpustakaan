import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String user = "root";
    static String pass = "";
    static String url = "jdbc:mysql://localhost:3306/dbperpustakaan";
    static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Terkoneksi Ke Database");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver tidak ada: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("JDBC Driver not found", e);
        } catch (SQLException e) {
            System.err.println("Gagal koneksi ke database: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Gagal koneksi ke database", e);
        }
        return conn;
    }
}
