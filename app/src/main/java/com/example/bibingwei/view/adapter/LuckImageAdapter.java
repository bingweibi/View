package com.example.bibingwei.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.bean.LuckImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bibingwei
 */
public class LuckImageAdapter extends RecyclerView.Adapter<LuckImageAdapter.ViewHolder>{

    private List<LuckImage.ResultsBean> mResultsBeans;
    private int imageWidth;


    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.luck_image_item) ImageView luckImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public LuckImageAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lucklistitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(mResultsBeans.get(position).getUrl()).into(holder.luckImage);
    }


    @Override
    public int getItemCount() {
        return mResultsBeans == null ? 0 : mResultsBeans.size();
    }

    public void setData(List<LuckImage.ResultsBean> resultsBeanList) {
        this.mResultsBeans = resultsBeanList;
        notifyDataSetChanged();
    }
}
