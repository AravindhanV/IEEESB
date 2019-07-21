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
import android.widget.TextView;

import com.example.ieee_sb.Data;
import com.example.ieee_sb.Event;
import com.example.ieee_sb.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class WorkshopFragment extends Fragment {

    private ArrayList<Event> workshops;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private TextView empty;

    private TextView empty1;
    private RecyclerView recyclerView1;
    private RecyclerView.LayoutManager layoutManager1;
    private RecyclerView.Adapter adapter1;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.activity_workshops, container, false);
        recyclerView = view.findViewById(R.id.workshop_recycler);
        recyclerView1 = view.findViewById(R.id.workshop_recycler1);
        empty = view.findViewById(R.id.workshop_empty_label_upcoming);
        empty1 = view.findViewById(R.id.workshop_empty_label_past);
        Data.workshops = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("/workshops");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Data.workshops.add(dataSnapshot.getValue(Event.class));
                refreshList();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s){
                Event e = dataSnapshot.getValue(Event.class);
                Data.workshops.set(searchEvent(e.getID()),e);
                refreshList();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Event e = dataSnapshot.getValue(Event.class);
                Data.workshops.remove(searchEvent(e.getID()));
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
                for (Event e : Data.workshops) {
                    if (e.getID().equals(s)) {
                        break;
                    }
                    i++;
                }
                return i;
            }

            public void refreshList(){
                ArrayList<Event> upcoming = new ArrayList<>();
                ArrayList<Event> past = new ArrayList<>();
                Calendar current = Calendar.getInstance();

                for(Event e : Data.workshops){
                    Calendar date = Calendar.getInstance();
                    date.set(e.getYear(),e.getMonth()-1,e.getDate());
                    if(current.after(date)){
                        past.add(e);
                    }
                    else{
                        upcoming.add(e);
                    }
                }

                setEmptyText(upcoming,empty);
                setEmptyText(past,empty1);

                adapter = new WorkshopAdapter(upcoming,getActivity());
                recyclerView.setAdapter(adapter);

                adapter1 = new WorkshopAdapter(past,getActivity());
                recyclerView1.setAdapter(adapter1);
            }

            public void setEmptyText(ArrayList<Event> list, TextView text){
                if(list.size()==0){
                    text.setVisibility(View.VISIBLE);
                }
                else{
                    text.setVisibility(View.GONE);
                }
            }
        });

        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new WorkshopAdapter(Data.workshops,getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutManager1 = new LinearLayoutManager(getActivity());
        adapter1 = new WorkshopAdapter(Data.workshops,getActivity());
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(adapter1);

        return view;
    }
}
