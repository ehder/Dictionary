package com.der.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.der.dictionary.adapter.MeaningsAdapter;
import com.der.dictionary.adapter.PhoneticsAdapter;
import com.der.dictionary.model.APIResponse;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView tvWord;
    RecyclerView recyclerPhonetics;
    RecyclerView recyclerMeanings;

    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningsAdapter meaningsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        progressDialog.setTitle("Loading...");
        progressDialog.show();
        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.getWordMeaning(listener, "hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {

        @Override
        public void onFetchData(APIResponse apiResponse, String message) {

            progressDialog.dismiss();
            if (apiResponse == null){
                Toast.makeText(MainActivity.this, "No data found.", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message , Toast.LENGTH_LONG).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        tvWord.setText("Word: " + apiResponse.getWord());

        phoneticsAdapter = new PhoneticsAdapter( apiResponse.getPhonetics(), this);
        recyclerPhonetics.setHasFixedSize(true);
        recyclerPhonetics.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerPhonetics.setAdapter(phoneticsAdapter);

        meaningsAdapter = new MeaningsAdapter(apiResponse.getMeanings(), this);
        recyclerMeanings.setHasFixedSize(true);
        recyclerMeanings.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerMeanings.setAdapter(meaningsAdapter);

    }

    private void init(){
        searchView = findViewById(R.id.search_view);
        tvWord = findViewById(R.id.tv_word);
        recyclerPhonetics = findViewById(R.id.recycler_phonetics);
        recyclerMeanings = findViewById(R.id.recycler_meanings);
        progressDialog = new ProgressDialog(this);
    }



}