package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ead.sliit.qfuel.helper.DBHelper;

public class Signup extends AppCompatActivity {

    EditText username,vehicletype,pw,cpw;
    Button signup;
    DBHelper MyDB;
    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username= findViewById(R.id.email);
        vehicletype= findViewById(R.id.vehicaltype);
        pw= findViewById(R.id.pass);
        cpw= findViewById(R.id.confirmpass);
        signup= findViewById(R.id.singup);
        MyDB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();
                String vehicle_type = vehicletype.getText().toString();
                String pass = pw.getText().toString();
                String cpass = cpw.getText().toString();
                String f_role = "vowner";

                if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(vehicle_type) || TextUtils.isEmpty(f_role) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(cpass))
                {
                    Toast.makeText(Signup.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(cpass)){
                        Boolean checked_user = MyDB.vownercheckUsername(user_name);
                        if(checked_user == false){
                            Boolean inserted = MyDB.vownerinsertData(user_name,f_role,vehicle_type,pass);
                            if(inserted == true){
                                Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Signup.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Signup.this, "User already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this, "Password are not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}