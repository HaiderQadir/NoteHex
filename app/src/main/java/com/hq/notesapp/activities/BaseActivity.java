package com.hq.notesapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hq.notesapp.R;
import com.hq.notesapp.interfaces.InitInterface;

/**
 * @author HaiderQadir
 **/

public class BaseActivity extends AppCompatActivity implements InitInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public void init() {
//        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Base Activity", Snackbar.LENGTH_LONG);
//        snackbar.show();
    }

}