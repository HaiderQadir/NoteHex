package com.hq.notesapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hq.notesapp.DatabaseHandler;
import com.hq.notesapp.R;
import com.hq.notesapp.adapters.NotesAdapter;

import java.util.ArrayList;

/**
 * @author Haider Qadir
 **/

public class AllNotesActivity extends BaseActivity {

    private DatabaseHandler dbHandler;
    ImageView mEditNotesButton;
    RecyclerView mRecyclerView;
    ArrayList<String> arrayList;
    ArrayList<String> arrayListTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        init();
        for (int i = 0; i < dbHandler.getNotes().size(); i++) {
            arrayList.add(dbHandler.getNotes().get(i).toString());
        }

        for (int i = 0; i < dbHandler.getTime().size(); i++) {
            arrayListTime.add(dbHandler.getTime().get(i).toString());
        }

        mRecyclerView.setAdapter(new NotesAdapter(arrayList, arrayListTime, false, this));

        mEditNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditNotes.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void init() {
        super.init();
        arrayList = new ArrayList();
        arrayListTime = new ArrayList();
        dbHandler = new DatabaseHandler(AllNotesActivity.this);
        mEditNotesButton = (ImageView) findViewById(R.id.IVEditNotes);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}