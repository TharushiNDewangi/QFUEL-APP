package ead.sliit.qfuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class AdminHome extends AppCompatActivity implements AdminHomeAdaptor.ClickedItem{

    TextView Result;
    Toolbar toolbar;
    RecyclerView recyclerView;

    AdminHomeAdaptor usersAdapter;
    ItemClickListener itemClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        //Result = findViewById(R.id.text_view_adminresult);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
// Initialize listener
        itemClickListener=new ItemClickListener() {
            @Override
            public void onClick(int position, String value) {
                // Display toast
                Toast.makeText(getApplicationContext(),"Position : "
                        +position +" || Value : "+value,Toast.LENGTH_SHORT).show();
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        usersAdapter = new AdminHomeAdaptor(this::ClickedUser);

        getAllUsers();

    }
    public void getAllUsers(){
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

        Call<List<StationOwner>> call = jsonPlaceHolderApi.getDetails();


        call.enqueue(new Callback<List<StationOwner>>() {
            @Override
            public void onResponse(Call<List<StationOwner>> call, Response<List<StationOwner>> response) {

                if(response.isSuccessful()){
                    List<StationOwner> userResponses = response.body();
                    usersAdapter.setData(userResponses);
                    recyclerView.setAdapter(usersAdapter);
//                    recyclerView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(getApplicationContext(), Login.class);
//                            startActivity(intent);
//
//                        }
//                    });

                }

            }

            @Override
            public void onFailure(Call<List<StationOwner>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }


    public void ClickedUser(StationOwner vo) {
//        String txtlocation = stationOwner.getLocation();
//        String txttype = stationOwner.getType();
//        String txttxtstart = stationOwner.getStart();
//        String txtend = stationOwner.getEnd();

       //startActivity(new Intent(this,AdminUpdateFuel.class);
        Intent intent = new Intent(getApplicationContext(), AdminUpdateFuel.class);
        intent.putExtra("location", vo.getLocation());
        intent.putExtra("Type", vo.getType());
        intent.putExtra("Start", vo.getStart());
        intent.putExtra("End", vo.getEnd());
        startActivity(intent);


    }
}

