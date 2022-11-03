package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminTasksNAV extends AppCompatActivity {

    Button btnadd,btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tasks_nav);

        btnadd = findViewById(R.id.btnadd);
        btnupdate = findViewById(R.id.btnupdate);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminFuelDetails.class);
                startActivity(intent);

            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                startActivity(intent);

            }
        });
    }

}