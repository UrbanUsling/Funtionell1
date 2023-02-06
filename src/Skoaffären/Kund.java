package Skoaffären;

public class Kund {
    private int id;
    private String namn;
    private String lösenord;


    public Kund (int id, String namn, String lösenord){
        this.id=id;
        this.namn=namn;
        this.lösenord=lösenord;

    }

    public int getId() {
        return id;
    }

    public String getLösenord() {
        return lösenord;
    }

    public String getNamn() {
        return namn;
    }
}
