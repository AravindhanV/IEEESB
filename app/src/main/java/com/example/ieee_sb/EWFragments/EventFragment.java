package com.example.ieee_sb.EWFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ieee_sb.Event;
import com.example.ieee_sb.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventFragment extends Fragment {

    private ArrayList<Event> events;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.activity_events, container, false);
        recyclerView = view.findViewById(R.id.event_recycler);
        events = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("/events");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> fireevents = dataSnapshot.getChildren();
                for(DataSnapshot i : fireevents){
                    events.add(i.getValue(Event.class));
                    layoutManager = new LinearLayoutManager(getActivity());
                    adapter = new EWAdapter(events);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        events.add(new Event("Techquilla",new ArrayList<String>(),"Description Here",1,"FEB",2019,"3:30 PM"));
//        events.add(new Event("Wit Wars 2.0",new ArrayList<String>(),"Description Here",5,"MAY",2020,"4:15 PM"));

        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new EWAdapter(events);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
