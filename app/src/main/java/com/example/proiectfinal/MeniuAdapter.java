package com.example.proiectfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//metoda NoteAdapter utilizata pt a afisa o lista de obiecte Note intr-un RecyclerView unde fiecare element din lista este afisat intr-un list_item
//clasa NOteAdapter are un tip generic NoteViewHolder care specifica clasa holder-ului de vizualizare(View Holder) pt RecyclerView
public class MeniuAdapter extends RecyclerView.Adapter<MeniuAdapter.NoteViewHolder> {

    private List<MeniuR> noteList;
    private Context context;
    private OnItemClickListener listener;

    interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public MeniuAdapter(List<MeniuR> noteList) {
        this.noteList = noteList;
    }

    public MeniuAdapter(List<MeniuR> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }
    //metoda onCreateViewHolder utilizata pentru a afisa o lista de obiecte de tipul Note pe ecran
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.meniu_list_item,parent,false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view);//se defineste un nou obiect pt fiecare element din lista,
        // astfel incat elementele sa poata fi afisate in mod corespunzatod

        return noteViewHolder;
    }
    //metoda apelata de RecyclerView pt fiecare element din lista si este utilizata pt a actualiza vizualizarea elementelor
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        MeniuR note = noteList.get(position);//se obtine ob note corespunzator pozitiei curente
        holder.titleTextView.setText(note.getTitlu());//se seteaza textul titleTextView(element vizual din design-ul de lista) pt a afisa titlul notei respective
    }
    //metoda getItemCount returneaza nr total de elemente din lista de note care trebuie afisate in recyclerView
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView,getAdapterPosition());
                }
            });
//setam un listener de evenimente de lunga durata pe un element vizual (la apasarea lunga pe un item va afisa un mesaj / caseta de dialog referitor la stergerea notitei)
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    new AlertDialog.Builder(itemView.getContext())
                            .setMessage("Delete " + noteList.get(position).getTitlu() + "?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    MancareSQLiteHelper dao = new MancareSQLiteHelper(itemView.getContext());//dao = data access object
                                    dao.delete(noteList.get(position));
                                    noteList.remove(position);
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("No",null)
                            .show();


                    return true;
                }
            });
        }
    }

}

