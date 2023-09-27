package com.example.proiectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MeniuDetActivity extends AppCompatActivity {
    private Button btn;
    private TextView txtTitlu,txtDescriere,txtPret;
    private MeniuR meniuR=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_det);

        btn=findViewById(R.id.btnInapoi);
        txtTitlu=findViewById(R.id.textView);
        txtDescriere=findViewById(R.id.textView2);
        txtPret=findViewById(R.id.textView3);

        Intent intent = getIntent();
        Bundle bundle =  intent.getExtras();
        int noteId = bundle.getInt("NoteId");
        if(noteId == -1){//nu s-a selectat nici o nota, deci se va crea una noua
            meniuR = new MeniuR(-1,"","","","");
        }
        else {//s-a selectat deja o nota existenta
            MancareSQLiteHelper dao = new MancareSQLiteHelper(  this);//se obtine o instanta a obiectului prin intermediul caruia se poate accesa baza de date

            meniuR = dao.getMeniu(noteId);//obtinem detaliile notitei selectate pe baza valorii noteId
            //detaliile notitei le vom folosi pentru a initializa continutul campurilor de editare si afisare
            txtTitlu.setText(meniuR.getTitlu());
            txtDescriere.setText(meniuR.getDescriere());
            txtPret.setText(meniuR.getPret());
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeniuDetActivity.this,MeniuActivity.class);
                startActivity(intent);
            }
        });
    }
}
