package com.cwenhui.mark.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.utils.ImageFirstDisplayListener;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

/**
 * 填充ViewPager的页面适配器
 * Created by cwenhui on 2016.02.23
 */
public class SlideShowViewAdapter extends PagerAdapter {
    private static final String TAG = "SlideShowViewAdapter";
    private Context context;
    private List<ImageView> imageViewsList;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;								//显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();

    public SlideShowViewAdapter(Context context, List<ImageView> imageViewsList) {
        this.imageViewsList = imageViewsList;
        this.context = context;
        initImageLoader(context);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(imageViewsList.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ImageView imageView = imageViewsList.get(position);

        imageLoader.displayImage(imageView.getTag() + "", imageView,options,displayListener);


        ((ViewPager)container).addView(imageViewsList.get(position));
        return imageViewsList.get(position);
    }

    @Override
    public int getCount() {
        return imageViewsList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.mianshi) 		    // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.mianshi) 	        // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.mianshi) 		        // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true) 								// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) 									// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) 							// 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .displayer(new RoundedBitmapDisplayer(20))			// 是否设置为圆角，弧度为多少
                .displayer(new SimpleBitmapDisplayer())				// 是否图片加载好后渐入的动画时间,
                                    //此时不要使用FadeInBitmapDisplayer(100),否则更新listview时会出现闪烁的问题
                .build();
        imageLoader.init(config);
    }
}
