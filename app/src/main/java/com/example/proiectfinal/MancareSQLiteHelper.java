package com.example.proiectfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MancareSQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VER = 1;//versiunea curenta a bazei de date
    private static final String DB_NAME = "Meniu";//numele bazei de date
    private static final String TABLE_NOTE = "Mancare";//numele tabelui(foii de calcul din excel) ce va contine felurile de mancare
    private static final String ID = "id";
    private static final String TITLU = "titlu";
    private static final String DESCRIERE= "descriere";
    private static final String PRET="pret";
    private static final String CATEGORIE="categorie";

    public MancareSQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //cream tabelul
        String sql = "CREATE TABLE " + TABLE_NOTE + "("
                + ID + " INTEGER PRIMARY KEY," + TITLU + " TEXT,"
                + DESCRIERE + " TEXT," + PRET + " TEXT," +CATEGORIE+ " TEXT" +")";

        db.execSQL(sql);//executa instructiunea de mai sus
    }
    public int getMeniuCount(){
        SQLiteDatabase db = getReadableDatabase();//pregateste baza de date pt a citi in ea

        String sql = "SELECT * FROM " + TABLE_NOTE;
        Cursor cursor = db.rawQuery(sql, null);

        int count = cursor.getCount();

        db.close();
        return count;
    }
    //Metoda folosita sa extragem din baza de date
    public MeniuR getMeniu(int id){
        SQLiteDatabase db = getReadableDatabase();
        MeniuR meniuR = null;
//extragem in obiectul cursor randurile din tabela specificata prin apelul metodei query
        //metoda query are parametrii:numele tabelei, coloanele pe care trebuie sa le selectam,
        // ID+"=?" reprezinta clauza WHERE care specifica ca trebuie selectata nota cu un anumit id,
        // semnul intrebarii este o modalitate de a specifica parametruu dinamici ai interogarii, furnizati ulterior
        //al patrulea parametru reprezinta valorile parametrilor dinamici specificati anterior in clauza Where
        //ultimii trei paraetrii sut optionali si permit specificarea unui criteriu de sortare a rezultatelor
        Cursor cursor = db.query(TABLE_NOTE,new String[]{ID,TITLU,DESCRIERE,PRET,CATEGORIE},
                ID + "=?", new String[]{String.valueOf(id)},null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
            meniuR = new MeniuR(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4) );
        }
        db.close();
        return meniuR;
    }
    //metoda de adaugare a unui fel de mancare
    public void addMeniu(MeniuR meniuR){
        SQLiteDatabase db = getWritableDatabase();//pregateste baza de date pt a scrie in ea

        ContentValues contentValues = new ContentValues();//obiect utilizat pt a stoca perechea de cheie-valoarea(TITLE, CONTENT)
        contentValues.put(TITLU,meniuR.getTitlu());
        contentValues.put(DESCRIERE, meniuR.getDescriere());
        contentValues.put(PRET,meniuR.getPret());
        contentValues.put(CATEGORIE,meniuR.getCategorie());
//adaugam valorile din obiectul ContentValues in baza de date, sub forma unui nou rand in tabele TABLE_NOTE
        db.insert(TABLE_NOTE,null,contentValues);
//se inchide conexiunea la baza de date
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);

        onCreate(db);
    }
    public void generateMock(){
        if(getMeniuCount() == 0){
            addMeniu(new MeniuR("Penne Pollo e funghi", "Ingrediente: Sos alb, piept de pui, gorgonzola, ciuperci, parmezan","30 lei","Paste"));
            addMeniu(new MeniuR("Spaghete Vegetariene", "Ingrediente: Sos rosu, dovlecel, vinete, ardei, ciuperci, brocolli, usturoi","25 lei","Paste"));
            addMeniu(new MeniuR("Spaghette frutti di mare", "Ingrediente: Midii, scoici, rosii, usturoi, lamaie","30 lei","Paste"));
            addMeniu(new MeniuR("Spaghete carbonara","Ingrediente: Sos alb, bacon, ou, parmezan","27 lei","Paste"));
            addMeniu(new MeniuR("Pizza Prosciutto Crudo","Ingrediente: Sos, mozzarella, prosciutto crudo","41 lei","Pizza"));
            addMeniu(new MeniuR("Pizza Breakfast","Ingrediente: sos alb, mozzarella, kaizer, ou, parmezan","42 lei","Pizza"));
            addMeniu(new MeniuR("Pizza Monte Verde","Ingrediente: sos, mozzarella, roșii, rucola, usturoi","40 lei","Pizza"));
            addMeniu(new MeniuR("Pizza Quattro Stagioni","Ingrediente: sos, mozzarella, ardei, ciuperci, măsline, salam, șuncă","39 lei","Pizza"));
            addMeniu(new MeniuR("Meniu Burger American","Contine: burger vita, cartofi, sos special, gramaj 500gr.","33 lei","Burger"));
            addMeniu(new MeniuR("Meniu Burger Time","Contine: burger vita, cartofi, sos special, gramaj 700gr.","39 lei","Burger"));
            addMeniu(new MeniuR("Meniu Burger Safari","Contine: burger pui, cartofi, sos special, gramaj 500gr.","30 lei","Burger"));
            addMeniu(new MeniuR("Meniu Burger Parma","Contine: burger vita, cartofi, sos special, gramaj 480gr.","35 lei","Burger"));
            addMeniu(new MeniuR("Salata Greceasca","Ingrediente: rosii, ceapa,ardei,castraveti, branza feta","23 lei","Salate"));
            addMeniu(new MeniuR("Salata Cezar","Ingrediente: salata verde, pui, rosii, parmezan, sos cezar, focaccia","28 lei","Salate"));
            addMeniu(new MeniuR("Salata mixta","Ingrediente: salata verde, rosii, castraveti, morcov, porumb, ulei de masline, otet balsamic","22 lei","Salate"));
            addMeniu(new MeniuR("Salata Capresse","Ingrediente: mozzarella, rosii, salata verde, ulei de masline, otet balsamic, oregano","26 lei","Salate"));
            addMeniu(new MeniuR("Cappucino","Ingrediente: cafea,crema lapte","16 lei","Bauturi"));
            addMeniu(new MeniuR("Cafea Ruseasca","Ingrediente: cafea, kahlua, zahar brun ,frisca vegetala","16 lei","Bautura"));
            addMeniu(new MeniuR("Milkshake Capsune","Ingrediente: capsuni, lapte, inghetata ","21 lei","Bauturi"));
            addMeniu(new MeniuR("Americano","Ingrediente: Campari, Martini, Sifon","21 lei","Bauturi"));
        }
    }

    public void delete(MeniuR meniuR) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NOTE, ID + " =?", new String[]{String.valueOf(meniuR.getId())});

        db.close();
    }


    public List<MeniuR> getAllMenu(){
        List<MeniuR> meniuList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_NOTE;
        Cursor cursor = db.rawQuery(sql,null);

        MeniuR meniuR = null;
        if(cursor.moveToFirst()){
            do{
                meniuR = new MeniuR();
                meniuR.setId(cursor.getInt(0));
                meniuR.setTitlu(cursor.getString(1));
                meniuR.setDescriere(cursor.getString(2));
                meniuR.setPret(cursor.getString(3));
                meniuList.add(meniuR);
            }
            while (cursor.moveToNext());
        }

        db.close();
        return meniuList;
    }
}
