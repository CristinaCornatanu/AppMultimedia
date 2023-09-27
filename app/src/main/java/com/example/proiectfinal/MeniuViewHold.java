package com.example.proiectfinal;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

public class MeniuViewHold extends RecyclerView.ViewHolder {
    TextView textView;
    public MeniuViewHold(@Nullable View itemView){
        super(itemView);

        textView=itemView.findViewById(R.id.titleTextView);
    }
}
