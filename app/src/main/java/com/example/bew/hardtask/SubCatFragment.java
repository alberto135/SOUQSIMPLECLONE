package com.example.bew.hardtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubCatFragment extends Fragment {

    private static final String TAG ="subcatactivity" ;
    RecyclerView recyclerView2 ;
    SubCatRecycularViewAdapter SubCaTrecCycularViewAdapter ;
    RecyclerView.LayoutManager layoutManager ;
    TextView bar ;
    ImageView back ;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.subcatageryfragment,container ,false) ;
        recyclerView2 =view.findViewById(R.id.subcatrecview);
        int id = getArguments().getInt("ID");
        bar=view.findViewById(R.id.title_textview);
        bar.setText(getArguments().getString("cat_name"));
        FetchJason(id);

        back = view.findViewById(R.id.imageview) ;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() ,MainActivity.class) ;
                startActivity(intent);



            }
        });










        return view ;
    }
    private void writerecycluarview (List<CategResponse> jasonresponse , int Counter)
    {



        SubCaTrecCycularViewAdapter = new SubCatRecycularViewAdapter(jasonresponse, Counter) ;
        layoutManager = new GridLayoutManager(getActivity() ,2 );
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(SubCaTrecCycularViewAdapter);




    }
    private void FetchJason(int id ){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://souq.hardtask.co/app/app.asmx/").addConverterFactory(GsonConverterFactory.create()).build();
        CategInterface categInterface = retrofit.create(CategInterface.class);
        final String ID =String.valueOf(id) ;
        String Q = "1";


        Call<List<CategResponse>> call = categInterface.getsubCategries(ID ,Q) ;
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
                    response.message() ;
                    Log.e(TAG , "not good "+ response.message());
                    Log.e(TAG , "good"+"counter is "+ID);

                }

            }

            @Override
            public void onFailure(Call<List<CategResponse>> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

}
