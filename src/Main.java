
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;


public class Main {
    private Skor skor1;
    private Kund kund1;
    public Main() throws SQLException {
            connectToAndQueryDatabase("root", "United3378");
            inloggning();
            väljSkor();
            connectToSP("root","United3378");

    }
    List<Kund> namnen= new ArrayList<>();
    List<Skor> skorna = new ArrayList<>();

    public List<Kund> getNamnen () {
        return namnen;
    }

    public List<Skor> getSkorna() {
        return skorna;
    }

    public void connectToAndQueryDatabase(String username,
                                          String password) throws SQLException {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skoaffären?serverTimezone" +
                        "=UTC&useSSL=false&allowPublicRetrieval=true",
                username,
                password);
             Statement stmt = con.createStatement();
             Statement stmt2 = con.createStatement();
             CallableStatement stmt3 = con.prepareCall("CALL AddToChart(?, ?, ?)");
             ResultSet rs = stmt.executeQuery("SELECT id, namn, lösenord FROM Kunder");
             ResultSet rs2 = stmt2.executeQuery("SELECT id, namn, färg, märke, pris, storlek, antal FROM Skor");
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
            }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
    public void connectToSP(String username,
                                              String password) throws SQLException {


        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skoaffären?serverTimezone" +
                        "=UTC&useSSL=false&allowPublicRetrieval=true",
                username,
                password);
             CallableStatement stmt3 = con.prepareCall("CALL AddToChart(?, ?, ?)");

        ) {
            stmt3.setInt(1,111);
            stmt3.setInt(2,skor1.getId());
            stmt3.setInt(3,kund1.getId());
            stmt3.executeQuery();
            System.out.println("Hit");
        }
    }
        public void inloggning() {
            Scanner scan = new Scanner(System.in);
            System.out.println("Skriv in namn");
            String namn = scan.nextLine();
            System.out.println("Skriv in lösenord");
            String lösen = scan.nextLine();
            for (Kund k : getNamnen()) {
                if (k.getNamn().equals(namn) && k.getLösenord().equals(lösen)) {
                    kund1=k;
                    getSkorna().forEach(skor -> System.out.println(skor.getNamn()+ ", " +skor.getFärg()
                    + ", "+ skor.getMärke() + ", " + skor.getPris() + "kr, storlek " + skor.getStorlek()));
                    break;
                }else if(k.getNamn().equals(namn)){
                    System.out.println("fel lösen");
                    inloggning();
                }else {
                    System.out.println("Kunden finns inte");
                    inloggning();
                }
            }
        }
        public void väljSkor() throws SQLException {
            Scanner scan2 = new Scanner(System.in);
            System.out.println("Skriv namnet på skon du vill lägga till i beställningen");
            String namn = scan2.nextLine();
            skorna.stream().filter(f-> f.getNamn().equals(namn)).forEach(f-> skor1=f);
        }
       /* PreparedStatement stmt2 = con.prepareStatement("SELECT Id FROM Kunder where namn = ?, lösenord = ?");
        Scanner scan = new Scanner(System.in);
        System.out.println("Skriv in namn");
        String namn = scan.nextLine();
        System.out.println("Skriv in lösenord");
        String lösen = scan.nextLine();
        stmt2.setString(1, namn);
        stmt2.setString(2, lösen);
        Inlogg I1 = s -> s.();

        ResultSet rs = stmt.executeQuery("SELECT namn, lösenord FROM Kunder");
        Consumer n= t-> System.out.println(t);
        while (rs.next()) {
            int x = rs.getInt("id");
            int s = rs.getInt("orderNr");
            namnen.add(s);
            n.accept(s);
        }
        namnen.stream().filter(z->z>115).forEach(z-> System.out.print(z + " "));*/


                public static void main (String[]args) throws SQLException {
                    new Main();
                }


            }