package com.example.ieee_sb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class EventRegistrationActivity extends AppCompatActivity {

    private WebView page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);

        page = findViewById(R.id.event_register_web);
        page.loadUrl("https://www.google.com");
    }
}
