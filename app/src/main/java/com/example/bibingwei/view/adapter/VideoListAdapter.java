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
import com.example.bibingwei.bean.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bibingwei
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private VideoListAdapter.OnItemClickListener mOnItemClickListener;
    private List<Video.ItemListBeanX> mVideoListBeans;
    private Context mContext;

    public void setClickListener(VideoListAdapter.OnItemClickListener clickListener){
        this.mOnItemClickListener = clickListener;
    }

    public interface OnItemClickListener {
        /**
         *
         * 点击播放视频
         * @param view
         * @param position
         */
        void onClick(View view,int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.videoImage)ImageView videoImage;
        @BindView(R.id.videoTitle)TextView videoTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(itemView,getAdapterPosition());
            }
        }
    }

    @NonNull
    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videolistitem,parent,false);
        return new VideoListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(mVideoListBeans.get(position).getData().getCover().getFeed()).into(holder.videoImage);
        holder.videoTitle.setText(mVideoListBeans.get(position).getData().getTitle());
    }

    @Override
    public int getItemCount() {
        return mVideoListBeans == null ? 0 : mVideoListBeans.size();
    }

    public void setData(List<Video.ItemListBeanX> videoListBeanList, Context mContext) {
        this.mContext = mContext;
        this.mVideoListBeans = videoListBeanList;
        notifyDataSetChanged();
    }
}
