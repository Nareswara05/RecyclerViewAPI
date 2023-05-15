package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailTeamPage extends AppCompatActivity {

    Intent i;
    EPLTeamModel eplTeamModel;
    TextView tvTeamName, tvteamShort, tvteamDesc;
    ImageView ivbadgeteams;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team_page);

        i = getIntent();
        eplTeamModel = (EPLTeamModel) i.getParcelableExtra("myteam");
        System.out.println("my team name : "+ eplTeamModel.getTeamName());
        System.out.println("Team short name" + eplTeamModel.getStrTeamShort());
        tvTeamName = findViewById(R.id.tvteamname);
        tvteamShort = findViewById(R.id.tvteamShort);
        tvteamDesc = findViewById(R.id.tvteamDesc);
        ivbadgeteams = findViewById(R.id.ivbadgeteams);
        tvTeamName.setText(eplTeamModel.getTeamName());
        tvteamShort.setText(eplTeamModel.getStrTeamShort());
        tvteamDesc.setText(eplTeamModel.getStrDescriptionEN());
        Glide.with(this).load(eplTeamModel.getStrTeamBadge()).into(ivbadgeteams);
    }}