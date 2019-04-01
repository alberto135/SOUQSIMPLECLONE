package com.example.bew.hardtask;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivit";
    RecyclerView recyclerView ;
    MyRecycularViewAdapter myRecycularViewAdapter ;
    RecyclerView.LayoutManager layoutManager ;

    ArrayList<CategResponse> categResponseArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView= findViewById(R.id.mainrecview);
        Log.e(TAG ,"fetching");
        FetchJason();







    }
    private void writerecycluarview (List<CategResponse> jasonresponse , int Counter )
    {


        myRecycularViewAdapter = new MyRecycularViewAdapter(jasonresponse, Counter ) ;
        layoutManager = new GridLayoutManager(MainActivity.this ,2 );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myRecycularViewAdapter);




    }





    private void FetchJason(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://souq.hardtask.co/app/app.asmx/").addConverterFactory(GsonConverterFactory.create()).build();
        CategInterface categInterface = retrofit.create(CategInterface.class);

        Call<List<CategResponse>> call = categInterface.getCategeries() ;
        call.enqueue(new Callback<List<CategResponse>>() {
            @Override
            public void onResponse(Call<List<CategResponse>> call, Response<List<CategResponse>> response) {

                if (response.isSuccessful()) {
                    List<CategResponse> Categries = response.body();
                    int z = Categries.size() ;
                    int Counter = 0;

                    for (int i = 0; i <z ; i++) {
                        String title = Categries.get(i).getTitleEN();
                        String pohoto = Categries.get(i).getPhoto();
                        String productcount = Categries.get(i).getProductCount();
                        int id = Categries.get(i).getId() ;
                        Categries.add(new CategResponse(title, pohoto, productcount,id));
                        Counter ++ ;
                        }
                        Log.e(TAG , "good"+"counter is "+Counter);
                        writerecycluarview(Categries , Counter);

                }else
                    {


                    }

            }

            @Override
            public void onFailure(Call<List<CategResponse>> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
    private void FetchsubCat (String id )
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://souq.hardtask.co/app/app.asmx/").addConverterFactory(GsonConverterFactory.create()).build();
        CategInterface categInterface2 = retrofit.create(CategInterface.class);

        Call<List<CategResponse>> call2 = categInterface2.getCategeries();
        call2.enqueue(new Callback<List<CategResponse>>() {
            @Override
            public void onResponse(Call<List<CategResponse>> call, Response<List<CategResponse>> response) {
                if (response.isSuccessful()) {
                    List<CategResponse> Categries = response.body();
                    int z = Categries.size();
                    int Counter = 0;

                    for (int i = 0; i < z; i++) {
                        String title = Categries.get(i).getTitleEN();
                        String pohoto = Categries.get(i).getPhoto();
                        String productcount = Categries.get(i).getProductCount();
                        int id = Categries.get(i).getId() ;
                        Categries.add(new CategResponse(title, pohoto, productcount ,id ));
                        Counter++;
                    }
                    Log.e(TAG, "good" + "counter is " + Counter);
                    writerecycluarview(Categries, Counter);

                }
            }

            @Override
            public void onFailure(Call<List<CategResponse>> call, Throwable t) {

            }
        });




    }



}
