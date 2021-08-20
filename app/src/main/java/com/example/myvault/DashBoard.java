package com.example.myvault;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        CardView cardViewCovid = (CardView) findViewById(R.id.cardViewCovid);
        CardView cardViewReminders = (CardView) findViewById(R.id.cardViewReminders);
        CardView cardViewTimeTable = (CardView) findViewById(R.id.cardViewTimeTable);

        cardViewCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCovid();
            }
        });

        cardViewReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReminders();
            }
        });

        cardViewTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeTable();
            }
        });
    }

    public void openCovid() {
        Intent intent = new Intent(this, CovidData.class);
        this.startActivity(intent);
    }

    public void openReminders() {
        Intent intent = new Intent(this, Reminders.class);
        this.startActivity(intent);
    }

    public void openTimeTable() {
        Intent intent = new Intent(this, TimeTable.class);
        this.startActivity(intent);
    }
}