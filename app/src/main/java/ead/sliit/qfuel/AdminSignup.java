package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;

import ead.sliit.qfuel.helper.DBHelper;

public class AdminSignup extends AppCompatActivity {

    EditText username,vehicletype,pw,cpw;
    Button adminsignup;
    DBHelper MyDB;
    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        username= findViewById(R.id.adminemail);
        vehicletype= findViewById(R.id.location);
        pw= findViewById(R.id.adminpass);
        cpw= findViewById(R.id.adminconfirmpass);
        adminsignup= (Button)findViewById(R.id.adminssingup);
        MyDB = new DBHelper(this);

        adminsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();
                String location = vehicletype.getText().toString();
                String pass = pw.getText().toString();
                String cpass = cpw.getText().toString();
                String f_role = "sowner";

                if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(location) || TextUtils.isEmpty(f_role) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(cpass))
                {
                    Toast.makeText(AdminSignup.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(cpass)){
                        Boolean checked_user = MyDB.sownercheckUsername(user_name);
                        if(checked_user == false){
                            Boolean inserted = MyDB.sownerinsertData(user_name,f_role,location,pass);
                            if(inserted == true){
                                Toast.makeText(AdminSignup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AdminSignup.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AdminSignup.this, "User already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdminSignup.this, "Password are not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}