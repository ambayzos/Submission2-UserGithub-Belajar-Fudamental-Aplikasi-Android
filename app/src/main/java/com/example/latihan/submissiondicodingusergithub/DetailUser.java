package com.example.latihan.submissiondicodingusergithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.latihan.submissiondicodingusergithub.Adapter.PageAdapter;
import com.example.latihan.submissiondicodingusergithub.Adapter.UserAdapter;
import com.example.latihan.submissiondicodingusergithub.entity.DetailUserEntity;
import com.example.latihan.submissiondicodingusergithub.entity.UserEntity;
import com.example.latihan.submissiondicodingusergithub.service.ApiConfig;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUser extends AppCompatActivity {

    public DetailUserEntity detailUserEntity;
    public UserEntity dataUserEntity;
    TextView tvName, tvUsername, tvLocation;
    ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        getSupportActionBar().setTitle(getString(R.string.detail_user));

        Bundle bundle = getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        dataUserEntity = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

        ivAvatar = findViewById(R.id.iv_avatar_detail);
        tvUsername = findViewById(R.id.tv_username_detail);
        tvName = findViewById(R.id.tv_name_detail);
        tvLocation = findViewById(R.id.tv_location_detail);

        //tampil progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(DetailUser.this);
        progressDialog.setMessage(getString(R.string.progress));
        progressDialog.show();

        //menampilkan Data Dari parcelable
        Glide.with(DetailUser.this)
                .load(dataUserEntity.getAvatarUrl())
                .into(ivAvatar);
        tvUsername.setText(dataUserEntity.getLogin());

        //mengambil API
        Call<DetailUserEntity> requestCallDetail = ApiConfig.getApiService().getDetailUser(dataUserEntity.getLogin());
        requestCallDetail.enqueue(new Callback<DetailUserEntity>() {
            @Override
            public void onResponse(Call<DetailUserEntity> call, Response<DetailUserEntity> response) {
                detailUserEntity = response.body();

                tvName.setText(detailUserEntity.getName());
                tvLocation.setText(detailUserEntity.getLocation());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUserEntity> call, Throwable t) {

            }
        });

        //Tab Layout dan ViewPager;
        PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);
    }
}