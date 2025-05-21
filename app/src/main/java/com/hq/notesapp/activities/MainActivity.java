package com.hq.notesapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.hq.notesapp.DatabaseHandler;
import com.hq.notesapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Haider Qadir
 **/

public class MainActivity extends BaseActivity {

    EditText mAddNoteEditText;
    ImageView mAddNoteButton;
    ImageView mViewNotesButton;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mViewNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllNotesActivity.class);
                startActivity(intent);
            }
        });

        mAddNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                date.getTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a dd MMM yyyy");
                String dateString = simpleDateFormat.format(date);
                if (!mAddNoteEditText.getText().toString().equals("")) {
                    dbHandler.addNewNote(mAddNoteEditText.getText().toString(), dateString);
                    Intent intent = new Intent(getApplicationContext(), AllNotesActivity.class);
                    startActivity(intent);

                    mAddNoteEditText.setText("");
                    mAddNoteEditText.clearFocus();

                } else {
                    Toast.makeText(MainActivity.this, "Empty Note cannot be Added.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void init() {
        super.init();
        dbHandler = new DatabaseHandler(MainActivity.this);
        mAddNoteEditText = this.findViewById(R.id.editTextAddNote);
        mAddNoteButton = this.findViewById(R.id.buttonAddNote);
        mViewNotesButton = this.findViewById(R.id.buttonViewNotes);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit ?");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(getApplicationContext(), "Continue Making Notes",
                        Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();

            }
        }).setCancelable(false);

        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Exit");
        alert.show();

    }
}