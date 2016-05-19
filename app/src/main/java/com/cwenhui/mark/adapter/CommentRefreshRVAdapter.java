package com.cwenhui.mark.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRefreshRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.ImageFirstDisplayListener;
import com.cwenhui.mark.common.ImageLoaderHelper;
import com.cwenhui.mark.common.SizeUtil;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.Comment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 评论区，即试题解析的适配器
 * Created by cwenhui on 2016.02.23
 */
public class CommentRefreshRVAdapter extends CommonRefreshRecyclerViewAdapter<Comment> {
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;                                //显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();
    private float y;                                                    //recyclerview在父容器中的纵坐标
    private float parentHeight;                                         //scrollview的高度

    public CommentRefreshRVAdapter(Context context, int itemLayout, int footerLayout, List<Comment> datas) {
        super(context, itemLayout, footerLayout, datas);
        options = ImageLoaderHelper.getInstance().initImageLoader(context,
                imageLoader, Constant.IMAGE_PATH, R.drawable.nowcoder_ic_launcher);
    }

    @Override
    protected void convert(RecyclerView.ViewHolder holder, Comment comment) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ((CommondRecyclerViewHolder) holder).setText(R.id.tv_item_comment_username, comment
                .getUser().getUsername())
                .setText(R.id.tv_item_comment_publish_time, sdf.format(comment.getCreateTime()))
                .setText(R.id.tv_item_comment_content, comment.getContent());
        imageLoader.displayImage(comment.getUser().getUsername(), (ImageView) (
                        (CommondRecyclerViewHolder) holder).getView(R.id.iv_item_comment_img),
                options, displayListener);
    }

    /**
     * 获取recyclerview在父容器中的位置
     * @param y     recyclerview在父容器中的纵坐标
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * 获取scrollview的高度
     * @param parentHeight      scrollview的高度
     */
    public void setParentHeight(float parentHeight) {
        this.parentHeight = parentHeight;
    }

    @Override
    public boolean isFullScreen() {
        //获取recyclerView的item的高度
        View view = mInflater.inflate(itemLayout, null);
        view.measure(0, 0);
        //得到一屏幕上最多放的数据数量 如有标题请将标题高度减去
        int count = SizeUtil.px2dip(context, parentHeight - y) / SizeUtil.px2dip(context, view
                .getMeasuredHeight());
        if (count > getItemCount()) {
            return false;
        } else {
            return true;
        }
    }
}
