package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import javax.xml.transform.Result;

import ead.sliit.qfuel.api.JsonPlaceHolderApi;
import ead.sliit.qfuel.helper.DBHelper;
import ead.sliit.qfuel.model.StationOwner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminFuelDetails extends AppCompatActivity {

    EditText location,type,start,end;
    String txtlocation,txttype,txtstart,txtend;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_fuel_details);

        location= findViewById(R.id.adminlocation);
        type= findViewById(R.id.admintype);
        start= findViewById(R.id.adminstart);
        end= findViewById(R.id.adminend);
        submit= findViewById(R.id.adminfuelsubmit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtlocation = location.getText().toString();
                txttype = type.getText().toString();
                txtstart = start.getText().toString();
                txtend = end.getText().toString();
                if (txtlocation.isEmpty() && txttype.isEmpty() && txtstart.isEmpty() && txtend.isEmpty()) {
                    Toast.makeText(AdminFuelDetails.this, "Please enter All the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    postStationOwnerDetails(txtlocation, txttype,txtstart,txtend);
                    Toast.makeText(AdminFuelDetails.this, "Successfully entered", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });


    }
    private void postStationOwnerDetails(String location, String type,String start,String end) {

        Retrofit retrofit = null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fuelappapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // passing data from our text fields to our modal class.
        StationOwner stationowner = new StationOwner(txtlocation, txttype,txtstart,txtend);
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<StationOwner> call = jsonPlaceHolderApi.postDetails(stationowner);
        call.enqueue(new Callback<StationOwner>() {
            @Override
            public void onResponse(Call<StationOwner> call, Response<StationOwner> response) {
                Toast.makeText(AdminFuelDetails.this, "Successfully entered to API", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<StationOwner> call, Throwable t) {
                Toast.makeText(AdminFuelDetails.this, "ERROR!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                startActivity(intent);
            }
        });

    }

}