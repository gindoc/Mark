package com.cwenhui.mark.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.MyEvent;
import com.cwenhui.mark.common.TextUtils;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.Practice;
import com.cwenhui.mark.presenter.ProblemPresenter;
import com.cwenhui.mark.ui.ExaminationActivity;
import com.cwenhui.mark.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 多选题页面
 * Created by cwenhui on 2016.02.23
 */
public class MultipleProblemFragment extends Fragment implements CommonRecyclerViewAdapter.onItemClickListener {
    public static final String TAG = "MultipleProblemFragment";
    private static final String INDEX = "index";                    //题目索引，即第几道题
    private View view;
    private CommonRecyclerViewAdapter<String> choiceAdapter;
    private List<String> answers = new ArrayList<>();               //用户所选答案
    private Practice practice;                                      //题目

    public static MultipleProblemFragment newInstance(int index, Practice practice) {
        MultipleProblemFragment fragment = new MultipleProblemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, practice);
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choice_problem, container, false);
        initData();
        initView();
        return view;
    }

    private void initView() {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_fragment_choice_prolem_title);
        tvTitle.setText("(多选题) " + practice.getPraticeTitle(), TextView.BufferType.SPANNABLE);

        Spannable span = (Spannable) tvTitle.getText();
        int color = ContextCompat.getColor(getActivity(), R.color.themeColor);
        span.setSpan(new ForegroundColorSpan(color), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView tvIndex = (TextView) view.findViewById(R.id.tv_fragment_choice_completion);
        tvIndex.setText(getArguments().getInt(INDEX) + 1 + "/10");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_choice_problem);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        recyclerView.setAdapter(choiceAdapter);
        choiceAdapter.setClickListener(this);
    }

    private void initData() {
        EventBus.getDefault().register(this);

        practice = (Practice) getArguments().get(TAG);

        List<String> choices = new ArrayList<>();
        String[] options = practice.getPraticeOptions().split(Constant.SPLIT_OPTIONS);
        choices.addAll(Arrays.asList(options));
        choiceAdapter = new CommonRecyclerViewAdapter<String>(getActivity(),
                R.layout.item_fragment_choice_problem, choices) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                char opt = (char) (holder.getLayoutPosition() + 65);
                holder.setText(R.id.tv_item_fragment_choice_problem_option, s)
                        .setText(R.id.tv_item_fragment_choice_problem, opt + "");
                setUpItemEvent(holder);
            }
        };
    }

    @Override
    public void onItemClick(View view, int pos) {
        char c = (char) (pos + 65);
        View option = view.findViewById(R.id.tv_item_fragment_choice_problem);
        if (answers.contains(c + "")) {             //如果该选项已选，则移出
            Drawable normal = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_normal);
            option.setBackground(normal);
            answers.remove(c + "");
        } else {                                    //如果该选项未选，则添加
            Drawable pressed = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_selected);
            option.setBackground(pressed);
            answers.add(c + "");
        }
        sendCompletionState();
    }

    /**
     * 发送答题情况给答题卡页面，包含题目索引
     */
    private void sendCompletionState() {
        MyEvent answerState = new MyEvent();
        answerState.eventType = AnswerSheetFragment.TAG;
        answerState.eventData = getArguments().getInt(INDEX);
        EventBus.getDefault().post(answerState);
    }

    @Override
    public void onItemLongClick(View view, int pos) { }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Subscribe
    public void onEventMainThread(MyEvent myEvent) {
        //当点击提交试卷时，ExaminationActivity发送过来的事件
        if (myEvent.eventType == ExaminationActivity.TAG) {
            //处理结果，并发送给答题卡页面
//            MyEvent answer = new MyEvent();
//            answer.eventType = getArguments().getInt(INDEX) + "";
////            answer.eventData = getAnswer();
//            answer.eventData = false;
//            if (practice.getPraticeAnswer().equals(getAnswer())) {
//                answer.eventData = true;
//            }
//            MyEvent event = new MyEvent();
//            event.eventType = AnswerSheetFragment.TAG;
//            event.eventData = answer;
//            EventBus.getDefault().post(event);
            ProblemPresenter presenter = new ProblemPresenter();
            presenter.submitAnswerForExamination(getArguments().getInt(INDEX));
        }
    }

    /**
     * 获得答案
     *
     * @return
     */
    private String getAnswer() {
        String answer = "";
        for (int i = 0; i < answers.size(); i++) {
            answer = answer + answers.get(i) + Constant.OPTIONS_SPECIAL_CHARS;
        }
        if (!TextUtils.isEmpty(answer)) {
            answer = answer.substring(0, answer.lastIndexOf(Constant.OPTIONS_SPECIAL_CHARS));
        }
        return answer;
    }
}
