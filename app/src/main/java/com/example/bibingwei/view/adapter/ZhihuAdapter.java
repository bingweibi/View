package com.example.bibingwei.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.bean.ZhiHu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bibingwei
 */
public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ViewHolder> {

    private List<ZhiHu.StoriesBean> mZhiHuArrayList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView zhihuImage;
        private TextView zhihuText;

        ViewHolder(View itemView) {
            super(itemView);
            zhihuImage = itemView.findViewById(R.id.zhihuListImageItem);
            zhihuText = itemView.findViewById(R.id.zhihuListTextItem);
        }
    }

    public ZhihuAdapter(List<ZhiHu.StoriesBean> zhiHuArrayList) {
        mZhiHuArrayList = zhiHuArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zhihulistitem,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mZhiHuArrayList.get(position).getImages().get(0)).into(holder.zhihuImage);
        holder.zhihuText.setText(mZhiHuArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mZhiHuArrayList.size();
    }
}
