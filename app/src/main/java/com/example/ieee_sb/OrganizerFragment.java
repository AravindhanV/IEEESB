package com.example.ieee_sb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrganizerFragment extends Fragment {
    private View view;
    private Organizer organizer;
    private CircleImageView image;
    private TextView name,number;
    private ConstraintLayout layout;

    public OrganizerFragment(Organizer organizer){
        this.organizer = organizer;
    }

    public OrganizerFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contact_layout, container, false);
        name = view.findViewById(R.id.contact_name);
        number = view.findViewById(R.id.contact_number);
        image = view.findViewById(R.id.contact_image);
        layout =  view.findViewById(R.id.contact_layout);
        name.setText(organizer.getName());
        number.setText(organizer.getNumber());
        Picasso.get().load(organizer.getUrl()).fit().centerCrop().into(image);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+organizer.getNumber()));
                startActivity(i);
            }
        });

        return view;
    }
}
