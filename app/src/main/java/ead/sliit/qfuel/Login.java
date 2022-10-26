package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ead.sliit.qfuel.helper.DBHelper;

public class Login extends AppCompatActivity {
    EditText username,pw;
    Button signup,signin;
    DBHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.email);
        pw = findViewById(R.id.pass);
        signup = findViewById(R.id.btnregi);
        signin = findViewById(R.id.login);
        MyDB = new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();

                if(user_name.equals(""))
                {
                    Toast.makeText(Login.this, "Enter username", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = MyDB.vownercheckUsername(user_name);
                    if(result == false)
                    {
                        Toast.makeText(Login.this, "User does not exists.\n Kindly Register", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Login.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);

            }
        });

    }

}