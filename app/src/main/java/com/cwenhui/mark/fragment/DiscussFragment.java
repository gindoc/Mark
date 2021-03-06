package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.common.CommonRefreshRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.ImageFirstDisplayListener;
import com.cwenhui.mark.common.ImageLoaderHelper;
import com.cwenhui.mark.common.RVScrollListener;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.DiscussPresenter;
import com.cwenhui.mark.view.IDiscussView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DiscussFragment extends Fragment implements IDiscussView,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "DiscussFragment";
    private View view;
    private DiscussPresenter presenter;
    private SwipeRefreshLayout swipe;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private CommonRefreshRecyclerViewAdapter<Discuss> adapter;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;                                //显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();
//    private Handler mHandler = new Handler();
    public static final int ALL = 10, TECH = 11, INTERVIEW_EXCE = 12, CHAT = 13, NOTICE = 14, REC_SHARE = 15,
            QUES = 16, RECRUIT = 17, WORK_FEELING = 18;
    private int plate;

    public static DiscussFragment newInstance(int plate) {
        DiscussFragment discussFragment = new DiscussFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("plate", plate);
        discussFragment.setArguments(bundle);
        return discussFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discuss_all, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        //注册EventBus
//        EventBus.getDefault().register(this);
        options = ImageLoaderHelper.getInstance().initImageLoader(getActivity(),
                imageLoader, Constant.IMAGE_PATH, R.drawable.nowcoder_ic_launcher);
        presenter = new DiscussPresenter(this);
        plate = (int) getArguments().get("plate");
    }

    private void initView() {
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fragment_discuss_all);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_discuss_all);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        swipe.setColorSchemeResources(R.color.swipeColor1, R.color.swipeColor2,
                R.color.swipeColor3, R.color.swipeColor4);
        swipe.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipe.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                24, getResources().getDisplayMetrics()));
        swipe.setRefreshing(true);

//        presenter.initDiscussListForEventBus(plate);
        presenter.initDiscussList(plate);
    }

    @Override
    public void onRefresh() {
//        presenter.refleshForEventBus(plate, Constant.PULL_DOWN);
        presenter.reflesh(Constant.PULL_DOWN);
    }

    @Override
    public void initDiscussList(List<Discuss> discusses) {
        adapter = new CommonRefreshRecyclerViewAdapter<Discuss>(getActivity(), R.layout.item_fragment_disguss,
                R.layout.layout_footer_discuss, discusses) {

            @Override
            protected void convert(RecyclerView.ViewHolder holder, Discuss discuss) {
                ((CommondRecyclerViewHolder) holder)
                        .setText(R.id.tv_item_fragment_discuss_name, discuss.getDcsName())
                        .setText(R.id.tv_item_fragment_discuss_publish_time, discuss.getPublishTime())
                        .setText(R.id.tv_item_fragment_discuss_topic, discuss.getTopic())
                        .setText(R.id.tv_item_fragment_discuss_content, discuss.getContent())
                        .setText(R.id.tv_item_fragment_discuss_cat, "[" + discuss.getCategory().getDisCatName() + "]")
                        .setVisibility(R.id.tv_item_fragment_discuss_level,
                                discuss.getLevel() == 1 ? View.VISIBLE : View.GONE);
                imageLoader.displayImage(discuss.getDcsImg(), (ImageView)
                                ((CommondRecyclerViewHolder) holder).getView(R.id.iv_item_fragment_discuss_img),
                        options, displayListener);
            }
        };
        recyclerView.setHasFixedSize(true);     //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RVScrollListener(recyclerView, swipe, presenter));
    }

    @Override
    public void refleshDiscussList(List<Discuss> discusses) {
        adapter.getmDatas().addAll(discusses);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void switchType(String type) {
        swipe.setRefreshing(true);
        presenter.switchDiscussType(plate, type);
    }

    @Override
    public void hideLoading() {
        swipe.setRefreshing(false);
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        EventBus.getDefault().unregister(this);//反注册EventBus
//    }

//    /**
//     * 测试使用EventBus使用情况，感觉使用EventBus后不用presenter了
//     *
//     * @param discusses
//     */
//    @Subscribe
//    public void onEventMainThread(final List<Discuss> discusses) {
//        Log.e(TAG, "onEventBackgroundThread");
//        mHandler.post(new Runnable() {          //要在主线程中更新UI
//            @Override
//            public void run() {
//                Log.e(TAG, "onEventMainThread");
//                if (adapter == null) {
//                    initDiscussList(discusses);
//                } else {
//                    adapter.getmDatas().addAll(discusses);
//                    adapter.notifyDataSetChanged();
//                }
//                hideLoading();
//                synchronized ((Object) RVScrollListener.isLoading) {
//                    RVScrollListener.isLoading = false;
//                }
//            }
//        });
//    }
}
