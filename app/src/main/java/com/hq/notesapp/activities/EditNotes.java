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

public class EditNotes extends BaseActivity {
    private DatabaseHandler dbHandler;
    RecyclerView mRecyclerView;
    ImageView mEditNotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        init();

        ArrayList<String> arrayList = new ArrayList();
        ArrayList<String> arrayListTime = new ArrayList();

        for (int i = 0; i < dbHandler.getNotes().size(); i++) {
            arrayList.add(dbHandler.getNotes().get(i).toString());
        }

        for (int i = 0; i < dbHandler.getTime().size(); i++) {
            arrayListTime.add(dbHandler.getTime().get(i).toString());
        }

        mRecyclerView.setAdapter(new NotesAdapter(arrayList, arrayListTime, true, this));
        mEditNotesButton.setVisibility(View.GONE);
    }

    @Override
    public void init() {
        super.init();
        dbHandler = new DatabaseHandler(EditNotes.this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEditNotesButton = (ImageView) findViewById(R.id.IVEditNotes);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}