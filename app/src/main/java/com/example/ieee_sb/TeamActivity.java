package com.example.ieee_sb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TeamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TeamMember> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        recyclerView = findViewById(R.id.team_list);

        members = new ArrayList<>();
        members.add(new TeamMember("Dr.Annapurna D ","Branch Counsellor","annapurnad@pes.edu",R.drawable.annapurna));
        members.add(new TeamMember("Prof M S Anand","IEEE Chair","anandms@pes.edu",R.drawable.anand));
        members.add(new TeamMember("Shubham Saxena","Secretary","xyz@gmail.com",R.drawable.team));
        members.add(new TeamMember("Manasa U Hegde","WiE Chair","abc@gmail.com",R.drawable.team));

        layoutManager = new LinearLayoutManager(this);
        adapter = new TeamAdapter(members,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
