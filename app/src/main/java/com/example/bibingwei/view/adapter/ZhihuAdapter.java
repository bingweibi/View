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

import java.util.List;

/**
 * @author bibingwei
 */
public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ViewHolder>{

    private List<ZhiHu.StoriesBean> mZhiHuArrayList;
    private Context mContext;

    public interface OnItemClickListener {
        /**
         * 点击item
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView zhihuImage;
        private TextView zhihuText;
        View zhihuItem;

        public ViewHolder(View itemView) {
            super(itemView);
            zhihuItem = itemView;
            zhihuImage = itemView.findViewById(R.id.zhihuListImageItem);
            zhihuText = itemView.findViewById(R.id.zhihuListTextItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
    }

    public ZhihuAdapter(Context context,List<ZhiHu.StoriesBean> zhiHuArrayList) {
        mContext = context;
        mZhiHuArrayList = zhiHuArrayList;
    }

    public ZhihuAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zhihulistitem,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(mContext).load(mZhiHuArrayList.get(position).getImages().get(0)).into(holder.zhihuImage);
        holder.zhihuText.setText(mZhiHuArrayList.get(position).getTitle());
        if (clickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mZhiHuArrayList == null ? 0 : mZhiHuArrayList.size();
    }
}
