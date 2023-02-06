package Skoaffären;

public class Beställningsskor {
    private int id;
    private int skorId;
    private int antal;
    public Beställningsskor(int id, int skorId, int antal){
        this.id=id;
        this.skorId=skorId;
        this.antal=antal;
    }

    public int getId() {
        return id;
    }

    public int getSkorId() {
        return skorId;
    }

    public int getAntal() {
        return antal;
    }
}
