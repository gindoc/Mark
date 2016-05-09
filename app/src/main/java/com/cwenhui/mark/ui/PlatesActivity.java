package com.cwenhui.mark.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
public class PlatesActivity extends AppCompatActivity implements View.OnLayoutChangeListener {
    private RecyclerView rvPlate;
    private Map<String, Boolean> stateOftags;           //存放每个tag的状态，true为被选中，false为未被选中
    private List<String> tags;                          //存放tag的名字
    private List<String> selectedTags = new ArrayList<String>();    //存放被选中tag
    private CommonRecyclerViewAdapter<String> tagsAdapter;
    private List<String> plates;                        //存放plate的名字
    private int lastSelectedPos;                        //存放上一个被选中的view在recycler中的位置
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
        tags.addAll(Arrays.asList(getResources().getStringArray(R.array.tagsInPlatesActivity)));
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

        plates = new ArrayList<String>();
        plates.addAll(Arrays.asList(getResources().getStringArray(R.array.platesInPlateActivity)));
        plateAdapter = new CommonRecyclerViewAdapter<String>(this, R.layout.item_activity_plates_plate, plates) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                holder.setText(R.id.tv_item_activity_plates_plate, s);
                setUpItemEvent(holder);
            }
        };
        //接收之前选中的view在recyclerView中的位置，如果之前为选中过，则找不到值（为-1）
        lastSelectedPos = plates.indexOf(getIntent().getStringExtra(PublishTopicActivity.SELECTED_PLATE));
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_plates);
        toolbar.setTitle("设置板块、标签");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        RecyclerView rvTags = (RecyclerView) findViewById(R.id.rv_activity_plates_tags);
        rvTags.setLayoutManager(new FullyGridLayoutManager(this, 4));
        rvTags.setAdapter(tagsAdapter);

        rvPlate = (RecyclerView) findViewById(R.id.rv_activity_plates_plate);
        rvPlate.setLayoutManager(new FullyGridLayoutManager(this, 4));
        rvPlate.setAdapter(plateAdapter);
        rvPlate.addOnLayoutChangeListener(this);    //当Activity被加载出来或ListView里面的Item被加载出来时执行

        tagsAdapter.setClickListener(new TagsItemClickListener());
        plateAdapter.setClickListener(new PlateItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_special_practice, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putStringArrayListExtra(PublishTopicActivity.SELECTED_TAGS,
                        (ArrayList<String>) selectedTags);
                intent.putExtra(PublishTopicActivity.SELECTED_PLATE,
                                    lastSelectedPos == -1 ? null : plates.get(lastSelectedPos));
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (lastSelectedPos != 0xffffffff) {        //若之前没有选中的view，则不做处理
            setPlateSelected(rvPlate.getChildAt(lastSelectedPos), lastSelectedPos);
        }
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
                selectedTags.remove(tag);
            } else {
                Drawable drawable = ContextCompat.getDrawable(PlatesActivity.this,
                        R.drawable.tags_background_activity_plates_pressed);
                view.findViewById(R.id.tv_item_activity_plates_tags).setBackground(drawable);
                stateOftags.put(tag, true);
                selectedTags.add(tag);
            }
        }

        @Override
        public void onItemLongClick(View view, int pos) {
        }
    }

    class PlateItemClickListener implements CommonRecyclerViewAdapter.onItemClickListener {

        @Override
        public void onItemClick(View view, int pos) {
            setPlateSelected(view, pos);
        }

        @Override
        public void onItemLongClick(View view, int pos) {
        }
    }

    /**
     * 设置view为选中状态
     * @param view  选中的view
     * @param pos   view在recyclerView中的位置
     */
    private void setPlateSelected(View view, int pos) {
        Drawable pressed = ContextCompat.getDrawable(PlatesActivity.this,
                R.drawable.plate_background_activity_plates_pressed);
        view.findViewById(R.id.tv_item_activity_plates_plate).setBackground(pressed);
        //若之前没有被选中view，则lastSelectedPos为-1
        if (lastSelectedPos != 0xffffffff && lastSelectedPos != pos) {
            View lastView = rvPlate.getChildAt(lastSelectedPos)
                    .findViewById(R.id.tv_item_activity_plates_plate);
            Drawable normal = ContextCompat.getDrawable(PlatesActivity.this,
                    R.drawable.plate_background_activity_plates);
            lastView.setBackground(normal);
        }
        lastSelectedPos = pos;
    }

}
