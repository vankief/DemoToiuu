package com.example.hashmap_stringinteger.retrofit;

import android.util.Log;

import com.example.hashmap_stringinteger.model.DataRequest;
import com.example.hashmap_stringinteger.model.DataResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class APIHelper {
    private APIService api;
    private final String BASE_URL = "http://192.168.102.100:3000/map/";

    public APIHelper() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(APIService.class);
        }
    }

    public void postData(DataRequest request, StringCallBack res) {
        api.postData(request).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        res.execute(String.valueOf(response.body()));
                    } else {
                        res.execute("");
                    }
                } else {
                    res.execute("");
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e("APIHelper", "API call failed: " + t.getMessage());
                res.execute("");
            }
        });
    }

    public interface APIService {
        @Headers( "x-api-key: asdasdas" )
        @POST("insert-data")
        Call<DataResponse> postData(@Body DataRequest request);
    }
}
