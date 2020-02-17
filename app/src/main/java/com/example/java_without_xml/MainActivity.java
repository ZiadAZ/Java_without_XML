package com.example.java_without_xml;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    LinearLayout linearLayout0;
    ImageView image;
    Button textDate;
    public String gender;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout0 = new LinearLayout(this);
        linearLayout0.setOrientation(LinearLayout.VERTICAL);
        linearLayout0.setGravity(1);
        linearLayout0.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout0.setWeightSum(10);

        image = new ImageView(this);
        image.setLayoutParams(param(LinearLayout.LayoutParams.MATCH_PARENT, 0, 3));
        linearLayout0.addView(image);

        Button btn_img = new Button(this);
        btn_img.setText("الكاميرا");
        btn_img.setLayoutParams(param(300, 0, 1));
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });
        linearLayout0.addView(btn_img);

        final EditText nameText = new EditText(this);
        nameText.setPadding(0, 30, 0, 5);
        nameText.setTextSize(25);
        nameText.setLayoutParams(param(LinearLayout.LayoutParams.WRAP_CONTENT, 0, 1));
        linearLayout0.addView(nameText);


        RadioGroup RG = new RadioGroup(this);
        RG.setPadding(0, 30, 0, 5);
        RG.setOrientation(LinearLayout.HORIZONTAL);
        RG.setLayoutParams(param(LinearLayout.LayoutParams.WRAP_CONTENT, 0, 1));
        final RadioButton RBMale = new RadioButton(this);
        RBMale.setText("ذكر");
        RBMale.setTextSize(30);
        final RadioButton RBFemale = new RadioButton(this);
        RBFemale.setText("أنثى");
        RBFemale.setTextSize(30);

        RG.addView(RBMale);
        RG.addView(RBFemale);
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (RBMale.isChecked())
                    gender = "ذكر";
                else if (RBFemale.isChecked())
                    gender = "أنثى";
            }
        });

        linearLayout0.addView(RG);

        textDate = new Button(this);
        textDate.setTextSize(30);
        textDate.setLayoutParams(param(LinearLayout.LayoutParams.WRAP_CONTENT, 0, 1));
        linearLayout0.addView(textDate);
        textDate.setHint("Enter Birth Date");
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

        ArrayList<String> arrList = new ArrayList<>();
        arrList.add("إختار التخصص");
        arrList.add("CS");
        arrList.add("IS");
        arrList.add("IT");
        final Spinner list = new Spinner(this);
        list.setLayoutParams(param(400, 0, 1));
        list.setBackgroundColor(Color.GRAY);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrList);
        list.setAdapter(adp1);


        linearLayout0.addView(list);

        Button btn_send = new Button(this);
        btn_send.setText("ارسال");
        btn_send.setLayoutParams(param(300, 0, 1));
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Repot.class);
                i.putExtra("image", bitmap);
                i.putExtra("name", nameText.getText().toString()); //nameText.getText().toString()
                i.putExtra("date", textDate.getText().toString());//textDate.getText().toString()
                i.putExtra("gender", gender);
                i.putExtra("major", list.getSelectedItem().toString()); //
                startActivity(i);

            }
        });
        linearLayout0.addView(btn_send);

        setContentView(linearLayout0);

    }


    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    LinearLayout.LayoutParams param(int w, int h, int s) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(w, h, s);
        return param;
    }

    void dateDialog() {
        DatePickerDialog date = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        date.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        textDate.setText(year + " / " + month + " / " + dayOfMonth);
    }
}
