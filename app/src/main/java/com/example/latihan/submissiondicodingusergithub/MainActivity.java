package com.example.latihan.submissiondicodingusergithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.latihan.submissiondicodingusergithub.Adapter.UserAdapter;
import com.example.latihan.submissiondicodingusergithub.entity.UserEntity;
import com.example.latihan.submissiondicodingusergithub.entity.UserResponse;
import com.example.latihan.submissiondicodingusergithub.service.ApiConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public List<UserEntity> dataGit = new ArrayList<>();
    public RecyclerView rvUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.search_user));
        }
        progressBar = findViewById(R.id.progressBar);

        rvUser = findViewById(R.id.rv_search_user);

        rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null){
            SearchView searchView = (SearchView) findViewById(R.id.sv_user);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.username));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    showProgress(true);
                    if (query != null){
                        getDataHere(query);
                    }else {
                        Toast.makeText(MainActivity.this, "Tolong masukkan Username!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    return true;
                }
            });

        }
    }

    private void getDataHere(final String username) {
        Call<UserResponse> requestCall = ApiConfig.getApiService().getSearchUser(username);
        requestCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    //mengambil data dari internet masuk ke data github.
                    dataGit = response.body().getItems();
                    //set adapter
                    rvUser.setAdapter(new UserAdapter(MainActivity.this, dataGit));
                    showProgress(false);
                }else {
                    Toast.makeText(MainActivity.this, "Request gagal!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress(boolean progres) {
        if (progres){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}