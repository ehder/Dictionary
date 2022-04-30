package com.der.dictionary;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.der.dictionary.model.APIResponse;
import com.der.dictionary.service.CallDictionaryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {

    private Context context;
    private static final String HTTPS_BASE_URL = "https://api.dictionaryapi.dev/api/v2/";
    private Retrofit retrofit;

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word){

        //initialize base url
        retrofit = new Retrofit.Builder().baseUrl(HTTPS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create
        CallDictionaryService callDictionary = retrofit.create(CallDictionaryService.class);

        //call api response
        Call<List<APIResponse>> call = callDictionary.callMeanings(word);


        try {
            call.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                    listener.onError("Request Fail: " + word);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "An Error Occurred.", Toast.LENGTH_SHORT).show();
        }

    }

}
