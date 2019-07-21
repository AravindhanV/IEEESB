package com.example.ieee_sb.MainFragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ieee_sb.ProfileData;
import com.example.ieee_sb.R;
import com.example.ieee_sb.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private ImageView logout;
    private View view;
    private TextView rate,usn,id,idlabel,name,delete,query,querylabel,feedback,feedbacklabel;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_profile, container, false);
        logout = view.findViewById(R.id.profile_logout);
        rate = view.findViewById(R.id.profile_rate);
        usn = view.findViewById(R.id.profile_usn);
        id = view.findViewById(R.id.profile_id);
        idlabel = view.findViewById(R.id.profile_id_label);
        name = view.findViewById(R.id.profile_name);
        delete = view.findViewById(R.id.profile_delete_account);
        feedback = view.findViewById(R.id.profile_feedback);
        feedbacklabel = view.findViewById(R.id.profile_feedback_label);
        querylabel = view.findViewById(R.id.profile_query_label);
        query = view.findViewById(R.id.profile_query);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("/userinfo/"+firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProfileData data = dataSnapshot.getValue(ProfileData.class);
                name.setText(data.getName());
                usn.setText(data.getUsn());
                if(data.getId().isEmpty()){
                    id.setText("Not an IEEE Member");
                }
                else{
                    id.setText(data.getId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(),"Thanks for the Rating",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=org.ieee.mobile.mcoe.ieee&hl=en_IN"));
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });

        query.setOnClickListener(queryListener);
        querylabel.setOnClickListener(queryListener);

        feedback.setOnClickListener(feedbackistener);
        feedbacklabel.setOnClickListener(feedbackistener);

        return view;
    }

    public void showPopUp(){
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.activity_pop_up_delete);
        Button delete = dialog.findViewById(R.id.delete_delete);
        Button cancel = dialog.findViewById(R.id.delete_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }

    public void deleteAccount(){
        FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                getActivity().finish();
                startActivity(new Intent(getActivity(),SignInActivity.class));
            }
        });
    }

    public View.OnClickListener queryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:"+query.getText().toString()));
            i.putExtra(Intent.EXTRA_EMAIL, "");
            i.putExtra(Intent.EXTRA_SUBJECT, "");
            startActivity(i);
        }
    };

    public View.OnClickListener feedbackistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:"+feedback.getText().toString()));
            i.putExtra(Intent.EXTRA_EMAIL, "");
            i.putExtra(Intent.EXTRA_SUBJECT, "IEEE SB App Feedback");
            startActivity(i);
        }
    };

}

