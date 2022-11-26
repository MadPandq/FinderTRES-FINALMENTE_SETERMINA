package com.finder.stotomas;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.finder.stotomas.SqliteDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    EditText mNameEditText;

    @BindView(R.id.descriptionEditText)
    EditText mDescriptionEditText;

    @BindView(R.id.urlEditText)
    EditText mUrlEditText;

    @BindView(R.id.animeButton)
    Button mAnimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        SqliteDatabase dataBase = new SqliteDatabase(this);

        mAnimeButton.setOnClickListener(v -> {
            String name = mNameEditText.getText().toString();
            String description = mDescriptionEditText.getText().toString();
            String url = mUrlEditText.getText().toString();

            Persona mNewPersona = new Persona();
            dataBase.newPersona(mNewPersona);

            Intent intent=new Intent(this, FinderActivity.class);
            startActivity(intent);
        });

    }
}
