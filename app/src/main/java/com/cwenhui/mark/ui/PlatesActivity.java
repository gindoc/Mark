package com.cwenhui.mark.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.widget.FullyGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PlatesActivity extends AppCompatActivity {
    private static final String SELECTED_TAG = "selectedTags";
    private RecyclerView rvPlate;
    private RecyclerView rvTags;
    private Map<String, Boolean> stateOftags;           //存放每个tag的状态，true为被选中，false为未被选中
    private List<String> tags;                          //存放tag的名字
    private List<String> selectedTags = new ArrayList<String>();    //存放被选中tag
    private CommonRecyclerViewAdapter<String> tagsAdapter;
    private List<String> plates;
    private Map<String, Integer> selectedPlate = new HashMap<String, Integer>();
    private CommonRecyclerViewAdapter<String> plateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates);

        initData();
        initView();
    }

    private void initData() {
        stateOftags = new HashMap<>();
        tags = new ArrayList<String>();
        String[] t = new String[]{"百度", "腾讯", "去哪儿", "人人", "微博", "阿里巴巴", "携程", "奇虎360",
                "谷歌", "微软", "小米", "java工程师", "搜狐", "网易", "唯品会", "京东",
                "迅雷", "华为", "滴滴", "蘑菇街", "php工程师", "58", "网龙", "算法工程师",
                "前端工程师", "完美世界", "ios工程师", "C++", "运维工程师", "安卓工程师", "大众点评", "中兴",
                "今日头条", "CVTE", "微店", "美团", "搜狗"};
        tags.addAll(Arrays.asList(t));
        for (String s : tags) {
            stateOftags.put(s, false);          //将每个tag的初始状态都设为未选中 false
        }
        tagsAdapter = new CommonRecyclerViewAdapter<String>(this, R.layout.item_activity_plates_tags, tags) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                holder.setText(R.id.tv_item_activity_plates_tags, s);
                setUpItemEvent(holder);
            }
        };

        String[] p = new String[]{"技术交流", "笔试面经", "随便聊聊", "资源分享", "我要提问",
                "招聘信息", "工作感受"};
        plates = new ArrayList<String>();
        plates.addAll(Arrays.asList(p));
        plateAdapter = new CommonRecyclerViewAdapter<String>(this, R.layout.item_activity_plates_plate, plates) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                holder.setText(R.id.tv_item_activity_plates_plate, s);
                setUpItemEvent(holder);
            }
        };
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_plates);
        toolbar.setTitle("设置板块、标签");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        rvTags = (RecyclerView) findViewById(R.id.rv_activity_plates_tags);
        rvTags.setLayoutManager(new FullyGridLayoutManager(this, 4));
        rvTags.setAdapter(tagsAdapter);

        rvPlate = (RecyclerView) findViewById(R.id.rv_activity_plates_plate);
        rvPlate.setLayoutManager(new FullyGridLayoutManager(this, 4));
        rvPlate.setAdapter(plateAdapter);

        tagsAdapter.setClickListener(new TagsItemClickListener());
        plateAdapter.setClickListener(new PlateItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_special_practice, menu);
        return super.onCreateOptionsMenu(menu);
    }

    class TagsItemClickListener implements CommonRecyclerViewAdapter.onItemClickListener {

        @Override
        public void onItemClick(View view, int pos) {
            String tag = tags.get(pos);
            if (stateOftags.get(tag)) {
                Drawable drawable = ContextCompat.getDrawable(PlatesActivity.this,
                        R.drawable.tags_background_activity_plates);
                view.findViewById(R.id.tv_item_activity_plates_tags).setBackground(drawable);
                stateOftags.put(tag, false);
            } else {
                Drawable drawable = ContextCompat.getDrawable(PlatesActivity.this,
                        R.drawable.tags_background_activity_plates_pressed);
                view.findViewById(R.id.tv_item_activity_plates_tags).setBackground(drawable);
                stateOftags.put(tag, true);
            }
        }

        @Override
        public void onItemLongClick(View view, int pos) {
        }
    }

    class PlateItemClickListener implements CommonRecyclerViewAdapter.onItemClickListener {

        @Override
        public void onItemClick(View view, int pos) {
            Drawable pressed = ContextCompat.getDrawable(PlatesActivity.this,
                    R.drawable.plate_background_activity_plates_pressed);
            view.findViewById(R.id.tv_item_activity_plates_plate).setBackground(pressed);
            if (selectedPlate.size() != 0) {
                int lastPos = selectedPlate.get(SELECTED_TAG);
                View lastView = rvPlate.getChildAt(lastPos).findViewById(R.id.tv_item_activity_plates_plate);
                Drawable normal = ContextCompat.getDrawable(PlatesActivity.this,
                        R.drawable.plate_background_activity_plates);
                lastView.setBackground(normal);
            }
            selectedPlate.put(SELECTED_TAG, pos);

        }

        @Override
        public void onItemLongClick(View view, int pos) {
        }
    }

}
