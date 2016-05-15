package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.MyEvent;
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
 * 填空题页面
 * Created by cwenhui on 2016.02.23
 */
public class FillBlankProblemFragment extends Fragment {
    public static final String TAG = "FillBlankProblemFrag";
    private static final String INDEX = "index";                    //题目索引，即第几道题
    private View view;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<String> blankAdapter;
    private Practice practice;                                      //题目
    private int answerNumber;                                       //答案数量

    public static FillBlankProblemFragment newInstance(int index, Practice practice) {
        FillBlankProblemFragment fragment = new FillBlankProblemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, practice);
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fill_blank, container, false);
        initData();
        initView();
        return view;
    }

    private void initView() {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_fragment_fill_blank_title);
        String title = practice.getPraticeTitle().replace(Constant.OPTIONS_SPECIAL_CHARS, "(   )");
        tvTitle.setText("(多选题) " + title, TextView.BufferType.SPANNABLE);
        Spannable span = (Spannable) tvTitle.getText();
        int color = ContextCompat.getColor(getActivity(), R.color.themeColor);
        span.setSpan(new ForegroundColorSpan(color), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView tvIndex = (TextView) view.findViewById(R.id.tv_fragment_fill_blank_completion);
        tvIndex.setText(getArguments().getInt(INDEX) + 1 + "/10");

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_fill_blank);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        recyclerView.setAdapter(blankAdapter);
    }

    private void initData() {
        EventBus.getDefault().register(this);

        practice = (Practice) getArguments().get(TAG);
        answerNumber = practice.getPraticeTitle().split(Constant.SPLIT_OPTIONS).length - 1;

        String[] as = new String[answerNumber];
        List<String> answers = new ArrayList<>();
        answers.addAll(Arrays.asList(as));
        blankAdapter = new CommonRecyclerViewAdapter<String>(getActivity(),
                R.layout.item_fragment_fill_blank, answers) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                holder.setText(R.id.tv_item_fragment_fill_blank_No, "( " + holder.getLayoutPosition() + " )");
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    /**
     * 发送答题情况给答题卡页面
     */
    private void sendCompletionState() {
        FillBlankCompletionState completionState = new FillBlankCompletionState();
        completionState.index = getArguments().getInt(INDEX);
        completionState.isCompleted = !getAnswer()                      //空格是否为空，为空则尚未答题（false）
                .replace(Constant.OPTIONS_SPECIAL_CHARS, "").equals("");

        MyEvent answerState = new MyEvent();
        answerState.eventType = AnswerSheetFragment.FROM_FILLBLANK;       //事件类型
        answerState.eventData = completionState;                        //答题情况，包含题目索引和是否答题
        EventBus.getDefault().post(answerState);
    }

    @Subscribe
    public void onEventMainThread(MyEvent myEvent) {
        //当从此页面切换到其他页面时，ExaminationActivity发送事件(包含当前题目索引)，通知此页面发送答题情况给答题卡页面
        // 即ExaminationActivity-->FillBlankProblemFragment-->AnswerSheetFragment
        if (myEvent.eventType == FillBlankProblemFragment.TAG) {
            if ((int) myEvent.eventData != getArguments().getInt(INDEX)) {      //当前题目索引不是本页面索引
                sendCompletionState();
            }
        }
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
     * 获得填空题答案
     * @return  返回用户填写的答案
     */
    private String getAnswer() {
        String answer = "";
        for (int i = 0; i < answerNumber; i++) {
            View view = recyclerView.getChildAt(i);
            EditText etAnswer = (EditText) view.findViewById(R.id.et_item_fragment_fill_blank_answer);
            answer = answer + etAnswer.getText() + Constant.OPTIONS_SPECIAL_CHARS;
        }
        answer = answer.substring(0, answer.lastIndexOf(Constant.OPTIONS_SPECIAL_CHARS));
        return answer;
    }


}

/**
 * 填空题答题情况
 */
class FillBlankCompletionState {
    int index;                      //当前页面索引
    boolean isCompleted;            //是否已答题
}