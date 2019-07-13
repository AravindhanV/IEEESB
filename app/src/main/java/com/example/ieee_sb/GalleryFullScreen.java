package com.example.ieee_sb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class GalleryFullScreen extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_full_screen);

        image = findViewById(R.id.fullscreen_image);
        image.setImageResource(getIntent().getIntExtra("image",-5));
    }
}
