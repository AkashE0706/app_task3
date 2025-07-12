package com.example.myapp.network;

import com.example.myapp.models.DataModel;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("data")  // Replace "data" with your API endpoint path
    Call<List<DataModel>> getDataList();
}
