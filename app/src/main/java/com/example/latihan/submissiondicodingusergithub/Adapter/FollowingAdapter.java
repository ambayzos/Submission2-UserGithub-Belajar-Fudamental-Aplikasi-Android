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
import com.example.latihan.submissiondicodingusergithub.entity.FollowingEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewFollowingHolder> {

    private Context context;
    private ArrayList<FollowingEntity> dataListFollowing = new ArrayList<>();

    public FollowingAdapter(Context context, ArrayList<FollowingEntity> dataListFollowing) {
        this.context = context;
        this.dataListFollowing = dataListFollowing;
    }

    @NonNull
    @Override
    public ViewFollowingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_following, parent, false);
        return new ViewFollowingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewFollowingHolder holder, int position) {
        holder.tvUsernameFollowing.setText(dataListFollowing.get(position).getLogin());
        Glide.with(context)
                .load(dataListFollowing.get(position).getAvatarUrl())
                .into(holder.ivAvatarFollowing);
    }

    @Override
    public int getItemCount() {
        return dataListFollowing.size();
    }

    public class ViewFollowingHolder extends RecyclerView.ViewHolder {
        TextView tvUsernameFollowing;
        ImageView ivAvatarFollowing;
        public ViewFollowingHolder(@NonNull  View itemView) {
            super(itemView);
            tvUsernameFollowing = itemView.findViewById(R.id.tv_username_following);
            ivAvatarFollowing = itemView.findViewById(R.id.iv_avatar_following);
        }
    }
}
