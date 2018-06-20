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
import com.example.bibingwei.bean.OtherReading;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bibingwei
 */
public class ReadingOtherAdapter extends RecyclerView.Adapter<ReadingOtherAdapter.ViewHolder> {

    private List<OtherReading.ResultBean.DataBean> mOtherNews;
    private ReadingOtherAdapter.OnItemClickListener mOnItemClickListener;

    public void setClickListener(ReadingOtherAdapter.OnItemClickListener clickListener){
        this.mOnItemClickListener = clickListener;
    }

    public interface OnItemClickListener{
        /**
         * 点击接口
         * @param view
         * @param position
         */
        void onClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.readingListImageItem)
        ImageView newsImage;
        @BindView(R.id.readingListTextItem)
        TextView newsText;

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

    public ReadingOtherAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.readinglistitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(mOtherNews.get(position).getThumbnail_pic_s()).into(holder.newsImage);
        holder.newsText.setText(mOtherNews.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return mOtherNews == null ? 0 : mOtherNews.size();
    }

    public void setData(List<OtherReading.ResultBean.DataBean> storiesBeanList) {
        this.mOtherNews = storiesBeanList;
        notifyDataSetChanged();
    }
}
