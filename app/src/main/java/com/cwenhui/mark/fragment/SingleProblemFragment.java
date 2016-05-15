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
 * 单选题页面
 * Created by cwenhui on 2016.02.23
 */
public class SingleProblemFragment extends Fragment implements CommonRecyclerViewAdapter.onItemClickListener {
    public static final String TAG = "SingleProblemFragment";
    private static final String INDEX = "index";                    //题目索引，即第几道题
    private View view;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<String> choiceAdapter;
    private Practice practice;                                      //题目
    private int lastPostion = -1;                                   //最后一次被选中的选项

    public static SingleProblemFragment newInstance(int index, Practice practice) {
        SingleProblemFragment fragment = new SingleProblemFragment();
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

    private void initData() {
        EventBus.getDefault().register(this);
        List<String> choices = new ArrayList<>();
        practice = (Practice) getArguments().get(TAG);
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

    private void initView() {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_fragment_choice_prolem_title);
        tvTitle.setText("(单选题) " + practice.getPraticeTitle(), TextView.BufferType.SPANNABLE);
        Spannable span = (Spannable) tvTitle.getText();
        int color = ContextCompat.getColor(getActivity(), R.color.themeColor);
        span.setSpan(new ForegroundColorSpan(color), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView tvIndex = (TextView) view.findViewById(R.id.tv_fragment_choice_completion);
        tvIndex.setText(getArguments().getInt(INDEX) + 1 + "/10");

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_choice_problem);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        recyclerView.setAdapter(choiceAdapter);
        choiceAdapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int pos) {
        Drawable pressed = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_selected);
        view.findViewById(R.id.tv_item_fragment_choice_problem).setBackground(pressed);
        if (lastPostion != -1 && lastPostion != pos) {
            View lastView = recyclerView.getChildAt(lastPostion)
                    .findViewById(R.id.tv_item_fragment_choice_problem);
            Drawable normal = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_normal);
            lastView.setBackground(normal);
        }
        lastPostion = pos;
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
    public void onItemLongClick(View view, int pos) {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Subscribe
    public void onEventMainThread(MyEvent myEvent) {
        //当点击提交试卷时，ExaminationActivity发送过来的事件
        if (myEvent.eventType == ExaminationActivity.TAG) {
//            //发送答题情况给答题卡页面，包含题目索引
//            MyEvent answer = new MyEvent();
//            answer.eventType = getArguments().getInt(INDEX) + "";
////            answer.eventData = (char)(lastPostion+65)+"";
//            answer.eventData = false;
//            if (practice.getPraticeAnswer().equals((char) (lastPostion + 65) + "")) {
//                answer.eventData = true;
//            }
//            MyEvent event = new MyEvent();
//            event.eventType = AnswerSheetFragment.TAG;
//            event.eventData = answer;
//            EventBus.getDefault().post(event);

            //此处还有进行网络请求，向服务器数据库的已做题库插入此道题
            ProblemPresenter presenter = new ProblemPresenter();
            presenter.submitAnswerForExamination(getArguments().getInt(INDEX));
        }
    }
}
