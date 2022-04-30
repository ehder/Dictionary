package com.der.dictionary.service;

import com.der.dictionary.model.APIResponse;

import java.util.List;

import retrofit2.Call;

public class CallDictionaryServiceImpl implements CallDictionaryService{
    @Override
    public Call<List<APIResponse>> callMeanings(String word) {
        return null;
    }
}
