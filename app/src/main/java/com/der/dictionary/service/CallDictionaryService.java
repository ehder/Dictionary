package com.der.dictionary.service;

import com.der.dictionary.model.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallDictionaryService {

    @GET("entries/en/{word}")
    Call<List<APIResponse>> callMeanings(@Path("word") String word);

    //TODO Other CRUD OPERATION

}
