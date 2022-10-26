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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminHome extends AppCompatActivity {

    TextView Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Result = findViewById(R.id.text_view_adminresult);
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

        Call<List<StationOwner>> call = jsonPlaceHolderApi.getDetails();
        Call<List<StationOwner>> call1 = jsonPlaceHolderApi.postDetails();

        call.enqueue(new Callback<List<StationOwner>>() {
            @Override
            public void onResponse(Call<List<StationOwner>> call, Response<List<StationOwner>> response) {

                if (!response.isSuccessful()) {
                    Result.setText("Code: " + response.code());
                    return;
                }

                List<StationOwner> sts = response.body();

                for (StationOwner st : sts) {
                    String content = "";
                    content += "Nearest Station: " + st.getLocation() + "\n";
                    content += "Fuel Type: " + st.getType() + "\n";
                    content += "Arrive Time: " + st.getStart() + "\n";
                    content += "Depart Time: " + st.getEnd() + "\n\n";

                    Result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<StationOwner>> call, Throwable t) {
                Result.setText(t.getMessage());
            }
        });
    }
}