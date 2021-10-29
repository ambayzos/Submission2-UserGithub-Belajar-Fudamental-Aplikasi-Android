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

import com.example.latihan.submissiondicodingusergithub.Adapter.FollowingAdapter;
import com.example.latihan.submissiondicodingusergithub.Adapter.UserAdapter;
import com.example.latihan.submissiondicodingusergithub.DetailUser;
import com.example.latihan.submissiondicodingusergithub.R;
import com.example.latihan.submissiondicodingusergithub.entity.FollowingEntity;
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
public class FollowingFragment extends Fragment {

    RecyclerView rvFollowing;
    UserEntity userEntity;

    public FollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Mengambil data dari search User;
        DetailUser detailUser = (DetailUser) getActivity();
        Bundle bundle = detailUser.getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
        userEntity = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

        rvFollowing = view.findViewById(R.id.rv_following);
        rvFollowing.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowingEntity>> requestFollowing = ApiConfig.getApiService().getFollowing(userEntity.getLogin());
        requestFollowing.enqueue(new Callback<List<FollowingEntity>>() {
            @Override
            public void onResponse(Call<List<FollowingEntity>> call, Response<List<FollowingEntity>> response) {
                ArrayList<FollowingEntity> listFollowingEntity = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listFollowingEntity.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse"+listFollowingEntity.size());
                        rvFollowing.setAdapter(new FollowingAdapter(getContext(), listFollowingEntity));
                    }
                }else {
                    Toast.makeText(getContext(), "Request gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowingEntity>> call, Throwable t) {
                Toast.makeText(getContext(), "Request Filure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}