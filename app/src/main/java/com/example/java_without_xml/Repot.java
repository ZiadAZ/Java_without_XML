package com.example.java_without_xml;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Repot extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repot);
        ImageView img=findViewById(R.id.img);
        TextView name=findViewById(R.id.name);
        TextView gender=findViewById(R.id.gender);
        TextView birthDate=findViewById(R.id.birth_date);
        TextView major=findViewById(R.id.major);

           img.setImageBitmap((Bitmap) getIntent().getExtras().get("image"));

          name.setText(getIntent().getStringExtra("name").toString());
        gender.setText(getIntent().getStringExtra("gender"));
        birthDate.setText(getIntent().getStringExtra("date").toString());
        major.setText(getIntent().getStringExtra("major").toString());
    }
}