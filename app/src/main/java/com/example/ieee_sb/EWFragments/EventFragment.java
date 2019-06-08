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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                events.add(dataSnapshot.getValue(Event.class));
                refreshList();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s){
                Event e = dataSnapshot.getValue(Event.class);
                events.set(searchEvent(e.getID()),e);
                refreshList();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Event e = dataSnapshot.getValue(Event.class);
                events.remove(searchEvent(e.getID()));
                refreshList();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            public int searchEvent(String s){
                int i=0;
                for (Event e : events) {
                    if (e.getID().equals(s)) {
                        break;
                    }
                    i++;
                }
                return i;
            }

            public void refreshList(){
                adapter = new EWAdapter(events);
                recyclerView.setAdapter(adapter);
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
