package com.finder.stotomas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.nameEditText)
    EditText mNameEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.descriptionEditText)
    EditText  mDescriptionEditText;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.urlEditText)
    EditText mUrlEditText;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.animeButton)
    Button mAnimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);

        int id= Objects.requireNonNull(getIntent().getExtras()).getInt("id");
        SqliteDatabase mDatabase = new SqliteDatabase(this);
        Persona mPersona = mDatabase.getPersona(id);

        mNameEditText.setText(mPersona.getName());

        mDescriptionEditText.setText(mPersona.getDescription());

        mUrlEditText.setText(mPersona.getUrl());

        mAnimeButton.setOnClickListener(v -> {
            String name = mNameEditText.getText().toString();
            String description = mDescriptionEditText.getText().toString();
            String url = mUrlEditText.getText().toString();

            Persona mNewPersona = new Persona(name, description, url);
            mDatabase.updatePersona(mNewPersona,id);

            Intent intent=new Intent(this, FinderActivity.class);
            startActivity(intent);
        });

    }
}