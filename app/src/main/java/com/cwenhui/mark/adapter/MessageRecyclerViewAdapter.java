package com.cwenhui.mark.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.bean.MessageType;
import com.cwenhui.mark.utils.RoundImageView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private static final String TAG = "MessageRecyclerViewAdapter";
    private LayoutInflater mInflater;
    private Context context;
    protected List<MessageType> mDatas;

    public interface onItemClickListener {
        void onItemClick(View view, int pos);

        void onItemLongClick(View view, int pos);
    }

    private onItemClickListener clickListener;

    public void setClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public MessageRecyclerViewAdapter(Context context, List<MessageType> datas) {
        this.context = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_fragment_friend_msg, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);           //view 是item的布局
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {       //通过ViewHolder设置值
        MessageType type = mDatas.get(position);
        holder.textView.setText(type.getMsgTypeName());
        holder.imageView.setImageResource(type.getResId());
        if (type.isPromptNewMsg()) {
            holder.newMsg.setVisibility(View.VISIBLE);
        }else{
            holder.newMsg.setVisibility(View.GONE);
        }
        setUpItemEvent(holder);

    }

    protected void setUpItemEvent(final MyViewHolder holder) {
        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 因为是通过notifyItemInserted(pos);通知数据更新的，不会刷新所有的view，
                     * 所以这里获得的所有position都是1（之前是addData(1)和deleteData(1)）,
                     * 解决办法：通过ViewHolder的getLayoutPosition()获得position
                     */
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

    public void checkAdapterIsEmpty (View emptyView) {
        if (getItemCount() == 0) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int pos) {
//        mDatas.add(pos, "insert data");
//        notifyItemInserted(pos);            //notifyDataSetChanged();没有动画效果

    }

    public void deleteData(int pos) {
//        mDatas.remove(pos);
//        notifyItemRemoved(pos);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "MyViewHolder";
    TextView textView;
    RoundImageView imageView;
    ImageView newMsg;

    public MyViewHolder(View viewGroup) {
        super(viewGroup);
        textView = (TextView) viewGroup.findViewById(R.id.tv_item_fragment_friend_msg_title);
        imageView = (RoundImageView) viewGroup.findViewById(R.id.iv_item_fragment_friend_msg_img);
        newMsg = (ImageView) viewGroup.findViewById(R.id.iv_item_fragment_friend_msg_new_msg);
    }
}