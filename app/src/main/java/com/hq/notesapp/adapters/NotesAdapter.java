package com.hq.notesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hq.notesapp.DatabaseHandler;
import com.hq.notesapp.R;

import java.util.ArrayList;

/**
 * @author HaiderQadir
 **/

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.viewHolder> {
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> dataTime = new ArrayList<>();
    DatabaseHandler dbHandler;
    Boolean flag;
    Context context;

    public NotesAdapter(ArrayList<String> data, ArrayList<String> dataTime, Boolean flag, Context context) {
        this.data = data;
        this.dataTime = dataTime;
        this.flag = flag;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        if (flag) {
            holder.mNotesTV.setText(data.get(position));
            holder.mTimeTV.setText(dataTime.get(position));
            dbHandler = new DatabaseHandler(context.getApplicationContext());

            holder.mNotesTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            holder.mDeleteNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbHandler.deleteItem(holder.mNotesTV.getText().toString());
                    Toast.makeText(v.getContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
                    holder.mDeleteNote.setImageDrawable(v.getResources().getDrawable(R.drawable.trash_can_solid_clicked));
                }
            });

            holder.mEditNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.mEditNote.setVisibility(View.VISIBLE);
                    holder.mConfirmEditNote.setVisibility(View.VISIBLE);

//                holder.buttonedit.setImageTintBlendMode(Color.parseColor(String.valueOf(R.color.white)));
                    holder.mEditNoteButton.setImageDrawable(v.getResources().getDrawable(R.drawable.pen_solid_clicked));
                }
            });

            holder.mConfirmEditNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHandler.updatenotes(holder.mNotesTV.getText().toString(), holder.mEditNote.getText().toString());
                    Toast.makeText(view.getContext(), "Edited Note Saved", Toast.LENGTH_SHORT).show();
                    holder.mNotesTV.setText(holder.mEditNote.getText().toString());
                    holder.mEditNote.setVisibility(View.GONE);
                    holder.mConfirmEditNote.setVisibility(View.GONE);
                    holder.mEditNoteButton.setImageDrawable(view.getResources().getDrawable(R.drawable.pen_solid));
                    holder.mDeleteNote.setImageDrawable(view.getResources().getDrawable(R.drawable.trash_can_solid));
                }
            });

        } else {
            holder.mNotesTV.setText(data.get(position));
            holder.mTimeTV.setText(dataTime.get(position));
            holder.mDeleteNote.setVisibility(View.GONE);
            holder.mEditNoteButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        TextView mNotesTV, mTimeTV;
        EditText mEditNote;
        Button mConfirmEditNote;
        ImageView mDeleteNote, mEditNoteButton;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mNotesTV = (TextView) itemView.findViewById(R.id.textviewNotes);
            mTimeTV = (TextView) itemView.findViewById(R.id.textviewTime);
            mEditNote = (EditText) itemView.findViewById(R.id.ETEditNote);
            mConfirmEditNote = (Button) itemView.findViewById(R.id.buttonConfirmEdit);
            mDeleteNote = (ImageView) itemView.findViewById(R.id.IVDeleteNote);
            mEditNoteButton = (ImageView) itemView.findViewById(R.id.IVEditNote);
        }
    }
}
