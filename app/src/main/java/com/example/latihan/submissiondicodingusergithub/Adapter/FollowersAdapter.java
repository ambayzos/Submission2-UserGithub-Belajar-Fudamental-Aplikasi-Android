package com.example.latihan.submissiondicodingusergithub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.latihan.submissiondicodingusergithub.R;
import com.example.latihan.submissiondicodingusergithub.entity.FollowersEntity;

import java.util.ArrayList;


public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder> {

    private Context context;
    private ArrayList<FollowersEntity> dataListFollowers = new ArrayList<>();

    public FollowersAdapter(Context context, ArrayList<FollowersEntity> dataListFollowers) {
        this.context = context;
        this.dataListFollowers = dataListFollowers;
    }

    @NonNull
    @Override
    public FollowersViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_followers, parent, false);
        return new FollowersViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FollowersViewHolder holder, int position) {
        holder.tvUsernameFollowers.setText(dataListFollowers.get(position).getLogin());
        Glide.with(context)
                .load(dataListFollowers.get(position).getAvatarUrl())
                .into(holder.ivAvatarFollowers);
    }

    @Override
    public int getItemCount() {
        return dataListFollowers.size();
    }

    public class FollowersViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsernameFollowers;
        ImageView ivAvatarFollowers;
        public FollowersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsernameFollowers = itemView.findViewById(R.id.tv_username_follower);
            ivAvatarFollowers = itemView.findViewById(R.id.iv_avatar_follower);
        }
    }
}
