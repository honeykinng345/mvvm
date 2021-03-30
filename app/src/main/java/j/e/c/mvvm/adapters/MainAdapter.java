package j.e.c.mvvm.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import j.e.c.mvvm.EditActivity;
import j.e.c.mvvm.MainActivity;
import j.e.c.mvvm.R;
import j.e.c.mvvm.models.Note;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    List<Note> notes;
    Context context;

    public MainAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.itemrec, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note note = notes.get(position);

        holder.text.setText(note.getText());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("Note_Id",note.getId());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("Are You Sure You Want To Delete It?");
                alert.setPositiveButton("YES", (dialogInterface, i) -> ((MainActivity)context).mainViewModel.deleteNote(notes.get(position)));
                alert.setNegativeButton("NO", (dialogInterface, i) -> {

                });
                alert.show();

            }

        });
   /*     holder.itemView.setOnLongClickListener(view -> {
          *//*  AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage("Are You Sure You Want To Delete It?");
            alert.setPositiveButton("YES", (dialogInterface, i) -> ((MainActivity)context).mainViewModel.deleteNote(notes.get(position)));
            alert.setNegativeButton("NO", (dialogInterface, i) -> {

            });
            alert.show();
            return false;*//*
        });*/
       /* holder.edit.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditorActivity.class);
            intent.putExtra("id", notes.get(position).getId());
            context.startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage("Are You Sure You Want To Delete It?");
            alert.setPositiveButton("YES", (dialogInterface, i) -> ((MainActivity)context).mainViewModel.deleteNote(notes.get(position)));
            alert.setNegativeButton("NO", (dialogInterface, i) -> {

            });
            alert.show();
            return false;
        });*/
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getItem(int adapterPosition) {
        return notes.get(adapterPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.edit)
        FloatingActionButton edit;
        @BindView(R.id.text)
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
