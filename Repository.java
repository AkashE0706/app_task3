package com.example.myapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapp.models.DataModel;
import com.example.myapp.network.ApiClient;
import com.example.myapp.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiService apiService;

    public Repository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void fetchData(MutableLiveData<List<DataModel>> liveData, MutableLiveData<String> errorLiveData) {
        apiService.getDataList().enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue(response.body());
                } else {
                    errorLiveData.postValue("Response Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                errorLiveData.postValue("Network Failure: " + t.getMessage());
                Log.e("Repository", "API call failed", t);
            }
        });
    }
}
