package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ead.sliit.qfuel.api.JsonPlaceHolderApi;
import ead.sliit.qfuel.model.StationOwner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminUpdateFuel extends AppCompatActivity {

    EditText location, type, start, end;
    String txtIlocation, txtItype, txtIstart, txtIend;
    Button submit;
    StationOwner stationOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_fuel);

        location = findViewById(R.id.updatelocation);
        type = findViewById(R.id.updatetype);
        start = findViewById(R.id.updatestart);
        end = findViewById(R.id.updateend);
        submit = findViewById(R.id.updatesubmit);

        Intent intent = getIntent();
        String txtlocation = intent.getStringExtra("location");
        String txtType = intent.getStringExtra("Type");
        String txtStart = intent.getStringExtra("Start");
        String txtEnd = intent.getStringExtra("End");


        location.setText(txtlocation);
        type.setText(txtType);
        start.setText(txtStart);
        end.setText(txtEnd);


        //updateDetails

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtIlocation = location.getText().toString();
                txtItype = type.getText().toString();
                txtIstart = start.getText().toString();
                txtIend = end.getText().toString();
                if (txtIlocation.isEmpty() && txtItype.isEmpty() && txtIstart.isEmpty() && txtIend.isEmpty()) {
                    Toast.makeText(AdminUpdateFuel.this, "Please enter All the values", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    postStationOwnerDetails(txtIlocation, txtItype,txtIstart,txtIend);
                    Toast.makeText(AdminUpdateFuel.this, "Successfully entered", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });


    }

    private void postStationOwnerDetails(String location, String type, String start, String end) {

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
        StationOwner stationowner = new StationOwner(location, type, start, end);
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        String ID= stationowner.getId();

        Call<StationOwner> call = jsonPlaceHolderApi.updateDetails(ID,stationowner);
        call.enqueue(new Callback<StationOwner>() {
            @Override
            public void onResponse(Call<StationOwner> call, Response<StationOwner> response) {
                Toast.makeText(AdminUpdateFuel.this, "Successfully entered to API", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<StationOwner> call, Throwable t) {
                Toast.makeText(AdminUpdateFuel.this, "Updated!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                startActivity(intent);
            }
        });

    }

}
