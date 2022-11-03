package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import ead.sliit.qfuel.api.JsonPlaceHolderApi;
import ead.sliit.qfuel.model.StationOwner;
import ead.sliit.qfuel.model.VehicleOwner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import retrofit2.Call;

public class Home extends AppCompatActivity {
    private TextView textViewResult,searchresult;
    EditText search;
    String searchval;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewResult = findViewById(R.id.text_view_result);
        search=findViewById(R.id.editTextTextnerest);
        searchresult=findViewById(R.id.text_view_searchres);
        b1=findViewById(R.id.searchbtn);

        Retrofit retrofit = null;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fuelapiapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<VehicleOwner>> call = jsonPlaceHolderApi.getVDetails();
        //Call<StationOwner> call1 = jsonPlaceHolderApi.getDetailsByLocation(searchval);

        call.enqueue(new Callback<List<VehicleOwner>>() {
            @Override
            public void onResponse(Call<List<VehicleOwner>> call, Response<List<VehicleOwner>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<VehicleOwner> vos = response.body();

                for (VehicleOwner vo : vos) {
                    String content = "";
                    content += "Nearest Station: " + vo.getNeareststation() + "\n";
                    content += "Fuel Type: " + vo.getType() + "\n";
                    content += "Arrive Time: " + vo.getArrivaltime() + "\n";
                    content += "Depart Time: " + vo.getDeparttime() + "\n\n";

                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<VehicleOwner>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchval = search.getText().toString();
                if (searchval.isEmpty() ) {
                    Toast.makeText(Home.this, "Please enter All the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    searchlocation(searchval);
//                    Toast.makeText(UserAddFuelDetails.this, "Successfully entered", Toast.LENGTH_SHORT).show();
//                    return;
                }


            }
        });

    }
    private void searchlocation(String searchval){
        Retrofit retrofit = null;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fuelappapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
//        Reader reader = new InputStreamReader(content);
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
//        Gson gson = gsonBuilder.create();
//        List<StationOwner> postsList = Arrays.asList(gson.fromJson(reader,
//                StationOwner[].class));
//        StationOwner stationowner = gson.fromJson(reader, StationOwner.class);
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //Post post = gson.fromJson(reader, Post.class);
//        List<StationOwner> postsList = Arrays.asList(gson.fromJson(reader,
//                Post[].class));
        Call<StationOwner> call1 = jsonPlaceHolderApi.getDetailsByLocation(searchval);
        call1.enqueue(new Callback<StationOwner>() {
            @Override
            public void onResponse(Call<StationOwner> call, Response<StationOwner> response) {
                if (!response.isSuccessful()) {
                    searchresult.setText("Code: " + response.code());
                    return;
                }
                StationOwner vo = response.body();
                String content = "";
                content += "Nearest Station: " + vo.getLocation() + "\n";
                content += "Fuel Type: " + vo.getType() + "\n";
                content += "Arrive Time: " + vo.getStart() + "\n";
                content += "Depart Time: " + vo.getEnd() + "\n\n";

                searchresult.append(content);
                searchresult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), JoinQueue.class);
                        intent.putExtra("location", vo.getLocation());
                        intent.putExtra("Type", vo.getType());
                        intent.putExtra("Start", vo.getStart());
                        intent.putExtra("End", vo.getEnd());
                        startActivity(intent);

                    }
                });



            }

            @Override
            public void onFailure(Call<StationOwner> call, Throwable t) {
                searchresult.setText(t.getMessage());
            }
        });

//        call1.enqueue(new Callback<StationOwner>() {
//            @Override
//            public void onResponse(Call<List<StationOwner>> call, Response<List<StationOwner>> response) {
//
//                if (!response.isSuccessful()) {
//                    searchresult.setText("Code: " + response.code());
//                    return;
//                }
//
//                List<StationOwner> vos = response.body();
//
//                for (StationOwner vo : vos) {
//                    String content = "";
//                    content += "Nearest Station: " + vo.getLocation() + "\n";
//                    content += "Fuel Type: " + vo.getType() + "\n";
//                    content += "Arrive Time: " + vo.getStart() + "\n";
//                    content += "Depart Time: " + vo.getEnd() + "\n\n";
//
//                    searchresult.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<StationOwner>> call, Throwable t) {
//                searchresult.setText(t.getMessage());
//            }
//        });

    }


}