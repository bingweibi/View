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
import com.example.bibingwei.bean.ZhiHu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bibingwei
 */
public class ZhiHuAdapter extends RecyclerView.Adapter<ZhiHuAdapter.ViewHolder>{

    private List<ZhiHu.StoriesBean> mZhiHuStories;
    private OnItemClickListener mOnItemClickListener;

    public void setClickListener(OnItemClickListener clickListener){
        this.mOnItemClickListener = clickListener;
    }

    public interface OnItemClickListener{
        /**
         * 点击接口
         * @param view
         * @param position
         */
        void onClick(View view,int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.readingListImageItem) ImageView zhihuImage;
        @BindView(R.id.readingListTextItem) TextView zhihuText;

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

    public ZhiHuAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.readinglistitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(mZhiHuStories.get(position).getImages().get(0)).into(holder.zhihuImage);
        holder.zhihuText.setText(mZhiHuStories.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return mZhiHuStories == null ? 0 : mZhiHuStories.size();
    }

    public void setData(List<ZhiHu.StoriesBean> storiesBeanList) {
        this.mZhiHuStories = storiesBeanList;
        notifyDataSetChanged();
    }
}
