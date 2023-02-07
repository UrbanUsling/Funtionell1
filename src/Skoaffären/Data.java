package Skoaffären;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Data{
        private static String connection;
        private static String username;
        private static String password;


    public void getLogin() {
            Properties p = new Properties();
            try  {
                p.load(new FileInputStream("src/Skoaffären/settings.properties"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            connection = p.getProperty("connection"," ");
            username = p.getProperty("username", " ");
            password = p.getProperty("password"," ");
        }

    private static List<Kund> namnen= new ArrayList<>();
    private static List<Skor> skorna = new ArrayList<>();
    private static List<Orderdata> orderdatas = new ArrayList<>();
    private static List<Ordernycklar> ordernycklars= new ArrayList<>();
    private static List<Beställningsskor> beställningsskors = new ArrayList<>();

    public static List<Orderdata> getOrderdatas() {
        return orderdatas;
    }

    public static List<Ordernycklar> getOrdernycklars() {
        return ordernycklars;
    }

    public static List<Beställningsskor> getBeställningsskors() {
        return beställningsskors;
    }

    public static List<Kund> getNamnen() {
        return namnen;
    }

    public static List<Skor> getSkorna() {
        return skorna;
    }


    public void connectToAndQueryDatabase() {
        try (Connection con = DriverManager.getConnection(connection,
                username,
                password);
             Statement stmt = con.createStatement();
             Statement stmt2 = con.createStatement();
             Statement stmt3 = con.createStatement();
             Statement stmt4 = con.createStatement();
             Statement stmt5 = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, namn, lösenord FROM Kunder");
             ResultSet rs2 = stmt2.executeQuery("SELECT id, namn, färg, märke, pris, storlek, antal FROM Skor");
             ResultSet rs3 = stmt3.executeQuery("SELECT ordernr, datum, kundid FROM Orderdata");
             ResultSet rs4= stmt4.executeQuery("SELECT id, ordernr FROM Ordernycklar");
             ResultSet rs5 = stmt5.executeQuery("SELECT id, skorid, antal FROM Beställningsskor");
        ) {
            while (rs.next()) {
                int i1 = rs.getInt("Id");
                String s1 = rs.getString("namn");
                String s2 = rs.getString("lösenord");
                namnen.add(new Kund(i1, s1, s2));
            }
            while (rs2.next()) {
                int i1 = rs2.getInt("Id");
                String s1 = rs2.getString("namn");
                String s2 = rs2.getString("färg");
                String s3 = rs2.getString("märke");
                int i2 = rs2.getInt("pris");
                int i3 = rs2.getInt("storlek");
                int i4 = rs2.getInt("antal");
                skorna.add(new Skor(i1, s1, s2, s3, i2, i3, i4));
            }
            while (rs3.next()) {
                int i1 = rs3.getInt("orderNr");
                LocalDate d1 = rs3.getDate("datum").toLocalDate();
                int i2 = rs3.getInt("kundId");
                orderdatas.add(new Orderdata(i1, d1, i2));
            }
            while (rs4.next()) {
                int i1 = rs4.getInt("Id");
                int i2 = rs4.getInt("OrderNr");
                ordernycklars.add(new Ordernycklar(i1, i2));
            }
            while (rs5.next()) {
                int i1 = rs5.getInt("Id");
                int i2 = rs5.getInt("SkorId");
                int i3 = rs5.getInt("antal");
                beställningsskors.add(new Beställningsskor(i1, i2, i3));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void connectToSP(int id, int skorId, int kundId) throws SQLException {

        try (Connection con = DriverManager.getConnection(connection,
                username,
                password);
             CallableStatement stmt3 = con.prepareCall("CALL AddToChart(?, ?, ?)");

        ) {
            stmt3.setInt(1,id);
            stmt3.setInt(2,skorId);
            stmt3.setInt(3,kundId);
            stmt3.executeQuery();
        }
    }
    }

