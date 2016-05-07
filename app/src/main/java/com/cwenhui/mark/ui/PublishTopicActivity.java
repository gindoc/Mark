package com.cwenhui.mark.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cwenhui.mark.R;
import com.cwenhui.mark.presenter.PublishTopicPresenter;
import com.cwenhui.mark.view.IPublishTopicView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PublishTopicActivity extends AppCompatActivity implements IPublishTopicView{
    private PublishTopicPresenter presenter;
    private LinearLayout llPlates;
    private EditText etTopic, etContent;

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

        llPlates = (LinearLayout) findViewById(R.id.ll_activity_publish_topic_plate);
        etTopic = (EditText) findViewById(R.id.et_activity_publish_topic_topic);
        etContent = (EditText) findViewById(R.id.et_activity_publish_topic_content);
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
                presenter.checkPlatesOrTags(PublishTopicActivity.this, llPlates);
                presenter.isTopicEmpty(etTopic);
                presenter.isContentEmpty(etContent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_FIRST_USER && resultCode == RESULT_OK) {
            // TODO: 2016/5/7 处理板块信息，将板块信息添加到llPlates,还有tags
        }

    }

    @Override
    public void ShowTips(String tips) {
        Toast.makeText(this, tips, Toast.LENGTH_SHORT).show();
    }

    //    @Override
//    public void toPlatesActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivityForResult(intent, RESULT_FIRST_USER);
//    }
}
