package com.example.proiectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeniuActivity extends AppCompatActivity {
    Button button,button2,button3;
    RecyclerView notesRecyclerView;
    List<MeniuR> noteList;
    MeniuAdapter meniuAdapter;
    ActivityResultLauncher activityResultLauncherDet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu);
        button=findViewById(R.id.button10);
        button2=findViewById(R.id.button11);
        button3=findViewById(R.id.button12);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeniuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeniuActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MeniuActivity.this,ContactActivity.class);
                startActivity(intent);
            }
        });


        MancareSQLiteHelper dao = new MancareSQLiteHelper(this);
        dao.generateMock();

        notesRecyclerView = findViewById(R.id.meniuRecyclerView);

        noteList = dao.getAllMenu();

        meniuAdapter = new MeniuAdapter(noteList, this);

        notesRecyclerView.setAdapter(meniuAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRecyclerView.setHasFixedSize(true);

        meniuAdapter.setOnItemClickListener(new MeniuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                detMeniu(position);
            }
        });
        activityResultLauncherDet = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            noteList.clear();
                            MancareSQLiteHelper dao = new MancareSQLiteHelper(getApplicationContext());
                            noteList.addAll(dao.getAllMenu());
                            meniuAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    private void detMeniu(int position){
        Intent intent = new Intent(this,MeniuDetActivity.class);
        intent.putExtra("NoteId", noteList.get(position).getId());
        activityResultLauncherDet.launch(intent);
    }
}
