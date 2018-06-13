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
import com.example.bibingwei.view.bean.Music;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bibingwei
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private MusicListAdapter.OnItemClickListener mOnItemClickListener;
    private List<Music.SongListBean> mSongListBeans;

    public void setClickListener(OnItemClickListener clickListener){
        this.mOnItemClickListener = clickListener;
    }

    public interface OnItemClickListener {
        /**
         *
         * 点击播放歌曲
         * @param view
         * @param position
         */
        void onClick(View view,int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.musicListImageItem)ImageView musicListImageItem;
        @BindView(R.id.musicListTitle)TextView musicListTitle;
        @BindView(R.id.musicListAuthor)TextView musicListAuthor;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.musiclistitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext()).load(mSongListBeans.get(position).getPic_small()).into(holder.musicListImageItem);
        holder.musicListTitle.setText(mSongListBeans.get(position).getTitle());
        holder.musicListAuthor.setText(mSongListBeans.get(position).getArtist_name());
    }

    @Override
    public int getItemCount() {
        return mSongListBeans == null ? 0 : mSongListBeans.size();
    }

    public void setData(List<Music.SongListBean> songListBeanList) {
        this.mSongListBeans = songListBeanList;
        notifyDataSetChanged();
    }
}
