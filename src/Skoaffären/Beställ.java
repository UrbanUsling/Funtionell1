package Skoaffären;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Beställ {
    private Skor skor1;
    private Kund kund1;
    public static List<Orderdata> ordrar = new ArrayList<>();
    private static List<Ordernycklar> nycklars = new ArrayList<>();
    private static Ordernycklar nyckel1;
    private static List<Beställningsskor> beställningar = new ArrayList<>();
    private static Beställningsskor beställ1;

    public static List<Ordernycklar> getNycklars() {
        return nycklars;
    }

    public static List<Beställningsskor> getBeställningar() {
        return beställningar;
    }

    public void inloggning() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Skriv in namn");
        String namn = scan.nextLine();
        System.out.println("Skriv in lösenord");
        String lösen = scan.nextLine();
        Data.getNamnen().stream().filter(f->(f.getNamn().equals(namn) &&
                f.getLösenord().equals(lösen))).forEach(f->kund1=f);
        if(kund1==null){
            inloggning();
        }
    }
    public void väljSkor() throws SQLException {
        Data.getSkorna().stream()
                .map(skor -> skor.getNamn()+ ", " +skor.getFärg() + ", "+ skor.getMärke() + ", " + skor.getPris() + "kr, storlek " + skor.getStorlek())
                .forEach(text -> System.out.println(text));
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Skriv namnet på skon du vill lägga till i beställningen");
        String namn = scan2.nextLine();
        Data.getSkorna().stream().filter(f-> f.getNamn().equals(namn)).forEach(f-> skor1=f);
        if(skor1!=null){
            hittaId(kund1.getId(), skor1.getId());
        }else{
            väljSkor();
        }
    }
    public void hittaId(int KundId, int skorId) throws SQLException {
        Returen r = (a,b) -> a == b;
        Data.getOrderdatas().stream().filter(f->r.compare(f.getKundId(), (KundId))).forEach(f-> ordrar.add(f));
        if(ordrar.isEmpty()){
            StoredProceduren.connectToSP(-1, skor1.getId(), kund1.getId());
            System.out.println("En ny beställning med ett nytt ordernummer skapades");
            System.exit(0);
        }
        for (Orderdata O : ordrar ) {
            Data.getOrdernycklars().stream().filter(f -> r.compare(f.getOrderNr(), O.getOrderNr()))
                    .forEach(f -> nycklars.add(f));
        }
        Data.getBeställningsskors().stream().filter(f->r.compare(f.getSkorId(), (skorId))).forEach(f-> beställningar.add(f));
        if(!beställningar.isEmpty()){
            for (Ordernycklar O : nycklars) {
                getBeställningar().stream().filter(f->r.compare(f.getId(), O.getId())).forEach(f->beställ1=f);
            }
        }
        if(beställ1==null){
            StoredProceduren.connectToSP(getNycklars().get(0).getId(), skor1.getId(), kund1.getId());
            System.out.print("En ny produkt läggs till i existerande order");
            System.exit(0);
        }else{
            StoredProceduren.connectToSP(beställ1.getId(), skor1.getId(), kund1.getId());
            System.out.print("Ännu ett exemplar av denna sko las till i din beställning");
            System.exit(0);
        }


    }
}
