package com.mohan.demoapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.mohan.demoapp.dataservice.DataApi;
import com.mohan.demoapp.dataservice.RetrofitService;
import com.mohan.demoapp.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepo {

    private static DataRepo dataRepo;

    public static DataRepo getInstance() {
        if (dataRepo == null) {
            dataRepo = new DataRepo();
        }
        return dataRepo;
    }

    private DataApi dataApi;

    public DataRepo() {
        dataApi = RetrofitService.createService(DataApi.class);
    }

    public MutableLiveData<List<Data>> getData() {
        final MutableLiveData<List<Data>> newsData = new MutableLiveData<>();
        dataApi.getDataList().enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call,
                                   Response<List<Data>> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}