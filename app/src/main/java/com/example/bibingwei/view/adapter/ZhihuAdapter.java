package com.example.bibingwei.view.adapter;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bibingwei
 */
public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ViewHolder>{

    private List<ZhiHu.StoriesBean> mZhihuStories;

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.zhihuListImageItem) ImageView zhihuImage;
        @BindView(R.id.zhihuListTextItem) TextView zhihuText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public ZhihuAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zhihulistitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(mZhihuStories.get(position).getImages().get(0)).into(holder.zhihuImage);
        holder.zhihuText.setText(mZhihuStories.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return mZhihuStories == null ? 0 : mZhihuStories.size();
    }

    public void setData(List<ZhiHu.StoriesBean> storiesBeanList) {
        this.mZhihuStories = storiesBeanList;
        notifyDataSetChanged();
    }
}
