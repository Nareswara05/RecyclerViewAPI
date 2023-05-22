package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailFilmPage extends AppCompatActivity {

    Intent i;
    MovieModel movieModel;
    TextView tvTeamName, tvteamShort, tvteamDesc;
    ImageView ivbadgeteams;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team_page);

        i = getIntent();
        movieModel = (MovieModel) i.getParcelableExtra("myteam");
        System.out.println("my team name : "+ movieModel.getMovieName());
        System.out.println("Team short name" + movieModel.getLanguage());
        tvTeamName = findViewById(R.id.tvteamname);
        tvteamShort = findViewById(R.id.tvteamShort);
        tvteamDesc = findViewById(R.id.tvteamDesc);
        ivbadgeteams = findViewById(R.id.ivbadgeteams);
        tvTeamName.setText(movieModel.getMovieName());
        tvteamShort.setText(movieModel.getLanguage());
        tvteamDesc.setText(movieModel.getOverview());
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieModel.getPosterPath()).into(ivbadgeteams);
    }}