package com.cwenhui.mark.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.mark.R;
import com.cwenhui.mark.utils.CommondRecyclerViewHolder;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public abstract class CommonRefreshRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;
    private int layoutId;
    protected List<T> mDatas;
    private LayoutInflater mInflater;
    private onItemClickListener clickListener;

    public CommonRefreshRecyclerViewAdapter(Context context, int layoutId, List<T> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.layoutId = layoutId;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = mInflater.inflate(layoutId, parent, false);
            return new CommondRecyclerViewHolder(view);
        }
        // type == TYPE_FOOTER 返回footerView
        else if (viewType == TYPE_FOOTER) {
            View view = mInflater.inflate(R.layout.layout_footer_view, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommondRecyclerViewHolder) {
            T t = mDatas.get(position);
            convert(holder, t);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 对item的各个控件进行操作（包括监听setUpItemEvent）
     * @param holder
     * @param t
     */
    protected abstract void convert(RecyclerView.ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int pos);

        void onItemLongClick(View view, int pos);
    }

    public void setClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    protected void setUpItemEvent(final MyViewHolder holder) {
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

class FooterViewHolder extends RecyclerView.ViewHolder{

    public FooterViewHolder(View itemView) {
        super(itemView);
    }
}