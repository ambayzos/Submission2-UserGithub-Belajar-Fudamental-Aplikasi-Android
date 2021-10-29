package com.example.latihan.submissiondicodingusergithub.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.latihan.submissiondicodingusergithub.Adapter.FollowersAdapter;
import com.example.latihan.submissiondicodingusergithub.Adapter.UserAdapter;
import com.example.latihan.submissiondicodingusergithub.DetailUser;
import com.example.latihan.submissiondicodingusergithub.R;
import com.example.latihan.submissiondicodingusergithub.entity.FollowersEntity;
import com.example.latihan.submissiondicodingusergithub.entity.UserEntity;
import com.example.latihan.submissiondicodingusergithub.service.ApiConfig;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowersFragment extends Fragment {
    RecyclerView rvFollowers;
    UserEntity userEntity;
    public FollowersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mengambil data dari search user;
        DetailUser detailUser = (DetailUser) getActivity();
        Bundle bundle = detailUser.getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        userEntity = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

        rvFollowers = view.findViewById(R.id.rv_follower);
        rvFollowers.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowersEntity>> requestFollowers = ApiConfig.getApiService().getFollowers(userEntity.getLogin());
        requestFollowers.enqueue(new Callback<List<FollowersEntity>>() {
            @Override
            public void onResponse(Call<List<FollowersEntity>> call, Response<List<FollowersEntity>> response) {
                ArrayList<FollowersEntity> listFollowersEntity = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listFollowersEntity.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse"+listFollowersEntity.size());
                        rvFollowers.setAdapter(new FollowersAdapter(getContext(), listFollowersEntity));
                    }
                }else {
                    Toast.makeText(getContext(), "Request Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowersEntity>> call, Throwable t) {
                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}