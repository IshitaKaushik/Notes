package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends ListAdapter<Note,NotesViewHolder> {
    private List<Note> arrayList=new ArrayList<>();
    private onClicked click;
    public NotesAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback,onClicked clicked){
        super(diffCallback);
            click=clicked;


    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item,parent,false);
        NotesViewHolder notesViewHolder=new NotesViewHolder(view);
        notesViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 click.onClicking(arrayList.get(notesViewHolder.getAdapterPosition()));
            }
        });
        notesViewHolder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click.onClickingtick(arrayList.get(notesViewHolder.getAdapterPosition()),notesViewHolder.editText.getText().toString());
            }
        });
        notesViewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!notesViewHolder.editText.isShown()){
                    notesViewHolder.editText.setVisibility(View.VISIBLE);
                    notesViewHolder.b.setVisibility(View.VISIBLE);
                }
                else{
                    notesViewHolder.editText.setVisibility(View.GONE);
                    notesViewHolder.b.setVisibility(View.GONE);
                }
            }
        });
        return notesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note=arrayList.get(position);
        holder.textView.setText(note.getNote());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void update(List<Note> n){
        arrayList.clear();
        arrayList.addAll(n);
        notifyDataSetChanged();
    }
    static class WordDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNote().equals(newItem.getNote());
        }
    }
}
class NotesViewHolder extends RecyclerView.ViewHolder{

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    TextView textView=itemView.findViewById(R.id.text);
    ImageView imageView=itemView.findViewById(R.id.image);
    ImageView imageView1=itemView.findViewById(R.id.imageedit);
    EditText editText=itemView.findViewById(R.id.editnote);
    ImageView b=itemView.findViewById(R.id.buttonedit);

}
interface onClicked{
    void onClicking(Note note);
    void onClickingtick(Note note,String text);
}

