package Skoaffären;

import java.time.LocalDate;


public class Orderdata {
    private int orderNr;
    private LocalDate datum;
    private int kundId;
    public Orderdata(int orderNr, LocalDate datum, int kundId){
        this.orderNr= orderNr;
        this.datum=datum;
        this.kundId = kundId;
    }

    public int getKundId() {
        return kundId;
    }

    public int getOrderNr() {
        return orderNr;
    }

    public LocalDate getDatum() {
        return datum;
    }
}
