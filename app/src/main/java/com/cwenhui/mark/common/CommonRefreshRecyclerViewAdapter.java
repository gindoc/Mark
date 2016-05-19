package com.cwenhui.mark.common;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public abstract class CommonRefreshRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NOT_FULL = -1;
    protected Context context;
    protected int itemLayout;
    private int footerLayout;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private onItemClickListener clickListener;

    public CommonRefreshRecyclerViewAdapter(Context context, int itemLayout, int footerLayout, List<T> datas) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.footerLayout = footerLayout;
        this.mDatas = datas;
        if (context != null) {
            this.mInflater = LayoutInflater.from(this.context);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = mInflater.inflate(itemLayout, parent, false);
            return new CommondRecyclerViewHolder(view);
        }
        // type == TYPE_FOOTER 返回footerView
        else if (viewType == TYPE_FOOTER) {
            View view = mInflater.inflate(footerLayout, parent, false);
            return new FooterViewHolder(view);
        }
        else if (viewType == TYPE_NOT_FULL) {
            return new FooterViewHolder(new TextView(parent.getContext()));     //借用FooterViewHolder显示空白view
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
        //当屏幕没有占满的时候，最后一个item用空白view代替footerView
        if(!isFullScreen() && position + 1 == getItemCount()){
            return TYPE_NOT_FULL;
        }
        // 最后一个item设置为footerView
        else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 对item的各个控件进行操作（包括监听setUpItemEvent）
     *
     * @param holder
     * @param t
     */
    protected abstract void convert(RecyclerView.ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    /**
     * 判断加载的item是否填充满屏幕
     * @return
     */
    public boolean isFullScreen() {
        // 窗口的宽度
        int screenWidth = SizeUtil.getScreenWidth((Activity) context);
        // 窗口高度
        int screenHeight = SizeUtil.getScreenHeight((Activity) context);
        //获取listview的item的高度
        View view = mInflater.inflate(itemLayout, null);
        view.measure(0, 0);
        //得到一屏幕上最多放的数据数量 如有标题请将标题高度减去
        int count = SizeUtil.px2dip(context, screenHeight) / SizeUtil.px2dip(context, view.getMeasuredHeight());
        if (count > getItemCount()) {
            return false;
        }else{
            return true;
        }
    }

    public interface onItemClickListener {
        void onItemClick(View view, int pos);

        void onItemLongClick(View view, int pos);
    }

    public void setClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    protected void setUpItemEvent(final /*MyViewHolder*/CommondRecyclerViewHolder holder) {
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

class FooterViewHolder extends RecyclerView.ViewHolder {

    public FooterViewHolder(View itemView) {
        super(itemView);
    }
}

