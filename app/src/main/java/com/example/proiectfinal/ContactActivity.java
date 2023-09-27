package com.example.proiectfinal;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ContactActivity extends AppCompatActivity {
    Button button,button2,button3,btn,btn1,btn2,btnCall,btnEmail;
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1 ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        button=findViewById(R.id.button4);
        button2=findViewById(R.id.button5);
        button3=findViewById(R.id.button6);
        btn=findViewById(R.id.btnFacebook);
        btn1=findViewById(R.id.btnYoutube);
        btn2=findViewById(R.id.btnInstagram);
        btnCall=findViewById(R.id.btnCall);
        btnEmail=findViewById(R.id.btnEmail);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContactActivity.this,MeniuActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://www.facebook.com/localsibiu/";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://www.youtube.com/@bonappetit";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://www.instagram.com/localsibiu/?hl=ro";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoIt();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "cristinaangela.cornatanu@gmail.com"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"cristinaangela.cornatanu@gmail.com"});
                startActivity(intent);
            }
        });
        }

    private void onDoIt() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( (Activity) this, new
                            String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE_PERMISSION);

        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+40)754 197 154"));
            startActivity(intent);
    }
}
}
