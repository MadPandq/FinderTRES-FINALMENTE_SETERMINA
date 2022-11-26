package com.finder.stotomas;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonaAdapter extends RecyclerView.Adapter<ViewHolder>{


    private final List<Persona> mPersonaList;

    public PersonaAdapter(List<Persona> personaList) {
        mPersonaList = personaList;
    }

    @Override
    public void onBindViewHolder(com.finder.stotomas.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.finder.stotomas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mPersonaList != null & mPersonaList.size() > 0) {
            return mPersonaList.size();
        } else {
            return 0;
        }
    }
//para agregar
    public void addItems(List<Persona> personaList) {
        mPersonaList.addAll(personaList);
        notifyDataSetChanged();
    }
//para eliminar
    public void deleteItem(int position) {
        if (mPersonaList != null & mPersonaList.size() > 0) {
            mPersonaList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.finder.stotomas.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.personaImageView)
        ImageView mAnimeImageView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.animeCardView)
        CardView mPersonaCardView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.deleteImageVIew)
        ImageView mDeleteImageVIew;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.editImageView)
        ImageView mEditImageView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.nameTextView)
        TextView mNameTextView;


        SqliteDatabase dataBase;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mAnimeImageView.setImageDrawable(null);
            mNameTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            Persona mPersona = mPersonaList.get(position);
            dataBase = new SqliteDatabase(itemView.getContext());

            if (mPersona.getUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(mPersona.getUrl())
                        .into(mAnimeImageView);
            }

            if (mPersona.getName() != null) {
                mNameTextView.setText(mPersona.getName());
            }

            mPersonaCardView.setOnClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("id",  mPersona.getId());
                itemView.getContext().startActivity(intent);
            });

            mEditImageView.setOnClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), EditActivity.class);
                intent.putExtra("id",  mPersona.getId());
                itemView.getContext().startActivity(intent);
            });

            mDeleteImageVIew.setOnClickListener(v -> {
                dataBase.deletePersona(mPersona.getId());
                deleteItem(position);
            });
        }
    }

}