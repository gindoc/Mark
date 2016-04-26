package com.cwenhui.mark.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.mark.utils.CommondRecyclerViewHolder;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommondRecyclerViewHolder> {
    private Context context;
    private int layoutId;
    private List<T> mDatas;
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
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(CommondRecyclerViewHolder holder, int position) {
        T t = mDatas.get(position);
        convert(holder, t);
    }

    /**
     * 对item的各个控件进行操作（包括监听setUpItemEvent）
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

//class CommondRecyclerViewHolder extends RecyclerView.ViewHolder {
//    private View mConvertView;
//    private SparseArray<View> mViews;
//
//    public CommondRecyclerViewHolder(View viewGroup) {
//        super(viewGroup);
//        mConvertView = viewGroup;
//    }
//
//    public CommondRecyclerViewHolder setText(int viewId, String text) {
//        TextView tv = getView(viewId);
//        tv.setText(text);
//        return this;
//    }
//
//    private <T extends View> T getView(int viewId) {
//        View view = mViews.get(viewId);
//        if (view == null) {
//            view = mConvertView.findViewById(viewId);
//            mViews.put(viewId, view);
//        }
//        return (T) view;
//
//    }
//}