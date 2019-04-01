package com.example.bew.hardtask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategInterface {

    @GET("GetCategories?categoryId=0&countryId=1")
    Call<List<CategResponse>> getCategeries() ;

    @GET("GetCategories")
    Call<List<CategResponse>> getsubCategries(@Query("categoryId") String Cat_id , @Query("countryId") String Country_id  );


}
