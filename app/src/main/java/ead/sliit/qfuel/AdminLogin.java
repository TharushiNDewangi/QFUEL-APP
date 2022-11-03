package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ead.sliit.qfuel.helper.DBHelper;

public class AdminLogin extends AppCompatActivity {
    EditText username,pw;
    Button signup,signin;
    DBHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.adminemail);
        pw = findViewById(R.id.adminpass);
        signup = findViewById(R.id.adminbtnregi);
        signin = findViewById(R.id.adminlogin);
        MyDB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();

                if(user_name.equals(""))
                {
                    Toast.makeText(AdminLogin.this, "Enter username", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = MyDB.sownercheckUsername(user_name);
                    if(result == false)
                    {
                        Toast.makeText(AdminLogin.this, "User does not exists.\n Kindly Register", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), AdminTasksNAV.class);
                        startActivity(intent);
                    }
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Login.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminSignup.class);
                startActivity(intent);

            }
        });

    }

}