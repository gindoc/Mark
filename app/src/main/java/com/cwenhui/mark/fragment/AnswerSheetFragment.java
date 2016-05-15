package com.cwenhui.mark.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.MyEvent;
import com.cwenhui.mark.ui.ExaminationActivity;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 答题卡页面
 * Created by cwenhui on 2016.02.23
 */
public class AnswerSheetFragment extends Fragment implements CommonRecyclerViewAdapter.onItemClickListener {
    public static final String TAG = "AnswerSheetFragment";
    public static final String FROM_FILLBLANK = "fromFillblank";
    private View view;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_answer_sheet, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        EventBus.getDefault().register(this);

        List<String> answers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            answers.add(i + "");
        }
        adapter = new CommonRecyclerViewAdapter<String>(getActivity(),
                R.layout.item_fragment_answer_sheet, answers) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                holder.setText(R.id.tv_item_fragment_answer_sheet, s);
                setUpItemEvent(holder);
            }
        };
        adapter.setClickListener(this);
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_answer_sheet);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onItemClick(View view, int pos) {
        MyEvent event = new MyEvent();
        event.eventType = ExaminationActivity.FROM_ANSWERSHEET;
        event.eventData = pos;
        EventBus.getDefault().post(event);
    }

    @Override
    public void onItemLongClick(View view, int pos) {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Subscribe
    public void onEventMainThread(MyEvent myEvent) {
//        if (myEvent.eventType == AnswerSheetFragment.TAG) {
//            //处理各个页面提交的结果
//            MyEvent answer = (MyEvent) myEvent.eventData;
//            View view = recyclerView.getChildAt(Integer.parseInt(answer.eventType));
//            View No = view.findViewById(R.id.tv_item_fragment_answer_sheet);
//            Drawable drawable;
//            if ((boolean) answer.eventData) {
//                drawable = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_selected);
////            No.setBackground();
//            } else {
//                drawable = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_wrong);
//            }
//            No.setBackground(drawable);
//        }

        //选择题页面发过来的事件
        if (myEvent.eventType == AnswerSheetFragment.TAG) {
            View view = recyclerView.getChildAt((Integer) myEvent.eventData);       //找到对应的题号
            View No = view.findViewById(R.id.tv_item_fragment_answer_sheet);
            Drawable drawable = ContextCompat.getDrawable(getActivity(),            //如果填写了答案，填充颜色
                    R.drawable.choice_question_option_selected);
            No.setBackground(drawable);
        }
        //填空题页面发过来的事件
        if (myEvent.eventType == AnswerSheetFragment.FROM_FILLBLANK) {
            FillBlankCompletionState completionState = (FillBlankCompletionState) myEvent.eventData;
            View view = recyclerView.getChildAt(completionState.index);         //找到对应的题号
            View No = view.findViewById(R.id.tv_item_fragment_answer_sheet);
            if (completionState.isCompleted) {                                  //如果填写了答案，填充颜色
                Drawable drawable = ContextCompat.getDrawable(getActivity(),
                        R.drawable.choice_question_option_selected);
                No.setBackground(drawable);
            }else{                                                              //未填写答案，默认背景
                Drawable drawable = ContextCompat.getDrawable(getActivity(),
                        R.drawable.choice_question_option_normal);
                No.setBackground(drawable);
            }
        }
    }
}