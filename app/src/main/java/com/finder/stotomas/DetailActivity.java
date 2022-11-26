package com.finder.stotomas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.nameTextView)
    TextView mNameTextVIew;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.descriptionTextView)
    TextView  mDescriptionTextView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.personaImageView)
    ImageView  mPersonaImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



            ButterKnife.bind(this);

            int id= Objects.requireNonNull(getIntent().getExtras()).getInt("id");
            SqliteDatabase mDatabase = new SqliteDatabase(this);
            Persona mPersona = mDatabase.getPersona(id);

            mNameTextVIew.setText(mPersona.getName());

            mDescriptionTextView.setText(mPersona.getDescription());

            Glide.with(this.getApplicationContext())
                .load(mPersona.getUrl())
                .into(mPersonaImageView);

    }
}
