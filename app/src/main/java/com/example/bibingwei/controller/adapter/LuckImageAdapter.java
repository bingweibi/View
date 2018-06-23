package com.example.bibingwei.controller.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bibingwei.controller.R;
import com.example.bibingwei.bean.LuckImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bibingwei
 */
public class LuckImageAdapter extends RecyclerView.Adapter<LuckImageAdapter.ViewHolder>{

    private List<LuckImage.ResultsBean> mResultsBeans;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setLongClickListener(OnItemLongClickListener longClickListener){
        this.mOnItemLongClickListener = longClickListener;
    }

    public interface OnItemLongClickListener{
        /**
         * 点击接口
         * @param view
         * @param position
         */
        void onLongClick(View view,int position);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        @BindView(R.id.luck_image_item) ImageView luckImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public boolean onLongClick(View v) {

            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener.onLongClick(itemView,getAdapterPosition());
            }
            return false;
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
