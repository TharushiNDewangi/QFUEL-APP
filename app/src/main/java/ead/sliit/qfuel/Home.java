package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = null;



            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://fuelapiapp.herokuapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
//            return retrofit;
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:5009/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<VehicleOwner>> call = jsonPlaceHolderApi.getVDetails();

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
    }
}