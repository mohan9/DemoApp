package com.mohan.demoapp.dataservice;

import com.mohan.demoapp.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataApi {
    @GET("imran4u/0e79cef20855dac6180cbd050ea0260d/raw/238369bf5e8aba603f4ddbff06ca64350cfa41bf/dizvik-data.json")
    Call<List<Data>> getDataList();
}
