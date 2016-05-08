package com.cwenhui.mark.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommondRecyclerViewHolder> {
    private Context context;
    private int layoutId;
    protected List<T> mDatas;
    private LayoutInflater mInflater;
    private onItemClickListener clickListener;

    public CommonRecyclerViewAdapter(Context context, int layoutId, List<T> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.layoutId = layoutId;
        this.mDatas = datas;
    }

    @Override
    public CommondRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(layoutId, parent, false);
        return new CommondRecyclerViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(CommondRecyclerViewHolder holder, int position) {
        T t = mDatas.get(position);
        convert(holder, t);
    }

    /**
     * 对item的各个控件进行操作（包括监听setUpItemEvent）
     *
     * @param holder
     * @param t
     */
    public abstract void convert(CommondRecyclerViewHolder holder, T t);

    public interface onItemClickListener {
        void onItemClick(View view, int pos);

        void onItemLongClick(View view, int pos);
    }

    public void setClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    protected void setUpItemEvent(final CommondRecyclerViewHolder holder) {
        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    clickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    clickListener.onItemLongClick(holder.itemView, layoutPosition);
                    return false;
                }
            });
        }
    }
}
