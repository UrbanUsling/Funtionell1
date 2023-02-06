package Skoaffären;

public class Skor {
    private int id;
    private String namn;
    private String färg;
    private String märke;
    private int pris;
    private int storlek;
    private int antal;
    public Skor(int id, String namn, String färg, String märke, int pris, int storlek, int antal){
        this.id=id;
        this.namn = namn;
        this.färg = färg;
        this.märke = märke;
        this.pris = pris;
        this.storlek = storlek;
        this.antal = antal;
    }

    public int getId() {
        return id;
    }

    public String getNamn() {
        return namn;
    }

    public String getFärg() {
        return färg;
    }

    public String getMärke() {
        return märke;
    }

    public int getPris() {
        return pris;
    }

    public int getStorlek() {
        return storlek;
    }

    public int getAntal() {
        return antal;
    }
}
