package com.cwenhui.mark.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cwenhui.mark.R;
import com.cwenhui.mark.presenter.PublishTopicPresenter;
import com.cwenhui.mark.view.IPublishTopicView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PublishTopicActivity extends AppCompatActivity implements IPublishTopicView, View.OnClickListener {
    public static final String SELECTED_TAGS = "selectedTags";
    public static final String SELECTED_PLATE = "selectedPlate";
    private static final int SELECTED_PLATE_ID = Integer.MAX_VALUE;
    private PublishTopicPresenter presenter;
    private RelativeLayout rlPlates;
    private EditText etTitle, etContent;
    private String plate = null;
    private List<String> tags = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_topic);

        initView();
    }

    private void initView() {
        presenter = new PublishTopicPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_publish_topic);
        toolbar.setTitle("发表帖子");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        rlPlates = (RelativeLayout) findViewById(R.id.rl_activity_publish_topic_plate);
        etTitle = (EditText) findViewById(R.id.et_activity_publish_topic_title);
        etContent = (EditText) findViewById(R.id.et_activity_publish_topic_content);

        rlPlates.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish_topic, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;

            case R.id.publish_topic_publish:
                presenter.checkMessage(tags);
                break;
            case R.id.rl_activity_publish_topic_plate:
                toPlatesActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_FIRST_USER && resultCode == RESULT_OK) {
            tags = data.getStringArrayListExtra(SELECTED_TAGS);
            plate = data.getStringExtra(SELECTED_PLATE);
            addPlate(plate);
        }

    }

    @Override
    public void ShowTips(String tips) {
        Toast.makeText(this, tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public CharSequence getTopicTitle() {
        return etTitle.getText();
    }

    @Override
    public CharSequence getContent() {
        return etContent.getText();
    }

    @Override
    public void addPlate(String plate) {
        TextView tvPlate = (TextView) findViewById(SELECTED_PLATE_ID);
        if (tvPlate == null) {
            tvPlate = new TextView(this);
            tvPlate.setId(SELECTED_PLATE_ID);       //设置id
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.LEFT_OF, R.id.iv_activity_publish_topic_plate);
            lp.rightMargin = (int) getResources().getDimension(R.dimen.x10);
            rlPlates.addView(tvPlate, lp);
        }
        tvPlate.setText(plate);
        tvPlate.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
    }

    @Override
    public void toPlatesActivity() {
        Intent intent = new Intent(this, PlatesActivity.class);
        startActivityForResult(intent, RESULT_FIRST_USER);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PlatesActivity.class);
        intent.putExtra(SELECTED_PLATE, plate);
        startActivityForResult(intent, RESULT_FIRST_USER);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }
}
