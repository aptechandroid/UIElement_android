package com.uits.register.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.uits.register.R;
import com.uits.register.model.User;

import java.util.List;

/**
 * DashBoardAdapter
 * <p>
 * Copyright © 2019 UITS CO.,LTD
 * Created PHUQUY on 2019-12-24.
 **/
public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.ItemViewHolder> {

    private List<User> mList;

    public DashBoardAdapter(Context context, List<User> users) {
        mList = users;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item_user, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        User user = mList.get(position);
        holder.mTxtUserName.setText(user.getUserName());
        holder.mTxtFullName.setText(user.getFirstName() + user.getLastName());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView mTxtUserName;
        private AppCompatTextView mTxtFullName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtUserName = itemView.findViewById(R.id.mTxtUserName);
            mTxtFullName = itemView.findViewById(R.id.mTxtFullName);
        }
    }
}
