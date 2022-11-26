package com.finder.stotomas;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinderActivity extends AppCompatActivity {


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    PersonaAdapter mPersonaAdapter;

    GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);

        ButterKnife.bind(this);
        Recycler();


        mFab.setOnClickListener(view -> {
            Intent intent=new Intent(FinderActivity.this, AddActivity.class);
            startActivity(intent);
        });

    }

    public void Recycler() {

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new GridLayoutManager(this, 1);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mPersonaAdapter = new PersonaAdapter(new ArrayList<>());

        Content();
    }

    private void Content() {

        SqliteDatabase mDatabase = new SqliteDatabase(this);
        List<Persona> mPersona= mDatabase.listPersona();


        if (mPersona.size() > 0) {
            mPersonaAdapter = new PersonaAdapter(mPersona);
        } else {
            ArrayList<Persona> personaEmpty = new ArrayList<>();
            mPersonaAdapter.addItems(personaEmpty);
        }

        mRecyclerView.setAdapter(mPersonaAdapter);

    }

}
