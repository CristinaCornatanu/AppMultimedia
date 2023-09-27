package com.example.proiectfinal;

public class MeniuR {
    private int id;
    private String titlu;
    private String descriere;
    private String pret;
    private String categorie;

    public MeniuR(){

    }

    public MeniuR(String titlu, String descriere,String pret, String categorie) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.pret=pret;
        this.categorie=categorie;
    }

    public MeniuR(int id, String titlu, String descriere,String pret,String categorie) {
        this.id = id;
        this.titlu = titlu;
        this.descriere = descriere;
        this.pret=pret;
        this.categorie=categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    @Override
    public String toString() {
        return titlu;
    }
}
