package Skoaffären;

public class Ordernycklar {
    private int id;
    private int orderNr;
    public Ordernycklar(int id, int orderNr){
        this.id=id;
        this.orderNr = orderNr;
    }

    public int getId() {
        return id;
    }

    public int getOrderNr() {
        return orderNr;
    }
}
